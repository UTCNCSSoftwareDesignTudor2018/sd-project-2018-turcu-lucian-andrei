package com.example.utcn.medpat.persistence.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Medic medic;

    @OneToOne(fetch = FetchType.EAGER)
    private Patient patient;

    private String location;
    private String date;

    public Appointment(Patient patient, Medic medic, String location) {
        this.patient = patient;
        this.medic = medic;
        this.location = location;
        this.date = "";
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
