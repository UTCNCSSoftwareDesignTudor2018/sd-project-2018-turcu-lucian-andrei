package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Medication {
    private Long id;
    private String name;
    private String manufacturer;
    private String description;

    public Medication(String name, String manufacturer, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public Medication(Long id, String name, String manufacturer, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    private Medication(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.description = builder.description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static class Builder {
        private Long id;
        private String name;
        private String manufacturer;
        private String description;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Medication create() {
            return new Medication(this);
        }
    }
}
