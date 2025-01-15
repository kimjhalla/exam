package com.kb.exam.domain.user.vo;

import com.kb.exam.exception.CustomValidationException;
import com.kb.exam.util.Utils;
import org.apache.commons.lang3.StringUtils;

public record UserLoginVO(String email, String password) {

    public UserLoginVO {
        // 이메일 validation check
        if (email == null || StringUtils.isEmpty(email)) {
            throw new CustomValidationException("이메일을 입력해 주세요.");
        } else if (!Utils.isValidEmail(email)) {
            throw new CustomValidationException("잘못된 이메일 주소 입니다.");
        } else if (email.length() > 100) {
            throw new CustomValidationException("이메일은 100자를 초과할 수 없습니다.");
        }

        if (password == null || StringUtils.isEmpty(password)) {
            throw new CustomValidationException("비밀번호를 입력해 주세요.");
        } else if (password.length() > 100) {
            throw new CustomValidationException("비밀번호는 25자를 초과할 수 없습니다.");
        }
    }
}
