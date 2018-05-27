package com.utcn.medpat.model;

import java.io.Serializable;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Medication implements Serializable{
    private Long id;
    private String name;
    private String manufacturer;
    private String description;

    public Medication(String name, String manufacturer, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public Medication(Long id, String name, String manufacturer, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
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

    @Override
    public String toString() {
        return name+", "+manufacturer;
    }
}
