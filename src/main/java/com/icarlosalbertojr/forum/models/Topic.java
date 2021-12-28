package com.icarlosalbertojr.forum.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
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
    private User author;
    @ManyToOne
    private Course course;
    @OneToMany
    private List<Answer> answers;

    public Topic() {
        topicStatus = TopicStatus.NOT_ANSWERED;
        createdAt = LocalDateTime.now();
    }


}
