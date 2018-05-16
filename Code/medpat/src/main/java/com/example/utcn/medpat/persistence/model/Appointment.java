package com.example.utcn.medpat.persistence.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Long patientId;

    @OneToOne(fetch = FetchType.EAGER)
    private Long doctorId;

    private String location;
    private String date;

    public Appointment(Long patientId, Long doctorId, String location) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.location = location;
        this.date = "";
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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
