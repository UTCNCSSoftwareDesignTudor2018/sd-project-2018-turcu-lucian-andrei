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

    @ManyToOne(fetch = FetchType.EAGER)
    private User from;

    @ManyToOne(fetch = FetchType.EAGER)
    private User to;

    private String date;
    private String message;

    public Message(User from, User to, String message) {
        from = from;
        to = to;
        this.message = message;
        this.date = "";
    }


    public Message() {};

    public Long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
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
