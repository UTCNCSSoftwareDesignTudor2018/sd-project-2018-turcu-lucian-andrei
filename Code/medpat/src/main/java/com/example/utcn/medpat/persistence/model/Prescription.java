package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Long medicId;

    @OneToOne(fetch = FetchType.EAGER)
    private Long patientId;

    @OneToMany
    private List<Medication> medications;

    private String creationDate;

    public Prescription(Long medicId, Long patientId) {
        this.medicId = medicId;
        this.patientId = patientId;
        this.creationDate = "";
        this.medications = new ArrayList<>();
    }

    public Prescription() {};

    public Long getId() {
        return id;
    }

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }
}
