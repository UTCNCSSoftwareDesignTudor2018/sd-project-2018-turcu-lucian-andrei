package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long>{
    Medic findAllById(Long medicId);
}
