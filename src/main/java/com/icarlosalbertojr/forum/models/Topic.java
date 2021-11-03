package com.icarlosalbertojr.forum.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private TopicStatus topicStatus;
    @ManyToOne
    private ForumUser author;
    @ManyToOne
    private Course course;
    @OneToMany
    private List<Answer> answers;

    public Topic() {
        topicStatus = TopicStatus.NOT_ANSWERED;
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TopicStatus getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(TopicStatus topicStatus) {
        this.topicStatus = topicStatus;
    }

    public ForumUser getAuthor() {
        return author;
    }

    public void setAuthor(ForumUser author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
