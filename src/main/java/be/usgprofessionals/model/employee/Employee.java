package be.usgprofessionals.model.employee;

import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.dbclasses.Project;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class Employee {

    private String first_name, last_name, email, profilePicURI, tel, dept_name, next_dept_name, employer_name, project_name;
    private int intern;
    private EID eid;
    private List<Project> projects;
    private Date birthday;

    public Employee(Date birthday, String first_name, String last_name, String email, String profilePicURI, String tel,
                    String dept_name, String next_dept_name, String employer_name, String project_name, int intern, EID eid, List<Project> projects) {
        this.birthday = birthday;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.profilePicURI = profilePicURI;
        this.tel = tel;
        this.dept_name = dept_name;
        this.employer_name = employer_name;
        this.project_name = project_name;
        this.intern = intern;
        this.eid = eid;
        this.projects = projects;
        this.next_dept_name = next_dept_name;
    }

    @Override
    public String toString() {
        return "EmployeeExternal{" +
                "birthday='" + birthday + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", profilePicURI='" + profilePicURI + '\'' +
                ", tel='" + tel + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", next_dept_name='" + next_dept_name + "'" +
                ", employer_name='" + employer_name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", intern=" + intern +
                ", eid=" + eid +
                ", projects=" + projects +
                '}';
    }
}
