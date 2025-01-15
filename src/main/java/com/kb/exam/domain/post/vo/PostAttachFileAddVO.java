package com.kb.exam.domain.post.vo;

import com.kb.exam.exception.CustomValidationException;
import org.apache.commons.lang3.StringUtils;

public record PostAttachFileAddVO(String fileName, String filePath) {
    public PostAttachFileAddVO {
        if (fileName == null || StringUtils.isEmpty(fileName)) {
            throw new CustomValidationException("파일명을 입력해 주세요.");
        } else if (fileName.length() > 125) {
            throw new CustomValidationException("파일명은 125자를 초과할 수 없습니다.");
        }

        if (filePath == null || StringUtils.isEmpty(filePath)) {
            throw new CustomValidationException("파일경로를 입력해 주세요.");
        } else if (filePath.length() > 250) {
            throw new CustomValidationException("파일경로는 250자를 초과할 수 없습니다.");
        }
    }
}
