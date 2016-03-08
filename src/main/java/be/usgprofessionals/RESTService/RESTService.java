package be.usgprofessionals.RESTService;

import be.usgprofessionals.controller.DataController;
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
}
