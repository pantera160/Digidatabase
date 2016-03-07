package be.usgprofessionals.Exceptions;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class NewIconRequiredException extends Exception {

    public NewIconRequiredException(String employer_name) {
        super("An icon for the company: +" + employer_name + " has to be added manually trough the admin page.");
    }
}
