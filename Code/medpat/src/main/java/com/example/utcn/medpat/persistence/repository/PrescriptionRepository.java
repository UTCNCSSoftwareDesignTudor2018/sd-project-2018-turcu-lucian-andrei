package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByPatientId(Long patientId);
    List<Prescription> findAllByMedicId(Long medicId);
}
