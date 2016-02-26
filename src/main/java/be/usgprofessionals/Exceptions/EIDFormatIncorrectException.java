package be.usgprofessionals.Exceptions;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class EIDFormatIncorrectException extends Exception {

    public EIDFormatIncorrectException(String EID) {
        super("Error with the following EID: " + EID);
    }
}
