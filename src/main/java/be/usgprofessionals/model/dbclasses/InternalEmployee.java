package be.usgprofessionals.model.dbclasses;

/**
 * Created by Thomas Straetmans on 25/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class InternalEmployee {

    public String eMail;
    public String firstName;
    public String fix;
    public String function;
    public String level0;
    public String level1;
    public String level2;
    public String level3;
    public String mobile;
    public String name;
    public String office;
    public String team;
    public String next_dept;

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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getLevel0() {
        return level0;
    }

    public void setLevel0(String level0) {
        this.level0 = level0;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
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

    public String getNext_dept() {
        return next_dept;
    }

    public void setNext_dept(String next_dept) {
        this.next_dept = next_dept;
    }
}
