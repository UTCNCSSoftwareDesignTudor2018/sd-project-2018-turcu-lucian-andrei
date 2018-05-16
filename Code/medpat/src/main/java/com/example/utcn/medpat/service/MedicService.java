package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Medic;
import com.example.utcn.medpat.persistence.repository.MedicRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MedicService {

    @Inject
    private MedicRepository medicRepository;

    public List<Medic> getMedics() {
        return medicRepository.findAll();
    }

    public Medic getMedic(Long medicId) {
        return medicRepository.findAllById(medicId);
    }
}
