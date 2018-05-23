package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Appointment {

    private Long id;
    private Medic medic;
    private Patient patient;
    private String location;
    private String date;

    public Appointment(Long id, Patient patient, Medic medic, String location) {
        this.id = id;
        this.patient = patient;
        this.medic = medic;
        this.location = location;
        this.date = "";
    }

    public Appointment(final Builder builder) {
        this.id = builder.id;
        this.patient = builder.patient;
        this.medic = builder.medic;
        this.location = builder.location;
        this.date = builder.date;
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class Builder {
        private Long id;
        private Patient patient;
        private Medic medic;
        private String location;
        private String date;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder setMedic(Medic medic) {
            this.medic = medic;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }
        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Appointment createAppointment() {
            return new Appointment(this);
        }

    }
}
