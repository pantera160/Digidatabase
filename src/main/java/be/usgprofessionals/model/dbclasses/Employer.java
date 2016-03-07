package be.usgprofessionals.model.dbclasses;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class Employer {
    private int employer_id;
    private String employer_name;
    private boolean new_employer;

    public Employer(int employer_id, String employer_name, boolean new_employer) {
        this.employer_id = employer_id;
        this.employer_name = employer_name;
        this.new_employer = new_employer;
    }

    public int getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(int employer_id) {
        this.employer_id = employer_id;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public boolean isNew_employer() {
        return new_employer;
    }

    public void setNew_employer(boolean new_employer) {
        this.new_employer = new_employer;
    }
}
