package com.icarlosalbertojr.forum.models;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isSolution;
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private ForumUser author;

    public Answer() {
        this.isSolution = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ForumUser getAuthor() {
        return author;
    }

    public void setAuthor(ForumUser author) {
        this.author = author;
    }

    public Boolean getSolution() {
        return isSolution;
    }

    public void setSolution(Boolean solution) {
        isSolution = solution;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
