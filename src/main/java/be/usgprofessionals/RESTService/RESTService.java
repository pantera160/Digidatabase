package be.usgprofessionals.RESTService;

import be.usgprofessionals.controller.DataController;
import be.usgprofessionals.model.dbclasses.Department;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import be.usgprofessionals.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 08/03/16.
 * <p>
 * Digidatabase for USG Professionals
 */
@RestController
@RequestMapping("/dbservice")
@ComponentScan("be.usgprofessionals.controller")
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

    @RequestMapping(value = "/newdepartment", method = RequestMethod.POST)
    public void createNewDepartment(@RequestBody Department department) {
        dataController.newDepartment(department);
    }

    @RequestMapping(value = "/deletedepartment", method = RequestMethod.DELETE)
    public void deleteDepartment(@RequestBody String id) {
        dataController.deleteDepartment(id);
    }

    @RequestMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        return dataController.getAllEmployees();
    }

    @RequestMapping(value = "/deleteemployee", method = RequestMethod.DELETE)
    public void deleteEmployee(@RequestBody String eid) {
        dataController.deleteEmployee(eid);
    }

    @RequestMapping(value = "/newemployee", method = RequestMethod.POST)
    public String newEmployee(@RequestBody Employee employee) {
        return dataController.newEmployee(employee);
    }

    @RequestMapping("/speakaps")
    public ArrayList<SpeakapEmployee> getAllSpeakaps() {
        return dataController.getAllSpeakaps();
    }

    @RequestMapping(value = "/newspeakap", method = RequestMethod.POST)
    public void createNewSpeakap(@RequestBody SpeakapEmployee e) {
        dataController.newSpeakap(e);
    }

    @RequestMapping(value = "/deletespeakap", method = RequestMethod.DELETE)
    public void deleteSpeakap(@RequestBody String id) {
        dataController.deleteSpeakap(id);
    }
}
