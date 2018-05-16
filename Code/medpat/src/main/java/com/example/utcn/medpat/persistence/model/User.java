package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;

enum Type {
    MEDIC, PATIENT;
}

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="id", nullable=false)
    private String id;

    private String password;
    private Type userType;
    private Long personId;

    public User(String username, String password, Type userType, Long personId) {
        this.id = username;
        this.password = password;
        this.userType = userType;
        this.personId = personId;
    }

    public User() {};

    public String getUsername() {
        return id;
    }

    public void setUsername(String username) {
        this.id = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Type getUserType() {
        return userType;
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }
}
