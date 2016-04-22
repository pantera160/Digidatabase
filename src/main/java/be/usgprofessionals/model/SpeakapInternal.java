package be.usgprofessionals.model;

import be.usgprofessionals.model.dbclasses.InternalEmployee;
import be.usgprofessionals.model.dbclasses.SpeakapEmployee;

/**
 * Created by Thomas Straetmans on 22/04/2016.
 * <p>
 * Digidatabase for USG Professionals
 */

/*
Object to combine a speakap employee with it's corresponding internal employee data.
 */
public class SpeakapInternal implements SpeakapDBObject {
    private SpeakapEmployee speakapEmployee;
    private InternalEmployee internalEmployee;
}
