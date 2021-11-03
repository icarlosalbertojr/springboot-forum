package com.icarlosalbertojr.forum.controllers.dto;

import com.icarlosalbertojr.forum.models.Answer;
import com.icarlosalbertojr.forum.models.Topic;
import com.icarlosalbertojr.forum.models.TopicStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailsResponseDto {

    private Long id;
    private String title;
    private String message;
    private String author;
    private String course;
    private TopicStatus topicStatus;
    private LocalDateTime createdAt;
    private List<AnswerResponseDto> answers;

    public TopicDetailsResponseDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.author = topic.getAuthor().getFullName();
        this.course = topic.getCourse().getCourseName();
        this.topicStatus = topic.getTopicStatus();
        this.createdAt = topic.getCreatedAt();
        this.answers = topic.getAnswers()
                .stream()
                .map(AnswerResponseDto::new)
                .collect(Collectors.toList());
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

    public String getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }

    public TopicStatus getTopicStatus() {
        return topicStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<AnswerResponseDto> getAnswers() {
        return answers;
    }
}
