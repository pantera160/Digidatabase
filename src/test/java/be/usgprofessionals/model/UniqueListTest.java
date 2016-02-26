package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 23/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class UniqueListTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddProjectWith2DifferentProjectsGetsAddedCorrectly() throws Exception {
        UniqueList<Project> projects = new UniqueList<>();
        Project p = new Project("ICT - General", "USG", "123456", "Apr 1, 2008", "Feb 1, 2009");
        Project q = new Project("ICT - Development", "USG Professionals", "456789", "Apr 10, 2008", "Apr 10, 2009");
        projects.addProject(p);
        projects.addProject(q);
        assertTrue(projects.size() == 2);
        assertTrue(projects.get(0).equals(p));
    }

    @Test
    public void testAddProjectWith2SameProjectsOnly1GetsAdded() {
        Project p = new Project("ICT - General", "USG", "123456", "Apr 1, 2008", "Apr 1, 2009");
        Project q = new Project("ICT - General", "USG", "123456", "Apr 1, 2008", "Apr 1, 2009");
        UniqueList<Project> projects = new UniqueList<>();
        projects.addProject(p);
        projects.addProject(q);
        assertTrue(projects.size() == 1);
    }

    @Test
    public void testAddProjectWith2EqualProjectsWithDifferentDates() {
        Project q = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2008");
        Project p = new Project("ICT - General", "USG", "123456", "Feb 1, 2009", "Apr 1, 2008");
        UniqueList<Project> projects = new UniqueList<>();
        projects.addProject(q);
        projects.addProject(p);
        assertTrue(projects.size() == 1);
        assertEquals(projects.get(0).getStartdate(), p.getStartdate());
        assertEquals(projects.get(0).getEnddate(), p.getEnddate());
    }
}