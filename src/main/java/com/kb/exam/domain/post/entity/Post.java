package com.kb.exam.domain.post.entity;

import com.kb.exam.domain.post.vo.PostAddVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    private long userSeq;
    private String title;
    private String content;
    @Column(updatable = false)
    private LocalDateTime registerDate;
    @Setter
    private LocalDateTime updateDate;

    public Post(PostAddVO postAddVO, long userSeq) {
        this.userSeq = userSeq;
        this.title = postAddVO.title();
        this.content = postAddVO.content();
        LocalDateTime now = LocalDateTime.now();
        this.registerDate = now;
        this.updateDate = now;
    }
}
