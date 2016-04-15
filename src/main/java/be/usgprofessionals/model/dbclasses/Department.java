package be.usgprofessionals.model.dbclasses;

import be.usgprofessionals.model.EID;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@XmlRootElement
@Data
public class Department {
    private String dept_name;
    private int dept_id;
    private EID reportsTo;

    public Department(String dept_name, int dept_id, EID reportsTo) {
        this.dept_name = dept_name;
        this.dept_id = dept_id;
        this.reportsTo = reportsTo;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dept_name='" + dept_name + '\'' +
                ", dept_id=" + dept_id +
                ", reportsTo=" + reportsTo +
                '}';
    }
}
