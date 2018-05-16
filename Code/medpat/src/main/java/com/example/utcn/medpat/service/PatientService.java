package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class PatientService {

    @Inject
    PatientRepository patientRepository;

}
