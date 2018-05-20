package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/20/2018.
 */

public class Patient {
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
}
