package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Prescription;
import com.example.utcn.medpat.persistence.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PrescriptionService {

    @Inject
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPatientPrescriptions(Long patientId) {
        return prescriptionRepository.findAllByPatientId(patientId);
    }

    public List<Prescription> getDoctorPrescriptions(Long doctorId) {
        return prescriptionRepository.findAllByMedicId(doctorId);
    }
    public void prescribe(Prescription prescription) {

        prescriptionRepository.save(prescription);
    }
}
