package com.example.utcn.medpat.communication.dto;

import java.util.List;

public class PrescriptionDTO {
    private Long medicId;
    private Long patientId;
    private String disease;
    private List<Long> medicationIds;

    public PrescriptionDTO(Long medicId, Long patientId, String disease, List<Long> medicationIds) {
        this.medicId = medicId;
        this.patientId = patientId;
        this.disease = disease;
        this.medicationIds = medicationIds;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<Long> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(List<Long> medicationIds) {
        this.medicationIds = medicationIds;
    }
}
