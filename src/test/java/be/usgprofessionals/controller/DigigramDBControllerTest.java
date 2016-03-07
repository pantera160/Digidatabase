package be.usgprofessionals.controller;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Thomas Straetmans on 07/03/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class DigigramDBControllerTest {

    DigigramDBController digigramDBController;
    ArrayList<String> depts = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        digigramDBController = new DigigramDBController();
        depts.add("Sales - Services & Industry");
        depts.add("CC Select");
        depts.add("CC Infrastructure");
        depts.add("CC Professional Services");
        depts.add("ICT - Business Intelligence");
        depts.add("ICT - Analyse");
    }

    @Test
    public void testGetDeptId() throws Exception {
        depts.forEach(dept -> {
            try {
                System.out.println(digigramDBController.getDeptId(dept).getDept_id());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}