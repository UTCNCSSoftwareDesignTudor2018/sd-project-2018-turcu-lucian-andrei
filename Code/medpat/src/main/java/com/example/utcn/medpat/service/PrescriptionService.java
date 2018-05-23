package com.example.utcn.medpat.service;

import com.example.utcn.medpat.communication.dto.PrescriptionDTO;
import com.example.utcn.medpat.persistence.model.Medication;
import com.example.utcn.medpat.persistence.model.Prescription;
import com.example.utcn.medpat.persistence.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrescriptionService {

    @Inject
    private PrescriptionRepository prescriptionRepository;

    @Inject
    private MedicService medicService;
    @Inject
    private PatientService patientService;
    @Inject
    private MedicationService medicationService;

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public List<Prescription> getPatientPrescriptions(Long patientId) {
        return prescriptionRepository.findAllByPatientId(patientId);
    }

    public List<Prescription> getDoctorPrescriptions(Long doctorId) {
        return prescriptionRepository.findAllByMedicId(doctorId);
    }
    public void prescribe(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setMedic(medicService.getMedic(prescriptionDTO.getMedicId()));
        prescription.setPatient(patientService.getPatient(prescriptionDTO.getPatientId()));
        prescription.setDisease(prescriptionDTO.getDisease());
        prescription.setMedications(new ArrayList<Medication>());
        for(Long med: prescriptionDTO.getMedicationIds()) {
            prescription.getMedications().add(medicationService.getMedication(med));
        }
        Date dt = new java.util.Date();
        prescription.setCreationDate(sdf.format(dt));
        prescriptionRepository.save(prescription);
    }
}
