package be.usgprofessionals.model.dbclasses;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class ConsultantEmployee {
    private String birthday;

    private String branch;

    private String email;

    private String fix;

    private String fullname;

    private double hours;

    private String idnr;

    private String mobile;

    public ConsultantEmployee(String birthday, String branch, String email, String fix, String fullname, double hours, String idnr, String mobile) {
        this.birthday = birthday;
        this.branch = branch;
        this.email = email;
        this.fix = fix;
        this.fullname = fullname;
        this.hours = hours;
        this.idnr = idnr;
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getIdnr() {
        return idnr;
    }

    public void setIdnr(String idnr) {
        this.idnr = idnr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "ConsultantEmployee{" +
                "birthday='" + birthday + '\'' +
                ", branch='" + branch + '\'' +
                ", email='" + email + '\'' +
                ", fix='" + fix + '\'' +
                ", fullname='" + fullname + '\'' +
                ", hours=" + hours +
                ", idnr=" + idnr +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
