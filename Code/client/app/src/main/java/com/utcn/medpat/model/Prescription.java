package com.utcn.medpat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Prescription {

    private Long id;
    private Medic medic;
    private Patient patient;
    private List<Medication> medications;
    private String creationDate;
    private String disease;

    public Prescription(Medic medic, Patient patient, String disease) {
        this.medic = medic;
        this.patient = patient;
        this.disease = disease;
        this.creationDate = "";
        this.medications = new ArrayList<>();
    }

    public Prescription(Long id, Medic medic, Patient patient, String creationDate, List<Medication> medications) {
        this.id = id;
        this.medic = medic;
        this.patient = patient;
        this.creationDate = creationDate;
        this.medications = medications;
    }

    public Prescription() {
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

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

}
