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
        if (this.contains(e)) {
            return false;
        } else {
            return super.add(e);
        }
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
        if (this.contains(element)) {
            return;
        } else {
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
                    e.printStackTrace();
                    //TODO in case of error, show nice msg so they know what and how to fix
                }

                return false;
            } else {
                return add(element);
            }
        }
        return false;
    }
}
