package com.utcn.medpat.dataAccess.dto;

import java.util.List;

/**
 * Created by Lucian on 5/27/2018.
 */

public class PrescriptionDTO {

    private Long medicId;
    private Long patientId;
    private List<Long> medicationIds;
    private String disease;

    public PrescriptionDTO(Long medicId, Long patientId, List<Long> medicationIds, String disease) {
        this.medicId = medicId;
        this.patientId = patientId;
        this.medicationIds = medicationIds;
        this.disease = disease;
    }

    public PrescriptionDTO(final Builder builder) {
        this.medicId = builder.medicId;
        this.patientId = builder.patientId;
        this.medicationIds = builder.medicationIds;
        this.disease = builder.disease;
    }

    public Long getMedicId() {
        return medicId;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<Long> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(List<Long> medicationIds) {
        this.medicationIds = medicationIds;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public static class Builder {
        private Long medicId;
        private Long patientId;
        private List<Long> medicationIds;
        private String disease;

        public Builder setMedicId(Long medicId) {
            this.medicId = medicId;
            return this;
        }

        public Builder setPatientId(Long patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder setMedicationIds(List<Long> medicationIds) {
            this.medicationIds = medicationIds;
            return this;
        }

        public Builder setDisease(String disease) {
            this.disease = disease;
            return this;
        }

        public PrescriptionDTO create() {
            return new PrescriptionDTO(this);
        }
    }
}
