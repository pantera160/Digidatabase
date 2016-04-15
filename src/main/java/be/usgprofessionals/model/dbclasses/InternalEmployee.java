package be.usgprofessionals.model.dbclasses;

import lombok.Data;

/**
 * Created by Thomas Straetmans on 25/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class InternalEmployee {

    private String eMail;
    private String firstName;
    private String fix;
    private String function;
    private String level0;
    private String level1;
    private String level2;
    private String level3;
    private String mobile;
    private String name;
    private String office;
    private String team;
    private String next_dept;

    public InternalEmployee(String eMail, String firstName, String fix, String function, String level0, String level1, String level2,
                            String level3, String mobile, String name, String office, String team) {
        this.eMail = eMail;
        this.firstName = firstName;
        this.fix = fix;
        this.function = function;
        this.level0 = level0;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.mobile = mobile;
        this.name = name;
        this.office = office;
        this.team = team;
    }

    @Override
    public String toString() {
        return "InternalEmployee{" +
                "eMail='" + eMail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fix='" + fix + '\'' +
                ", function='" + function + '\'' +
                ", level0='" + level0 + '\'' +
                ", level1='" + level1 + '\'' +
                ", level2='" + level2 + '\'' +
                ", level3='" + level3 + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", office='" + office + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
