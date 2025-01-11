package com.kb.exam.domain.post.vo;

import java.util.List;

public record PostVO(String title, String content, List<PostAttachFileVO> postAttachFileVOS) {
}
