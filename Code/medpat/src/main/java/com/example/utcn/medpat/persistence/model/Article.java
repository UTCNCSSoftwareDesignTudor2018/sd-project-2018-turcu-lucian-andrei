package com.example.utcn.medpat.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne
    private Long authorId;

    private String title;
    private String body;
    private Date creationDate;

    public Article(Long authorId, String title, String body, Date creationDate) {
        this.authorId = authorId;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
