package be.usgprofessionals.model.dbclasses;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
//POJO that contains the data from an employee found in Speakap.
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

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
