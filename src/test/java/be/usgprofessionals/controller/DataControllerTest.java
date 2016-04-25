package be.usgprofessionals.controller;

import be.usgprofessionals.model.UniqueList;
import be.usgprofessionals.model.dbclasses.ConsultantEmployee;
import be.usgprofessionals.model.dbclasses.Department;
import be.usgprofessionals.model.dbclasses.Project;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 23/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@RunWith(MockitoJUnitRunner.class)
public class DataControllerTest {


    @InjectMocks
    DataController dataController = new DataController();
    @Mock
    private DatadumpDBController datadumpDbController;
    @Mock
    private DigigramDBController digigramDBController;

    @Before
    public void setUp() {
        dataController = new DataController();
    }

    @Test
    public void getAllDepartments() throws Exception {
        ArrayList<Department> departementslijst = new ArrayList<>();
        Mockito.when(digigramDBController.getAllDepartments()).thenReturn(departementslijst);

        //    Mockito.when(digigramDBController.getCreateProjectID(Mockito.eq("abc"))).thenThrow(RuntimeException.class);
        //    Mockito.verify(digigramDBController, Mockito.times(1)).insertEmployee();
        //    Mockito.verifyNoMoreInteractions(digigramDBController);

        ArrayList<Department> allDepartments = dataController.getAllDepartments();

        Assertions.assertThat(allDepartments).isSameAs(departementslijst);


    }


    @Test
    public void testGetMainProject() throws Exception {
        UniqueList<Project> projects = new UniqueList<>();
        Project p = new Project("ICT - General", "Foo", "123456", "Jan 10, 2009", "Jun 1, 2008");
        Project q = new Project("ICT - General", "USG", "123456", "Dec 1, 2009", "Apr 1, 2008");
        Project o = new Project("ICT - General", "USG Professionals", "123456", "Jan 1, 2009", "Jun 23, 2008");
        Project m = new Project("ICT - General", "KBC", "123456", "Dec 31, 2008", "Apr 23, 2008");
        Project m2 = new Project("ICT - General", "KBC", "123456", "Dec 1, 2008", "Apr 31, 2008");
        projects.addProject(p);
        projects.addProject(q);
        projects.addProject(o);
        projects.addProject(m);
        projects.addProject(m2);
        assertTrue(projects.size() == 4);
        Project main = dataController.getMainProject(projects);
        assertTrue(main.equals(q));
        Assertions.assertThat(projects).hasSize(4);
        Assertions.assertThat(projects).extracting("costumer").containsOnly("Foo", "USG", "USG Professionals", "KBC");
    }

    @Test
    public void testCreateEmployeeObjectRunsCorrectly() throws Exception {
        UniqueList<Project> projects = new UniqueList<>();
        Project p1 = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2008");
        Project p2 = new Project("ICT - General", "USG", "123456", "Jan 1, 2010", "Apr 23, 2008");
        Project p3 = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2007");
        projects.addProject(p1);
        projects.addProject(p2);
        projects.addProject(p3);
        SpeakapEmployee speakapEmployee = new SpeakapEmployee("12345abcde6789ts", "", "Thomas", "Straetmans", "", "", "");
        ConsultantEmployee consultantEmployee = new ConsultantEmployee("Jan 1, 1991", "ICT - General", "thomasstraetmans@hotmail.com", "", "Straetmans Thomas", 1000, 123456, "");
        System.out.println(dataController.createEmployeeObject(speakapEmployee, consultantEmployee, projects));
    }

    @Test
    public void run() {
        ArrayList<String> errors = dataController.run();
        errors.forEach(System.out::println);
    }
}