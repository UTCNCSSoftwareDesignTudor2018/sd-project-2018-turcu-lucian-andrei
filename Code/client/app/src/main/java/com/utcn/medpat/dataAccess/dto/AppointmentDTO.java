package com.utcn.medpat.dataAccess.dto;

import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;

/**
 * Created by Lucian on 5/23/2018.
 */

public class AppointmentDTO {

    private Long medicId;
    private Long patientId;
    private String location;
    private String date;

    public Long getMedicId() {
        return medicId;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AppointmentDTO(Long medicId, Long patientId, String location, String date) {
        this.medicId = medicId;
        this.patientId = patientId;
        this.location = location;
        this.date = date;
    }
}
