package com.kb.exam.domain.post.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(nullable = false)
    private long postSeq;
    @Column(nullable = false)
    private long userSeq;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false, updatable = false)
    private LocalDateTime registerDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;
}
