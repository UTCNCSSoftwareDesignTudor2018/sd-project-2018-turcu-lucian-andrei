package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    private String name;
    private String address;

    public Patient(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Patient(Long id, String name, String address) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Patient() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
