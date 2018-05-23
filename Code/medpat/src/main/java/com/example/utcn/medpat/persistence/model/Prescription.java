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
    private Medic medic;

    @OneToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @OneToMany
    private List<Medication> medications;

    private String disease;

    private String creationDate;

    public Prescription(Medic medic, Patient patient, String disease) {
        this.medic = medic;
        this.patient = patient;
        this.disease = disease;
        this.creationDate = "";
        this.medications = new ArrayList<>();
    }

    public Prescription() {};

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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
