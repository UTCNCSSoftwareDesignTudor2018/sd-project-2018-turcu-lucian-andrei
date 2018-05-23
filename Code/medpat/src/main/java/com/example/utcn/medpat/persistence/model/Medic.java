package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="medic")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
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

    public void setId(Long id) {
        this.id = id;
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

}
