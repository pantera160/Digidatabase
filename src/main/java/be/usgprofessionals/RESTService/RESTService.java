package be.usgprofessionals.RESTService;

import be.usgprofessionals.controller.DataController;
import be.usgprofessionals.model.dbclasses.Department;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
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
    public void createNewDepartment(Department department) {
        dataController.newDepartment(department);
    }

    @RequestMapping("/deletedepartment")
    public void deleteDepartment(String id) {
        dataController.deleteDepartment(id);
    }

    @RequestMapping("/employees")
    public void getAllEmployees() {

    }

    @RequestMapping("/speakaps")
    public ArrayList<SpeakapEmployee> getAllSpeakaps() {
        return dataController.getAllSpeakaps();
    }

    @RequestMapping("/newspeakap")
    public void createNewSpeakap(SpeakapEmployee e) {
        dataController.newSpeakap(e);
    }

    @RequestMapping("/deletespeakap")
    public void deleteSpeakap(String id) {
        dataController.deleteSpeakap(id);
    }
}
