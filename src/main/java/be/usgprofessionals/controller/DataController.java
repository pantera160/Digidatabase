package be.usgprofessionals.controller;

import be.usgprofessionals.Exceptions.DataMergeException;
import be.usgprofessionals.Exceptions.DataNotFoundException;
import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.QConsultantdump;
import be.usgprofessionals.QProjectsdump;
import be.usgprofessionals.QSpeakapdump;
import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.UniqueList;
import be.usgprofessionals.model.dbclasses.ConsultantEmployee;
import be.usgprofessionals.model.dbclasses.InternalEmployee;
import be.usgprofessionals.model.dbclasses.Project;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import be.usgprofessionals.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@ComponentScan("be.usgprofessionals.controller")
public class DataController {

    @Autowired
    private DBController dbController;

    public DataController() {
        if (dbController == null) {
            dbController = new DBController();
        }
    }

    public ArrayList<String> run() {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        QConsultantdump qConsultantdump = new QConsultantdump("c");
        List<SpeakapEmployee> employees = dbController.getAllEmployees();
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Employee> digiEmployees = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            String name = employees.get(i).getLastname();
            String firstname = employees.get(i).getFirstname();
            try {

                ConsultantEmployee consultant = dbController.getConsultant(name + " " + firstname);
                List<Project> projects = getProjects(consultant.getIdnr());
                digiEmployees.add(createEmployeeObject(employees.get(i), consultant, projects));
            } catch (DataNotFoundException dnfe) {
                errors.add(dnfe.getMessage());
                try {
                    InternalEmployee internalEmployee = dbController.getInternal(name, firstname);
                } catch (DataMergeException | DataNotFoundException e) {
                    errors.add(e.getMessage());
                    e.printStackTrace();
                }
                dnfe.printStackTrace();
            } catch (DataMergeException dme) {
                errors.add(dme.getMessage());
            } catch (EIDFormatIncorrectException e) {
                e.printStackTrace();
            }
        }

        insertEmployees(errors, digiEmployees);

        return errors;
    }

    private boolean insertData(SpeakapEmployee speakapEmployee, ConsultantEmployee consultantEmployee, List<Project> projects) {
        QProjectsdump qProjectsdump = new QProjectsdump("p");

        return true;
    }

    public Employee createEmployeeObject(SpeakapEmployee speakapEmployee, Object consultantInternalEmployee, List<Project> projects) throws EIDFormatIncorrectException {
        if (consultantInternalEmployee instanceof ConsultantEmployee) {
            ConsultantEmployee consultantEmployee = (ConsultantEmployee) consultantInternalEmployee;
            Project mainProject = getMainProject(projects);
            return new Employee(consultantEmployee.getBirthday(), speakapEmployee.getFirstname(), speakapEmployee.getLastname(),
                    consultantEmployee.getEmail(), speakapEmployee.getProfilePicURL(), consultantEmployee.getMobile(), mainProject.getBranch(), mainProject.getCostumer(),
                    mainProject.getBranch(), 0, new EID(speakapEmployee.getEid()), projects);
        } else if (consultantInternalEmployee instanceof InternalEmployee) {
            InternalEmployee internalEmployee = (InternalEmployee) consultantInternalEmployee;
            return new Employee(speakapEmployee.getBirthday(), speakapEmployee.getFirstname(), speakapEmployee.getLastname(), internalEmployee.geteMail(), speakapEmployee.getProfilePicURL(),
                    internalEmployee.getMobile(), internalEmployee.getTeam(), "USG Professionals", internalEmployee.getFunction(), 1, new EID(speakapEmployee.getEid()), null);
        } else {
            return null;
        }
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
        List<Project> projects = dbController.getProjects(idnr);
        UniqueList<Project> uniqueProjects = new UniqueList<>();
        projects.forEach(uniqueProjects::addProject);
        return uniqueProjects;
    }

    private void insertEmployees(ArrayList<String> errors, ArrayList<Employee> employees){

    }
}
