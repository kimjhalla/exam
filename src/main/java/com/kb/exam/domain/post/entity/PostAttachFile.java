package com.kb.exam.domain.post.entity;

import com.kb.exam.domain.post.vo.PostAttachFileAddVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST_ATTACH_FILE")
public class PostAttachFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    private long postSeq;
    private String fileName;
    private String filePath;
    @Column(updatable = false)
    private LocalDateTime registerDate;

    public PostAttachFile(PostAttachFileAddVO postAttachFileAddVO, long postSeq) {
        this.postSeq = postSeq;
        this.fileName = postAttachFileAddVO.fileName();
        this.filePath = postAttachFileAddVO.filePath();
        this.registerDate = LocalDateTime.now();
    }
}
