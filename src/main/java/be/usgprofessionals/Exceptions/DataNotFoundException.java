package be.usgprofessionals.Exceptions;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String errormsg) {
        super(errormsg);
    }
}
