package com.kb.exam.domain.post.vo;

import com.kb.exam.exception.CustomValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public record PostAddVO(String title, String content, List<PostAttachFileAddVO> postAttachFileAddVOS) {

    public PostAddVO {
        if (title == null || StringUtils.isEmpty(title)) {
            throw new CustomValidationException("제목을 입력해 주세요.");
        } else if (title.length() > 50) {
            throw new CustomValidationException("제목은 50자를 초과할 수 없습니다.");
        }

        if (content == null || StringUtils.isEmpty(content)) {
            throw new CustomValidationException("내용을 입력해 주세요.");
        } else if (content.length() > 1000) {
            throw new CustomValidationException("내용은 1000자를 초과할 수 없습니다.");
        }
    }
}
