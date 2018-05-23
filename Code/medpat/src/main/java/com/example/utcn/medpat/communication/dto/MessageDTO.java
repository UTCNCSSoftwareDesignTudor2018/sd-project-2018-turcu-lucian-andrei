package com.example.utcn.medpat.communication.dto;

public class MessageDTO {
    String from;
    String to;
    String message;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDTO(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}
