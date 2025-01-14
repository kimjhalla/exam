package com.kb.exam.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    private long postSeq;
    private long userSeq;
    @Setter
    private String content;
    @Column(updatable = false)
    private LocalDateTime registerDate;
    @Setter
    private LocalDateTime updateDate;

    public Comment(long postSeq, long userSeq, String content) {
        this.postSeq = postSeq;
        this.userSeq = userSeq;
        this.content = content;
        LocalDateTime now = LocalDateTime.now();
        this.registerDate = now;
        this.updateDate = now;
    }
}
