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

    public Message(Builder builder) {
        from = builder.from;
        to = builder.to;
        this.message = builder.message;
        this.date = builder.date;
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

    static class Builder {
        private Long id;
        private User from;
        private User to;
        private String date;
        private String message;

        public Builder setFrom(User from) {
            this.from = from;
            return this;
        }

        public Builder setTo(User to) {
            this.to = to;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Message create() {
            return new Message(id, from, to, date, message);
        }
    }
}
