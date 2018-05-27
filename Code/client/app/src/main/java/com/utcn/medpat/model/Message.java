package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Message {

    private Long id;
    private User from;
    private User to;
    private String date;
    private String message;

    public Message(Long id, User from, User to, String date, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }

    public Message() {};

    public Long getId() {
        return this.id;
    }

    public User getFrom() {
        return this.from;
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
