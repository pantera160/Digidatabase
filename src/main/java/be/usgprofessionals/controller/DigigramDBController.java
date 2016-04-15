package be.usgprofessionals.controller;

import be.usgprofessionals.Exceptions.DataNotFoundException;
import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;
import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.dbclasses.Department;
import be.usgprofessionals.model.dbclasses.Employer;
import be.usgprofessionals.model.employee.Employee;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 02/03/16.
 * <p>
 * Digidatabase for USG Professionals
 */
@Component
public class DigigramDBController extends DBController {

    public DigigramDBController() {
        super("DBURL2");
    }

    public Department getDeptId(String department) throws DataNotFoundException, SQLException, EIDFormatIncorrectException {
        Department dept_id;
        if (department.contains("-")) {
            String[] depts = department.split(" - ");
            department = depts[1];
        }
        department = department.replace(" ", "_");
        department = correctWrongDepts(department);
        createConnectionQuery();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select dept_id, dept_name, manager_id from departments where dept_name like '%" + department + "%'");
        if (!result.next()) {
            throw new DataNotFoundException("There has been found no department with the following name:" + department);
        } else {
            dept_id = new Department(result.getString(2), result.getInt(1), new EID(result.getString(3)));
        }
        stmt.close();


        return dept_id;
    }

    protected String correctWrongDepts(String department) {
        if (department.equals("Analyse")) {
            return "Analysis";
        } else if (department.equals("CC_Professional_Services")) {
            return "Professional_Services";
        } else if (department.equals("IT,_PM_Change")) {
            return "ICT,_PM_&_Change";
        } else if (department.equals("Infrastructuur")) {
            return "Infrastructure";
        }
        return department;
    }

    public Employer getCreateEmployerID(String employer_name) throws SQLException {
        createConnectionQuery();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select employer_id from employer where name ='" + employer_name + "'");
        if (!result.next()) {
            int id = stmt.executeUpdate("insert into employers(name, city, adress, iconURI) values('" + employer_name + "', NULL, NULL, NULL)", Statement.RETURN_GENERATED_KEYS);
            return new Employer(id, employer_name, true);
        } else {
            return new Employer(result.getInt(1), employer_name, false);
        }

    }

    public void insertEmployee(Department dept, Department next_dept, int employer_id, Employee employee, int project_id) throws SQLException {
        createConnectionQuery();
        PreparedStatement stmt = connection.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, employee.getEid().getId());
        stmt.setString(2, employee.getFirst_name());
        stmt.setString(3, employee.getLast_name());
        stmt.setString(4, employee.getProfilePicURI());
        stmt.setString(5, null);
        stmt.setInt(6, project_id);
        stmt.setInt(7, employer_id);
        stmt.setInt(8, employee.getIntern());
        stmt.setInt(9, dept.getDept_id());
        stmt.setString(10, dept.getReportsTo().getId());
        if (next_dept.getDept_id() == -1) {
            stmt.setObject(11, null);
        } else {
            stmt.setInt(11, next_dept.getDept_id());
        }
        stmt.setDate(12, new Date(employee.getBirthday().getTime()));
        stmt.setString(13, employee.getEmail());
        stmt.setString(14, employee.getTel());
        stmt.executeUpdate();
    }

    public int getCreateProjectID(String project_name) throws SQLException {
        createConnectionQuery();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select project_id from project where name = '" + project_name + "'");
        if (!result.next()) {
            return stmt.executeUpdate("insert into project(name) values('" + project_name + "')", Statement.RETURN_GENERATED_KEYS);
        } else {
            return result.getInt(1);
        }
    }

    public ArrayList<Department> getAllDepartments() throws SQLException, EIDFormatIncorrectException {
        ArrayList<Department> departments = new ArrayList<>();
        createConnectionQuery();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("select dept_id, dept_name, manager_id from departments");
        while (result.next()) {
            departments.add(new Department(result.getString(2), result.getInt(1), new EID(result.getString(3))));
        }
        return departments;
    }

    public void newDepartment(Department department) throws SQLException {
        createConnectionQuery();
        PreparedStatement stmt = connection.prepareStatement("insert into departments(DEPT_NAME, MANAGER_ID) values(?,?)");
        stmt.setString(1, department.getDept_name());
        stmt.setString(2, department.getReportsTo().toString());
        stmt.executeUpdate();
    }

    public void deleteDepartment(String id) throws SQLException {
        createConnectionQuery();
        PreparedStatement stmt = connection.prepareStatement(("DELETE from departments where dept_id = ?"));
        stmt.setInt(1, Integer.parseInt(id));
        stmt.executeUpdate();
    }
}
