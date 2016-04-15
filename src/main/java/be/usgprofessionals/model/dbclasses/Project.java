package be.usgprofessionals.model.dbclasses;

import lombok.Data;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class Project {
    private String branch;
    private String costumer;
    private String empId;
    private String enddate;
    private String startdate;

    public Project(String branch, String costumer, String empId, String enddate, String startdate) {
        this.branch = branch;
        this.costumer = costumer;
        this.empId = empId;
        this.enddate = enddate;
        this.startdate = startdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (branch != null ? !branch.equals(project.branch) : project.branch != null) return false;
        if (costumer != null ? !costumer.equals(project.costumer) : project.costumer != null) return false;
        return !(empId != null ? !empId.equals(project.empId) : project.empId != null);
    }

    @Override
    public int hashCode() {
        int result = branch != null ? branch.hashCode() : 0;
        result = 31 * result + (costumer != null ? costumer.hashCode() : 0);
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        return result;
    }
}

