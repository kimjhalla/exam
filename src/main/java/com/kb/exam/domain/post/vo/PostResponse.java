package com.kb.exam.domain.post.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {
    private long seq;
    private long userSeq;
    private String title;
}
