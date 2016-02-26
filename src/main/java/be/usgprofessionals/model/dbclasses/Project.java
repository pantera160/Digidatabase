package be.usgprofessionals.model.dbclasses;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class Project {
    public String branch;
    public String costumer;
    public String empId;
    public String enddate;
    public String startdate;

    public Project(String branch, String costumer, String empId, String enddate, String startdate) {
        this.branch = branch;
        this.costumer = costumer;
        this.empId = empId;
        this.enddate = enddate;
        this.startdate = startdate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCostumer() {
        return costumer;
    }

    public void setCostumer(String costumer) {
        this.costumer = costumer;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "branch='" + branch + '\'' +
                ", costumer='" + costumer + '\'' +
                ", empId='" + empId + '\'' +
                ", enddate='" + enddate + '\'' +
                ", startdate='" + startdate + '\'' +
                '}';
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

