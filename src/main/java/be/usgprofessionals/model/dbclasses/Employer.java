package be.usgprofessionals.model.dbclasses;

import lombok.Data;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class Employer {
    private int employer_id;
    private String employer_name;
    private boolean new_employer;

    public Employer(int employer_id, String employer_name, boolean new_employer) {
        this.employer_id = employer_id;
        this.employer_name = employer_name;
        this.new_employer = new_employer;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "employer_id=" + employer_id +
                ", employer_name='" + employer_name + '\'' +
                ", new_employer=" + new_employer +
                '}';
    }
}
