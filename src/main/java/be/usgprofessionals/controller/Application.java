package be.usgprofessionals.controller;

import be.usgprofessionals.RESTService.RESTService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Thomas Straetmans on 08/03/16.
 * <p>
 * Digidatabase for USG Professionals
 */

@SpringBootApplication
@ComponentScan("be.usgprofessionals")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
