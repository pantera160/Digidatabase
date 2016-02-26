package be.usgprofessionals.model;

import be.usgprofessionals.Exceptions.EIDFormatIncorrectException;

/**
 * Created by Thomas Straetmans on 16/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class EID {

    private String id;

    public EID(String id) throws EIDFormatIncorrectException {
        if (id.matches("(.*)[0-9](.*)") && id.matches("(.*)\\p{L}(.*)") && id.length() == 16) {
            this.id = id;
        } else {
            System.out.println("failed eid: " + id);
            throw new EIDFormatIncorrectException(id);
        }
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (o instanceof EID) {
            if (((EID) o).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
