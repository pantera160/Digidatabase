package be.usgprofessionals.model.dbclasses;

import be.usgprofessionals.model.EID;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class Department {
    private String dept_name;
    private int dept_id;
    private EID reportsTo;

    public Department(String dept_name, int dept_id, EID reportsTo) {
        this.dept_name = dept_name;
        this.dept_id = dept_id;
        this.reportsTo = reportsTo;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public EID getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(EID reportsTo) {
        this.reportsTo = reportsTo;
    }
}
