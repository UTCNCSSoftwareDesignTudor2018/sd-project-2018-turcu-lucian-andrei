package com.utcn.medpat.model;

import java.io.Serializable;

/**
 * Created by Lucian on 5/20/2018.
 */

public class Patient implements Serializable{
    private Long id;
    private String name;
    private String address;

    public Patient(String name, String address) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
