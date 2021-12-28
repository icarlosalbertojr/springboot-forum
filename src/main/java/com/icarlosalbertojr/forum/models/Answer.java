package com.icarlosalbertojr.forum.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
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
    private User author;

    public Answer() {
        this.isSolution = false;
    }

}
