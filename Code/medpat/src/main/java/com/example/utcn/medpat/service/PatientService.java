package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Patient;
import com.example.utcn.medpat.persistence.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PatientService {

    @Inject
    private PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(Long patientId) {
        return patientRepository.findAllById(patientId);
    }
}
