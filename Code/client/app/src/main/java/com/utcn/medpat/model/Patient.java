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

    private Patient(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
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

    static class Builder {
        private Long id;
        private String name;
        private String address;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Patient create() {
            return new Patient(this);
        }
    }
}
