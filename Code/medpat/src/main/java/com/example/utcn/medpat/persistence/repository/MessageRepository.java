package com.example.utcn.medpat.persistence.repository;

import com.example.utcn.medpat.persistence.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByToId(String toId);
    List<Message> findAllByFromId(String fromId);
}
