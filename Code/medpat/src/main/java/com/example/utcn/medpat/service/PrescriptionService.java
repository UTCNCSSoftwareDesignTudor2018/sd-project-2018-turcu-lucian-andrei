package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class PrescriptionService {

    @Inject
    PrescriptionRepository prescriptionRepository;
}
