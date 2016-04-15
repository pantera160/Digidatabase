package be.usgprofessionals.controller;

import be.usgprofessionals.Exceptions.DataMergeException;
import be.usgprofessionals.Exceptions.DataNotFoundException;
import be.usgprofessionals.model.dbclasses.ConsultantEmployee;
import be.usgprofessionals.model.dbclasses.Project;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 18/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class DatadumpDBControllerTest {
    DatadumpDBController datadumpDbController;

    @Before
    public void setUp() {
        datadumpDbController = new DatadumpDBController();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<SpeakapEmployee> list = datadumpDbController.getAllEmployees();
        System.out.println(list.size());
        System.out.println(list.get(list.size() / 2));
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetConsultant() throws Exception {
        ConsultantEmployee consultantEmployee = datadumpDbController.getConsultant("Straetmans Thomas");
        System.out.println(consultantEmployee);
        assertTrue(consultantEmployee.getIdnr().equals("3456739"));
    }

    @Test(expected = DataMergeException.class)
    public void testGetConsultantMultipleWithSameNameExists() throws Exception {
        ConsultantEmployee consultantEmployee = datadumpDbController.getConsultant("Vliegen Lize");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void correctMessageThrownWhenMultipleWithSameNameExist() throws DataNotFoundException, DataMergeException {
        expectedException.expect(DataMergeException.class);
        expectedException.expectMessage("Multiple consultants with the same name have been found. Please add these consultants manually. Consultants name: Vliegen Lize");
        ConsultantEmployee consultantEmployee = datadumpDbController.getConsultant("Vliegen Lize");
    }

    @Test(expected = DataNotFoundException.class)
    public void testGetConsultantRequestedNameDoesNotExist() throws Exception {
        ConsultantEmployee consultantEmployee = datadumpDbController.getConsultant("Willy Wonka");
    }

    @Test
    public void correctMessageThrownWhenNoneWithThisNameExist() throws DataNotFoundException, DataMergeException {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("No consultant with the following name has been found in consultantsdump, if persists in internaldump check speakap database. Name: Willy Wonka");
        ConsultantEmployee consultantEmployee = datadumpDbController.getConsultant("Willy Wonka");
    }

    @Test
    public void testGetProjects() throws DataNotFoundException, DataMergeException {
        List<Project> projects = datadumpDbController.getProjects("1681302");
        System.out.println(projects.size());
        System.out.println(projects.get(projects.size() / 2));
        assertTrue(projects.size() > 0);
    }

    @Test
    public void dataMergeExceptionThownWhenNonExistingIdIsRequested() throws Exception {
        expectedException.expect(DataMergeException.class);
        expectedException.expectMessage("o project has been found for the employee by number: 000002");
        List<Project> projects = datadumpDbController.getProjects("000002");
    }

    @Test
    public void testTimeToRequestAllConsultants() throws Exception {
        long starttime = System.nanoTime();
        List<SpeakapEmployee> speakaps = datadumpDbController.getAllEmployees();
        ArrayList<ConsultantEmployee> consultants = new ArrayList<>();
        speakaps.forEach(employee -> {
            try {
                consultants.add(datadumpDbController.getConsultant(employee.getLastname() + " " + employee.getFirstname()));
            } catch (DataMergeException | DataNotFoundException e) {
                e.printStackTrace();
            }
        });
        long endtime = System.nanoTime();
        long duration = (endtime - starttime) / 1000000;
        System.out.println("function durration: " + duration);
    }
}