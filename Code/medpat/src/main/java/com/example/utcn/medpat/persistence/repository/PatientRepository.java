package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findAllById(Long patientId);
}
