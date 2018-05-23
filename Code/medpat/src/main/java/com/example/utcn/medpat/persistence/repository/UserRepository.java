package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
   User findUserById(String id);
   List<User> findAll();
}
