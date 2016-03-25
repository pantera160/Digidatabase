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
        PreparedStatement stmt = connection.prepareStatement("insert into departments(dept_name, manager_id) values(?,?)");
        stmt.setString(1, department.getDept_name());
        stmt.setString(2, department.getReportsTo().toString());
        stmt.executeUpdate();
    }

    public void deleteDepartment(String id) throws SQLException {
        createConnectionQuery();
        PreparedStatement stmt = connection.prepareStatement("DELETE from departments where dept_id = ?");
        stmt.setInt(1, Integer.parseInt(id));
        stmt.executeUpdate();
    }

    public ArrayList<Employee> getAllEmployees() throws SQLException {
        createConnectionQuery();
        ArrayList<Employee> employees = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT userId, firstName, lastName, profilePicURI, uniqueProperty, project_id, employer_id, intern, dept_id, reportsTo, nextDept_id, birthday, email, tel " +
                "from users");
        while (result.next()) {
            int employer_id = result.getInt("EMPLOYER_ID");
            String employer_name;
            ResultSet emp_id_set = stmt.executeQuery("select name from employer where employer_id =" + employer_id);
            if (emp_id_set.next()) {
                employer_name = emp_id_set.getString("NAME");
            } else {
                System.out.println("no employers found for id: " + employer_id);
                continue;
            }
            int dept_id = result.getInt("DEPT_ID");
            String dept_name;
            ResultSet dept_id_set = stmt.executeQuery("SELECT dept_name from departments where dept_id = " + dept_id);
            if (dept_id_set.next()) {
                dept_name = dept_id_set.getString("DEPT_NAME");
            } else {
                System.out.println("No dept found for id: " + dept_id);
                continue;
            }
            int next_dept_id = result.getInt("NEXTDEPT_ID");
            String next_dept_name;
            if (!result.wasNull()) {
                ResultSet next_dept_id_set = stmt.executeQuery("select dept_name from departments where dept_id = " + next_dept_id);
                if (next_dept_id_set.next()) {
                    next_dept_name = next_dept_id_set.getString("DEPT_NAME");
                } else {
                    System.out.println("No dept found for id:" + next_dept_id);
                    continue;
                }
            } else {
                next_dept_name = "NULL";
            }
            int project_id = result.getInt("PROJECT_ID");
            String project_name;
            ResultSet project_id_set = stmt.executeQuery("select name from project where project_id =" + project_id);
            if (project_id_set.next()) {
                project_name = project_id_set.getString("NAME");
            } else {
                System.out.println("No project found with id: " + project_id);
                continue;
            }
            java.util.Date birthday = new Date(result.getDate("BIRTHDAY").getTime());
            try {
                employees.add(new Employee(birthday, result.getString("FIRSTNAME"), result.getString("LASTNAME"), result.getString("EMAIL"), result.getString("PROFILEPICURI"), result.getString("TEL"),
                        dept_name, next_dept_name, employer_name, project_name, result.getInt("INTERN"), new EID(result.getString("USERID")), null));
            } catch (EIDFormatIncorrectException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public void createNewEmployee(Employee employee) throws SQLException {
        createConnectionQuery();
        Statement stmt = connection.createStatement();
        int project_id;
        ResultSet result_project_id = stmt.executeQuery("select project_id from project where name = " + employee.getProject_name());
        if (result_project_id.next()) {
            project_id = result_project_id.getInt("PROJECT_ID");
        } else {
            project_id = createNew(employee.getProject_name(), "insert into project(name) values(?)");
        }
        Department dept;
        ResultSet result_dept_id = stmt.executeQuery("select dept_id, manager_id from departments where dept_name = " + employee.getDept_name());
        if (result_dept_id.next()) {
            try {
                dept = new Department(employee.getDept_name(), result_dept_id.getInt("DEPT_ID"), new EID(result_dept_id.getString("MANAGER_ID")));
            } catch (EIDFormatIncorrectException e) {
                throw new SQLException("EID from given department manager is invalid, please check the departments tab on admin page");
            }
        } else {
            throw new SQLException("Department does not exist. Please check the given name or add the department through admin page");
        }
        int next_dept_id;
        if (employee.getNext_dept_name() == "") {
            next_dept_id = 0;
        } else {
            ResultSet result_next_dept_id = stmt.executeQuery("select dept_id from departments where dept_name = " + employee.getNext_dept_name());
            if (result_next_dept_id.next()) {
                next_dept_id = result_next_dept_id.getInt("DEPT_ID");
            } else {
                throw new SQLException("Department does not exist. Please check the given name or add the department through admin page");
            }
        }
        int employer_id;
        ResultSet result_employer_id = stmt.executeQuery("select employer_id from employer where name = " + employee.getEmployer_name());
        if (result_employer_id.next()) {
            employer_id = result_employer_id.getInt("EMPLOYER_ID");
        } else {
            employer_id = createNew(employee.getEmployer_name(), "insert into employer(name) values(?)");
        }
        PreparedStatement prepstmt = connection.prepareStatement("insert into users(userId, firstName, lastName, profilePicURI, uniqueProperty, project_id, employer_id, intern, dept_id, reportsTo, nextDept_id, birthday, email, tel)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        prepstmt.setString(1, employee.getEid().toString());
        prepstmt.setString(2, employee.getFirst_name());
        prepstmt.setString(3, employee.getLast_name());
        prepstmt.setString(4, employee.getProfilePicURI());
        prepstmt.setString(5, null);
        prepstmt.setInt(6, project_id);
        prepstmt.setInt(7, employer_id);
        prepstmt.setInt(8, employee.getIntern());
        prepstmt.setInt(9, dept.getDept_id());
        prepstmt.setString(10, dept.getReportsTo().toString());
        if (next_dept_id != 0) {
            prepstmt.setInt(11, next_dept_id);
        } else {
            prepstmt.setNull(11, Types.INTEGER);
        }
        prepstmt.setDate(12, new java.sql.Date(employee.getBirthday().getTime()));
        prepstmt.setString(13, employee.getEmail());
        prepstmt.setString(14, employee.getTel());

        int affectedRows = prepstmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Insert new employee failed, no rows were affected");
        }
    }

    public void deleteEmployee(String eid) {

    }

    private int createNew(String name, String query) throws SQLException {
        PreparedStatement prepstmt = connection.prepareStatement("query", Statement.RETURN_GENERATED_KEYS);
        prepstmt.setString(1, name);

        int affectedRows = prepstmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Insert failed, no rows affected");
        }
        ResultSet generatedKeys = prepstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("No id got returned from project insert");
        }
    }
}
