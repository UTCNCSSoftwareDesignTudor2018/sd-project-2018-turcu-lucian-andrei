package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Appointment {

    private Long id;
    private Medic medic;
    private Patient patient;
    private String location;
    private String date;

    public Appointment(Long id, Patient patient, Medic medic, String location) {
        this.id = id;
        this.patient = patient;
        this.medic = medic;
        this.location = location;
        this.date = "";
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
}
