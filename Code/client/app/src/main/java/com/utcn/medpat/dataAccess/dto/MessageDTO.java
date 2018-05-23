package com.utcn.medpat.dataAccess.dto;

import com.utcn.medpat.model.Message;

/**
 * Created by Lucian on 5/23/2018.
 */

public class MessageDTO {
    private String from;
    private String to;
    private String message;

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

    public MessageDTO(final Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.message = builder.message;
    }

    public static class Builder {
        private String from;
        private String to;
        private String message;

        public Builder setFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder setTo(String to) {
            this.to = to;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageDTO create() {
            return new MessageDTO(this);
        }
    }

}
