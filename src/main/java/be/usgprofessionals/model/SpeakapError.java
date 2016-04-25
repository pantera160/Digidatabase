package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.SpeakapEmployee;
import lombok.Data;

/**
 * Created by Thomas Straetmans on 22/04/2016.
 * <p>
 * Digidatabase for USG Professionals
 */
@Data
public class SpeakapError implements SpeakapDBObject {
    private String errorMsg;
    private SpeakapEmployee speakapEmployee;
}
