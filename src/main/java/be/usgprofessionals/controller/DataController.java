package be.usgprofessionals.controller;

import be.usgprofessionals.Exceptions.DataMergeException;
import be.usgprofessionals.Exceptions.DataNotFoundException;
import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.QConsultantdump;
import be.usgprofessionals.QProjectsdump;
import be.usgprofessionals.QSpeakapdump;
import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.UniqueList;
import be.usgprofessionals.model.dbclasses.*;
import be.usgprofessionals.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Component
public class DataController {

    @Autowired
    private DatadumpDBController datadumpDbController;
    @Autowired
    private DigigramDBController digigramDBController;

    public DataController() {
    }

    public ArrayList<String> run() {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        QConsultantdump qConsultantdump = new QConsultantdump("c");
        List<SpeakapEmployee> employees = datadumpDbController.getAllEmployees();
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Employee> digiEmployees = new ArrayList<>();
        for (SpeakapEmployee employee : employees) {
            String name = employee.getLastname();
            String firstname = employee.getFirstname();
            try {

                ConsultantEmployee consultant = datadumpDbController.getConsultant(name + " " + firstname);
                List<Project> projects = getProjects(consultant.getIdnr());
                digiEmployees.add(createEmployeeObject(employee, consultant, projects));
            } catch (DataNotFoundException dnfe) {
                errors.add(dnfe.getMessage());
                try {
                    InternalEmployee internalEmployee = datadumpDbController.getInternal(name, firstname);
                    digiEmployees.add(createEmployeeObject(employee, internalEmployee, null));
                } catch (DataMergeException | DataNotFoundException e) {
                    errors.add(e.getMessage());
                    e.printStackTrace();
                } catch (EIDFormatIncorrectException e) {
                    e.printStackTrace();
                }
                dnfe.printStackTrace();
            } catch (DataMergeException dme) {
                errors.add(dme.getMessage());
            } catch (EIDFormatIncorrectException e) {
                e.printStackTrace();
            }
        }

        return insertEmployees(errors, digiEmployees);
    }

    private boolean insertData(SpeakapEmployee speakapEmployee, ConsultantEmployee consultantEmployee, List<Project> projects) {
        QProjectsdump qProjectsdump = new QProjectsdump("p");

        return true;
    }

    public Employee createEmployeeObject(SpeakapEmployee speakapEmployee, Object consultantInternalEmployee, List<Project> projects) throws EIDFormatIncorrectException {
        if (consultantInternalEmployee instanceof ConsultantEmployee) {
            ConsultantEmployee consultantEmployee = (ConsultantEmployee) consultantInternalEmployee;
            Project mainProject = getMainProject(projects);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            Date birthday;
            try {
                birthday = sdf.parse(consultantEmployee.getBirthday());
            } catch (ParseException e) {
                e.printStackTrace();
                birthday = new Date();
            }
            return new Employee(birthday, speakapEmployee.getFirstname(), speakapEmployee.getLastname(),
                    consultantEmployee.getEmail(), speakapEmployee.getProfilePicURL(), consultantEmployee.getMobile(), mainProject.getBranch(), null, mainProject.getCostumer(),
                    "Consultant " + mainProject.getBranch(), 0, new EID(speakapEmployee.getEid()), projects);
        } else if (consultantInternalEmployee instanceof InternalEmployee) {
            InternalEmployee internalEmployee = (InternalEmployee) consultantInternalEmployee;
            internalEmployee = setDepartments(internalEmployee);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday;
            try {
                birthday = sdf.parse(speakapEmployee.getBirthday());
            } catch (ParseException e) {
                e.printStackTrace();
                birthday = new Date();
            }
            return new Employee(birthday, speakapEmployee.getFirstname(), speakapEmployee.getLastname(), internalEmployee.getEMail(), speakapEmployee.getProfilePicURL(),
                    internalEmployee.getMobile(), internalEmployee.getTeam(), internalEmployee.getNext_dept(), "USG Professionals", internalEmployee.getFunction(), 1, new EID(speakapEmployee.getEid()), null);
        } else {
            return null;
        }
    }

    private InternalEmployee setDepartments(InternalEmployee internalEmployee) {
        String level0 = internalEmployee.getLevel0();
        String level1 = internalEmployee.getLevel0();
        String level2 = internalEmployee.getLevel2();
        String level3 = internalEmployee.getLevel3();
        String department = internalEmployee.getTeam();
        if (!Objects.equals(level0, "")) {
            internalEmployee.setNext_dept(returnDeptName(level1));
        } else if (!Objects.equals(level1, "")) {
            internalEmployee.setTeam(returnDeptName(level1));
            internalEmployee.setNext_dept(level2);
        } else if (!Objects.equals(level2, "")) {
            if (!Objects.equals(level3, "")) {
                internalEmployee.setNext_dept(internalEmployee.getTeam());
            }
            internalEmployee.setTeam(level2);
        }
        return internalEmployee;
    }

    private String returnDeptName(String level) {
        if (level.equals("Root Op +1")) {
            return "Operations";
        } else if (level.equals("Root Mang +1")) {
            return "Management Team";
        } else return null;
    }

    public Project getMainProject(List<Project> projects) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        Project mainProject;
        final Comparator<Project> longestProject = (p1, p2) -> {
            try {
                return Long.compare(sdf.parse(p1.getEnddate()).getTime() - sdf.parse(p1.getStartdate()).getTime(),
                        sdf.parse(p2.getEnddate()).getTime() - sdf.parse(p2.getStartdate()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
        };
        mainProject = projects
                .stream()
                .max(longestProject)
                .get();

        return mainProject;
    }

    public UniqueList<Project> getProjects(String idnr) throws DataMergeException {
        List<Project> projects = datadumpDbController.getProjects(idnr);
        UniqueList<Project> uniqueProjects = new UniqueList<>();
        projects.forEach(uniqueProjects::addProject);
        return uniqueProjects;
    }

    private ArrayList<String> insertEmployees(ArrayList<String> errors, ArrayList<Employee> employees) {
        employees
                .forEach(employee -> {
                    try {
                        insertEmployee(employee);
                    } catch (Exception e) {
                        errors.add(e.getMessage());
                    }
                });
        return errors;
    }

    private void insertEmployee(Employee employee) throws Exception {
        boolean manager = false;
        Department dept = digigramDBController.getDeptId(employee.getDept_name());
        Department next_dept;
        if (employee.getNext_dept_name() != null) {
            next_dept = digigramDBController.getDeptId(employee.getNext_dept_name());
        } else {
            next_dept = new Department(null, -1, null);
        }
        Employer employer = digigramDBController.getCreateEmployerID(employee.getEmployer_name());
        int project_id = digigramDBController.getCreateProjectID(employee.getProject_name());
        digigramDBController.insertEmployee(dept, next_dept, employer.getEmployer_id(), employee, project_id);
        //TODO set reportsTo
    }


    public ArrayList<Department> getAllDepartments() {
        try {
            return digigramDBController.getAllDepartments();
        } catch (SQLException | EIDFormatIncorrectException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<SpeakapEmployee> getAllSpeakaps() {
        return datadumpDbController.getAllSpeakaps();
    }

    public boolean newSpeakap(SpeakapEmployee e) {
        return datadumpDbController.newSpeakap(e);
    }

    public boolean newDepartment(Department department) {
        try {
            return digigramDBController.newDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSpeakap(String id) {
        return datadumpDbController.deleteSpeakap(id);
    }

    public boolean deleteDepartment(String id) {
        try {
            return digigramDBController.deleteDepartment(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
