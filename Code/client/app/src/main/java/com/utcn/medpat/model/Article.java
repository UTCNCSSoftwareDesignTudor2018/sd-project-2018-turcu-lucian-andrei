package com.utcn.medpat.model;

/**
 * Created by Lucian on 5/21/2018.
 */

public class Article {
    private Long id;
    private Medic author;
    private String title;
    private String body;
    private String creationDate;

    public Article(Medic author, String title, String body, String creationDate) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    public Article(Long id, Medic author, String title, String body, String creationDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public Medic getAuthor() {
        return author;
    }

    public void setAuthorId(Medic author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
