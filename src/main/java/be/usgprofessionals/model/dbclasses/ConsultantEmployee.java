package be.usgprofessionals.model.dbclasses;

import lombok.Data;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
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
