package com.kb.exam.domain.post.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostAttachFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(nullable = false)
    private long postSeq;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String filePath;
    @Column(nullable = false, updatable = false)
    private LocalDateTime registerDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;
}
