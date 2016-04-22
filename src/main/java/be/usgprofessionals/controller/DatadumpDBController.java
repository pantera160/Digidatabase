package be.usgprofessionals.controller;

import be.usgprofessionals.Exceptions.DataMergeException;
import be.usgprofessionals.Exceptions.DataNotFoundException;
import be.usgprofessionals.QConsultantdump;
import be.usgprofessionals.QInternaldump;
import be.usgprofessionals.QProjectsdump;
import be.usgprofessionals.QSpeakapdump;
import be.usgprofessionals.model.SpeakapDBObject;
import be.usgprofessionals.model.dbclasses.ConsultantEmployee;
import be.usgprofessionals.model.dbclasses.InternalEmployee;
import be.usgprofessionals.model.dbclasses.Project;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Thomas Straetmans on 15/02/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Component
public class DatadumpDBController extends DBController {

    public DatadumpDBController() {
        super("DBURL");
    }

    public List<SpeakapEmployee> getAllEmployees() {
        List<SpeakapEmployee> speakapEmployees = new ArrayList<>();
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(qSpeakapdump)
                .list(qSpeakapdump.eid, qSpeakapdump.profilePicURL, qSpeakapdump.firstname, qSpeakapdump.lastname, qSpeakapdump.birthday);
        // results.forEach(employee -> speakapEmployees.add(new SpeakapEmployee(employee.get(qSpeakapdump.eid), employee.get(qSpeakapdump.email), employee.get(qSpeakapdump.firstname),
        //        employee.get(qSpeakapdump.lastname), employee.get(qSpeakapdump.profilePicURL), employee.get(qSpeakapdump.tel), employee.get(qSpeakapdump.birthday).toString())));
        //TODO
        speakapEmployees = results.stream().map(DatadumpDBController::convertEmployee).collect(Collectors.toList());

        QConsultantdump qConsultantdump = QConsultantdump.consultantdump;
        QInternaldump qInternaldump = QInternaldump.internaldump;
        SQLQuery q = query.from(qSpeakapdump);
        q.leftJoin(qConsultantdump).on(qConsultantdump.fullname.eq(qSpeakapdump.lastname.concat(" ").concat(qSpeakapdump.firstname)));
        q.leftJoin(qInternaldump).on(qInternaldump.name.eq(qSpeakapdump.lastname).and(qInternaldump.firstName.eq(qSpeakapdump.firstname)));
        q.orderBy(qSpeakapdump.eid.asc());
        List<Tuple> list = q.list(qSpeakapdump, qConsultantdump, qInternaldump);


        return speakapEmployees;
    }

    public static SpeakapEmployee convertEmployee(Tuple employee) {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        return new SpeakapEmployee(employee.get(qSpeakapdump.eid), employee.get(qSpeakapdump.email), employee.get(qSpeakapdump.firstname),
                employee.get(qSpeakapdump.lastname), employee.get(qSpeakapdump.profilePicURL), employee.get(qSpeakapdump.tel), employee.get(qSpeakapdump.birthday).toString());
    }

    public ConsultantEmployee getConsultant(String fullname) throws DataMergeException, DataNotFoundException {
        QConsultantdump qConsultantdump = new QConsultantdump("c");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(qConsultantdump)
                .where(qConsultantdump.fullname.eq(fullname))
                .list(qConsultantdump.birthday, qConsultantdump.branch, qConsultantdump.email, qConsultantdump.mobile, qConsultantdump.idnr, qConsultantdump.fullname);
        if (results.size() > 1) {
            throw new DataMergeException("Multiple consultants with the same name have been found. Please add these consultants manually. Consultants name: " + fullname);
        } else if (results.size() <= 0) {
            throw new DataNotFoundException("No consultant with the following name has been found in consultantsdump, if persists in internaldump check speakap database. Name: " + fullname);
        } else {
            Tuple tuple = results.get(0);
            return new ConsultantEmployee(tuple.get(qConsultantdump.birthday), tuple.get(qConsultantdump.branch), tuple.get(qConsultantdump.email), tuple.get(qConsultantdump.fix),
                    tuple.get(qConsultantdump.fullname), 0.0, tuple.get(qConsultantdump.idnr).toString(), tuple.get(qConsultantdump.mobile));
        }
    }

    public InternalEmployee getInternal(String name, String firstname) throws DataMergeException, DataNotFoundException {
        QInternaldump qInternaldump = new QInternaldump("i");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(qInternaldump)
                .where(qInternaldump.firstName.eq(firstname))
                .where(qInternaldump.name.eq(name))
                .list(qInternaldump.all());
        if (results.size() > 1) {
            throw new DataMergeException("Multiple internals with the same name have been found. Please add these consultants manually. Employee name: " + name + " " + firstname);
        } else if (results.size() <= 0) {
            throw new DataNotFoundException("No internal with the following name has been found in internaldump, check speakap database. Name: " + name + " " + firstname);
        } else {
            Tuple tuple = results.get(0);
            return new InternalEmployee(tuple.get(qInternaldump.eMail), tuple.get(qInternaldump.firstName), tuple.get(qInternaldump.fix), tuple.get(qInternaldump.function),
                    tuple.get(qInternaldump.level0), tuple.get(qInternaldump.level1), tuple.get(qInternaldump.level2), tuple.get(qInternaldump.level3),
                    tuple.get(qInternaldump.mobile), tuple.get(qInternaldump.name), tuple.get(qInternaldump.office), tuple.get(qInternaldump.team));
        }
    }

    public List<Project> getProjects(String idnr) throws DataMergeException {
        QProjectsdump qProjectsdump = new QProjectsdump("p");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(qProjectsdump)
                .where(qProjectsdump.empId.eq(idnr))
                .list(qProjectsdump.branch, qProjectsdump.costumer, qProjectsdump.empId, qProjectsdump.enddate, qProjectsdump.startdate, qProjectsdump.id);
        if (results.size() <= 0) {
            throw new DataMergeException("No project has been found for the employee by number: " + idnr);
        } else {
            List<Project> projects = new ArrayList<>();
            results.forEach(project -> projects.add(new Project(project.get(qProjectsdump.branch), project.get(qProjectsdump.costumer), project.get(qProjectsdump.empId),
                    project.get(qProjectsdump.enddate), project.get(qProjectsdump.startdate))));
            return projects;
        }
    }

    public ArrayList<SpeakapEmployee> getAllSpeakaps() {
        ArrayList<SpeakapEmployee> speakapEmployees = new ArrayList<>();
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        SQLQuery query = createConnectionQuery();
        List<Tuple> results = query
                .from(qSpeakapdump)
                .list(qSpeakapdump.eid, qSpeakapdump.firstname, qSpeakapdump.lastname, qSpeakapdump.birthday, qSpeakapdump.email, qSpeakapdump.profilePicURL, qSpeakapdump.tel);
        results.forEach(t -> speakapEmployees.add(new SpeakapEmployee(t.get(qSpeakapdump.eid), t.get(qSpeakapdump.email), t.get(qSpeakapdump.firstname), t.get(qSpeakapdump.lastname),
                t.get(qSpeakapdump.profilePicURL), t.get(qSpeakapdump.tel), t.get(qSpeakapdump.birthday).toString())));
        return speakapEmployees;
    }

    public void newSpeakap(SpeakapEmployee e) {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        SQLInsertClause query = createInsertClause(qSpeakapdump);
        query.values(e.getEid(), e.getEmail(), e.getFirstname(), e.getLastname(), e.getBirthday(), e.getTel(), e.getProfilePicURL()).execute();
    }

    public void deleteSpeakap(String id) {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        SQLDeleteClause query = createDeleteClause(qSpeakapdump);
        query.where(qSpeakapdump.eid.eq(id)).execute();
    }

    public SpeakapDBObject convertTupleToEmployees(Tuple employee) {
        QSpeakapdump qSpeakapdump = new QSpeakapdump("s");
        QInternaldump qInternaldump = new QInternaldump("i");
        QConsultantdump qConsultantdump = new QConsultantdump("c");
        SpeakapEmployee speakapEmployee = new SpeakapEmployee(employee.get(qSpeakapdump.eid), employee.get(qSpeakapdump.email), employee.get(qSpeakapdump.firstname),
                employee.get(qSpeakapdump.lastname), employee.get(qSpeakapdump.profilePicURL), employee.get(qSpeakapdump.tel), employee.get(qSpeakapdump.birthday).toString());
        InternalEmployee internalEmployee = new InternalEmployee(employee.get(qInternaldump.eMail), employee.get(qInternaldump.firstName), employee.get(qInternaldump.fix),
                employee.get(qInternaldump.function), employee.get(qInternaldump.level0), employee.get(qInternaldump.level1), employee.get(qInternaldump.level2), employee.get(qInternaldump.level3),
                employee.get(qInternaldump.mobile), employee.get(qInternaldump.name), employee.get(qInternaldump.office), employee.get(qInternaldump.team));
        ConsultantEmployee consultantEmployee = new ConsultantEmployee(employee.get(qConsultantdump.birthday), employee.get(qConsultantdump.branch), employee.get(qConsultantdump.email),
                employee.get(qConsultantdump.fix), employee.get(qConsultantdump.fullname), 0.0, employee.get(qConsultantdump.idnr).toString(), employee.get(qConsultantdump.mobile));
        if (internalEmployee.getFirstName() == null) {

        } else if (consultantEmployee.getFullname() == null) {

        } else {

        }
        return null;
    }
}
