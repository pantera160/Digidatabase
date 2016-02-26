package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.Project;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas Straetmans on 23/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class ProjectTest {

    @Test
    public void testEqualsReturnsTrueOnSameObject() throws Exception {
        Project p = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2008");
        assertTrue(p.equals(p));
    }

    @Test
    public void testEqualsReturnsTrueOnSameEmpIDCostumerBranchWithDiffOthers() {
        Project p = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2008");
        Project q = new Project("ICT - General", "USG", "123456", "Feb 1, 2009", "Mar 23, 2008");
        assertTrue(p.equals(q));
    }

    @Test
    public void testEqualsReturnFalseWhenDifferentProjects() {
        Project p = new Project("ICT - General", "USG", "123456", "Jan 1, 2009", "Apr 23, 2008");
        Project q = new Project("ICT - Development", "USG", "564789", "Jan 1, 2009", "Apr 20, 2008");
        assertFalse(p.equals(q));
    }
}