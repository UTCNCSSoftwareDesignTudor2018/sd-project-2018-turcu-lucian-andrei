package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.repository.MedicRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MedicService {

    @Inject
    MedicRepository medicRepository;

}
