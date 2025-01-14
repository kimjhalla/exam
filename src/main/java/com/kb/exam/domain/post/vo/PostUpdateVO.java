package com.kb.exam.domain.post.vo;

import com.kb.exam.exception.CustomValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public record PostUpdateVO(long postSeq,String title, String content, List<PostAttachFileAddVO> postAttachFileAddVOS) {

    public PostUpdateVO {
        if (postSeq <= 0) {
            throw new CustomValidationException("잘못된 게시물 값 입니다.");
        }

        if (title == null || StringUtils.isEmpty(title)) {
            throw new CustomValidationException("제목을 입력해 주세요.");
        }

        if (content == null || StringUtils.isEmpty(content)) {
            throw new CustomValidationException("내용을 입력해 주세요.");
        }
    }
}
