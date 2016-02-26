package be.usgprofessionals.model.employee;

import be.usgprofessionals.model.EID;
import be.usgprofessionals.model.dbclasses.Project;

import java.util.List;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class Employee {

    private String birthday, firstname, lastname, email, profilePicURI, tel, dept_name, employer_name, projectname;
    private int intern;
    private EID eid;
    private List<Project> projects;

    public Employee(String birthday, String firstname, String lastname, String email, String profilePicURI, String tel,
                    String dept_name, String employer_name, String projectname, int intern, EID eid, List<Project> projects) {
        this.birthday = birthday;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.profilePicURI = profilePicURI;
        this.tel = tel;
        this.dept_name = dept_name;
        this.employer_name = employer_name;
        this.projectname = projectname;
        this.intern = intern;
        this.eid = eid;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "EmployeeExternal{" +
                "birthday='" + birthday + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", profilePicURI='" + profilePicURI + '\'' +
                ", tel='" + tel + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", employer_name='" + employer_name + '\'' +
                ", projectname='" + projectname + '\'' +
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
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
}
