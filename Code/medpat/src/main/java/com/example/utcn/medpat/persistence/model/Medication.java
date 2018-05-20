package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    private String name;
    private String manufacturer;
    private String description;

    public Medication(String name, String manufacturer, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public Medication() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
