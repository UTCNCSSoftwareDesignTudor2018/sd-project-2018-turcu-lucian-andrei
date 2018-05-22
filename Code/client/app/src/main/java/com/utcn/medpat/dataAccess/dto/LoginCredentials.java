package com.utcn.medpat.dataAccess.dto;

/**
 * Created by Lucian on 5/21/2018.
 */


public class LoginCredentials {
    private String username;
    private String password;

    private LoginCredentials(final Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

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

    public static class Builder {
        private String username;
        private String password;

        public Builder() {
        }

        public Builder setUsername(final String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public LoginCredentials create() {
            return new LoginCredentials(this);
        }
    }
}