package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.ConsultantEmployee;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import lombok.Data;

/**
 * Created by Thomas Straetmans on 22/04/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class SpeakapConsultant implements SpeakapDBObject {
    private SpeakapEmployee speakapEmployee;
    private ConsultantEmployee consultantEmployee;
}
