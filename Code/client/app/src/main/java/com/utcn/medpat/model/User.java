package com.utcn.medpat.model;

import java.io.Serializable;

/**
 * Created by Lucian on 5/20/2018.
 */

public class User implements Serializable {
    private String username;
    private String password;
    private String userType;
    private Long personId;

    public User(String username, String password, String userType, Long personId) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.personId = personId;
    }

    public User() {};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String toString() {
        return this.getUsername();
    }
}
