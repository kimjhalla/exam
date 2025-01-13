package com.kb.exam.domain.post.entity;

import com.kb.exam.domain.post.vo.PostVO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(nullable = false)
    private long userSeq;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, updatable = false)
    private LocalDateTime registerDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Post(PostVO postVO, long userSeq) {
        this.userSeq = userSeq;
        this.title = postVO.title();
        this.content = postVO.content();
        LocalDateTime now = LocalDateTime.now();
        this.registerDate = now;
        this.updateDate = now;
    }
}
