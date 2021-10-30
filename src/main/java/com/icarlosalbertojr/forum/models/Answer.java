package com.icarlosalbertojr.forum.models;

import java.time.LocalDateTime;

public class Answer {

    private Long id;
    private Topic topic;
    private User author;
    private LocalDateTime createdAt;
    private Boolean isSolution;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getSolution() {
        return isSolution;
    }

    public void setSolution(Boolean solution) {
        isSolution = solution;
    }
}
