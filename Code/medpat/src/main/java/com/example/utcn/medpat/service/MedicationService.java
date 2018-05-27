package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Medication;
import com.example.utcn.medpat.persistence.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MedicationService {

    @Inject
    MedicationRepository medicationRepository;

    public Medication getMedication(Long id) {
        return medicationRepository.findAllById(id);
    }
    public List<Medication> getMedications() {
        return medicationRepository.findAll();
    }
}
