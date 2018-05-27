package com.utcn.medpat.model;

import java.io.Serializable;

/**
 * Created by Lucian on 5/20/2018.
 */

public class Medic implements Serializable{
    private Long id;
    private String name;
    private String workAddress;
    private String specialization;

    public Medic(String name, String workAddress, String specialization) {
        this.name = name;
        this.workAddress = workAddress;
        this.specialization = specialization;
    }

    public Medic() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return name;
    }

}
