package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Thomas Straetmans on 22/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
public class UniqueList<E> extends ArrayList<E> {

    @Override
    public boolean add(E e) {
        return !this.contains(e) && super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Collection<E> copy = new ArrayList<E>(collection);
        copy.removeAll(this);
        return super.addAll(copy);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        Collection<E> copy = new ArrayList<E>(collection);
        copy.removeAll(this);
        return super.addAll(index, copy);
    }

    @Override
    public void add(int index, E element) {
        if (!this.contains(element)) {
            super.add(index, element);
        }
    }

    public boolean addProject(E element) {
        if (element instanceof Project) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            Project project = (Project) element;
            int i = this.indexOf(project);
            if (i >= 0) {
                Project project1 = (Project) this.get(i);
                try {
                    if (sdf.parse(project1.getEnddate()).before(sdf.parse(project.getEnddate()))) {
                        project1.setEnddate(project.getEnddate());
                    }
                    if (sdf.parse(project1.getStartdate()).after(sdf.parse(project.getStartdate()))) {
                        project1.setStartdate(project.getStartdate());
                    }
                    this.add(i, (E) project1);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }

                return false;
            } else {
                return add(element);
            }
        }
        return false;
    }
}
