package com.kb.exam.user.vo;

import org.apache.commons.lang3.StringUtils;

public record UserLoginVO(String email, String password) {

    public UserLoginVO {
        if (email == null || StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("이메일을 입력해 주세요.");
        }
        // TODO 이메일 정규 표현식 추가

        if (password == null || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("비밀번호 입력해 주세요.");
        }
        // TODO 비밀번호 규칙 정규 표현식 추가
    }
}
