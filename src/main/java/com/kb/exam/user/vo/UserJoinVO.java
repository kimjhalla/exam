package com.kb.exam.user.vo;

import com.kb.exam.exceptions.CustomValidationException;
import org.apache.commons.lang3.StringUtils;

public record UserJoinVO(String name, String nickname, String email, String password) {

    public UserJoinVO {
        // 빈값일 경우 체크하여 exception 처리

        if (StringUtils.isEmpty(name)) {
            throw new CustomValidationException("이름을 입력해 주세요.");
        }
        if (StringUtils.isEmpty(nickname)) {
            throw new CustomValidationException("닉네임을 입력해 주세요.");
        }
        if (StringUtils.isEmpty(email)) {
            throw new CustomValidationException("이메일을 입력해 주세요.");
        }
        if (StringUtils.isEmpty(password)) {
            throw new CustomValidationException("비밀번호를 입력해 주세요.");
        }
    }
}
