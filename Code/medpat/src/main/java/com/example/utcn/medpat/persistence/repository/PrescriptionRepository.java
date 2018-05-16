package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
