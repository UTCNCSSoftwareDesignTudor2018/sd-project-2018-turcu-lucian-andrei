package com.example.utcn.medpat.communication.dto;

public class AppointmentDTO {
    private Long patientId;
    private Long medicId;
    private String date;
    private String location;

    public AppointmentDTO(Long patientID, Long medicId, String date, String location) {
        this.patientId = patientID;
        this.medicId = medicId;
        this.date = date;
        this.location = location;
    }

    public Long getPatientID() {
        return patientId;
    }

    public void setPatientID(Long patientID) {
        this.patientId = patientID;
    }

    public Long getMedicId() {
        return medicId;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
