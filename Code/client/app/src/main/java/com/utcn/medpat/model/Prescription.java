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

    public Prescription(final Builder builder) {
        this.id = builder.id;
        this.medic = builder.medic;
        this.patient = builder.patient;
        this.creationDate = builder.creationDate;
        this.medications = builder.medications;
        this.disease = builder.disease;
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

    static class Builder {
        private Long id;
        private Medic medic;
        private Patient patient;
        private List<Medication> medications;
        private String creationDate;
        private String disease;

        public Builder setMedic(Medic medic) {
            this.medic = medic;
            return this;
        }

        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder setMedications(List<Medication> medications) {
            this.medications = medications;
            return this;
        }

        public Builder setDisease(String disease) {
            this.disease = disease;
            return this;
        }

        public Prescription createPrescription() {
            return new Prescription(this);
        }
    }
}
