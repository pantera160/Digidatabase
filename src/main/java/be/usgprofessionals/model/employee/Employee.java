package be.usgprofessionals.model.employee;

import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.dbclasses.Project;

import java.util.Date;
import java.util.List;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicURI() {
        return profilePicURI;
    }

    public void setProfilePicURI(String profilePicURI) {
        this.profilePicURI = profilePicURI;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getIntern() {
        return intern;
    }

    public void setIntern(int intern) {
        this.intern = intern;
    }

    public EID getEid() {
        return eid;
    }

    public void setEid(EID eid) {
        this.eid = eid;
    }

    public String getNext_dept_name() {
        return next_dept_name;
    }

    public void setNext_dept_name(String next_dept_name) {
        this.next_dept_name = next_dept_name;
    }
}
