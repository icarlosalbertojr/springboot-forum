package com.icarlosalbertojr.forum.controllers.dto;

import com.icarlosalbertojr.forum.models.Answer;

import java.time.LocalDateTime;

public class AnswerResponseDto {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isSolution;

    public AnswerResponseDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.createdAt = answer.getCreatedAt();
        this.isSolution = answer.getIsSolution();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getSolution() {
        return isSolution;
    }
}
