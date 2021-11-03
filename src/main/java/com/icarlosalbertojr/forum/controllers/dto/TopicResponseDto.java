package com.icarlosalbertojr.forum.controllers.dto;

import com.icarlosalbertojr.forum.models.Topic;

import java.time.LocalDateTime;

public class TopicResponseDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;

    public TopicResponseDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
