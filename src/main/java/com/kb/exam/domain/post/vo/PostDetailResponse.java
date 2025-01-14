package com.kb.exam.domain.post.vo;

import com.kb.exam.domain.post.entity.Post;
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

    public PostDetailResponse(Post post) {
        this.seq = post.getSeq();
        this.userSeq = post.getUserSeq();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
