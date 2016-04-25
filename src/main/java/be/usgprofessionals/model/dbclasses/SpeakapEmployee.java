package be.usgprofessionals.model.dbclasses;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
//POJO that contains the data from an employee found in Speakap.
@XmlRootElement
@Data
public class SpeakapEmployee {

    private String birthday;
    private String eid;
    private String email;
    private String firstname;
    private String lastname;
    private String profilePicURL;
    private String tel;

    public SpeakapEmployee(String eid, String email, String firstname, String lastname, String profilePicURL, String tel, String birthday) {
        this.eid = eid;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePicURL = profilePicURL;
        this.tel = tel;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "SpeakapEmployee{" +
                "eid='" + eid + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profilePicURL='" + profilePicURL + '\'' +
                ", tel='" + tel + '\'' +
                ", birthday='" + birthday + "'" +
                '}';
    }
}
