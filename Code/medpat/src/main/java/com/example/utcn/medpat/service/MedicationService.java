package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MedicationService {

    @Inject
    MedicationRepository medicationRepository;
}
