package be.usgprofessionals.RESTService;

import be.usgprofessionals.controller.DataController;
import be.usgprofessionals.model.dbclasses.Department;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import be.usgprofessionals.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 08/03/16.
 * <p>
 * Digidatabase for USG Professionals
 */
@RestController
@RequestMapping("/dbservice")
public class RESTService {

    @Autowired
    private DataController dataController;


    public RESTService() {
        if (dataController == null) {
            dataController = new DataController();
        }
    }

    @RequestMapping("/run")
    public ArrayList<String> runService() {
        return dataController.run();
    }

    @RequestMapping("/")
    public String test() {
        return "test succesfull. Connection to REST succeeded";
    }

    @RequestMapping("/departments")
    public ArrayList<Department> getAllDepartments() {
        return dataController.getAllDepartments();
    }

    @RequestMapping("/newdepartment")
    public boolean createNewDepartment(Department department) {
        return dataController.newDepartment(department);
    }

    @RequestMapping("/deletedepartment")
    public boolean deleteDepartment(String id) {
        return dataController.deleteDepartment(id);
    }

    @RequestMapping("/employees")
    public void getAllEmployees() {

    }

    @RequestMapping("/newemployee")
    public boolean createNewEmployee(Employee employee) {
        return false;
    }

    @RequestMapping("/speakaps")
    public ArrayList<SpeakapEmployee> getAllSpeakaps() {
        return dataController.getAllSpeakaps();
    }

    @RequestMapping("/newspeakap")
    public boolean createNewSpeakap(SpeakapEmployee e) {
        return dataController.newSpeakap(e);
    }

    @RequestMapping("/deletespeakap")
    public boolean deleteSpeakap(String id) {
        return dataController.deleteSpeakap(id);
    }
}
