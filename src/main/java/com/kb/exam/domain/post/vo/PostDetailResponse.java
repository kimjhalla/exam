package com.kb.exam.domain.post.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailResponse {
    private long seq;
    private long userSeq;
    private String title;
    private String content;
    private List<CommentResponse> comments;
}
