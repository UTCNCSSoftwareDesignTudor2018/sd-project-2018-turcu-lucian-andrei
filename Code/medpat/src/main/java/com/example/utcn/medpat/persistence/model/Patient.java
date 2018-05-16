package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="patient")
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    private String name;
    private String address;

    public Patient(User user, String name, String address) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.name = name;
        this.address = address;
    }

    public Patient() {};

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
