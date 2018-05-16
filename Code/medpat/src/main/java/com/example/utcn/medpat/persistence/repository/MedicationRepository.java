package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
