package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

enum Specialization {

}
@Entity
@Table(name="medic")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    private String name;
    private String workAddress;
    private Specialization specialization;

    public Medic(String name, String workAddress, Specialization specialization) {
        this.name = name;
        this.workAddress = workAddress;
        this.specialization = specialization;
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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
