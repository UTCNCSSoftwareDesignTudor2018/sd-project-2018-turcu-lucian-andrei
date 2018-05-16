package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    private Long fromId;
    private Long toId;
    private String date;
    private String message;

    public Message(Long fromId, Long toId, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.date = "";
    }


    public Message() {};

    public Long getId() {
        return id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
