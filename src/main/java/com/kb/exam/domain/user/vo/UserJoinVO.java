package com.kb.exam.domain.user.vo;

import com.kb.exam.domain.user.enums.UserRoleEnum;
import com.kb.exam.exception.CustomValidationException;
import com.kb.exam.util.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public record UserJoinVO(String name, String nickname, String email, String password, List<String> roles) {

    public UserJoinVO {
        // 빈값일 경우 체크하여 exception 처리
        if (name == null || StringUtils.isEmpty(name)) {
            throw new CustomValidationException("이름을 입력해 주세요.");
        }

        if (nickname == null || StringUtils.isEmpty(nickname)) {
            throw new CustomValidationException("닉네임을 입력해 주세요.");
        }

        // 이메일 validation check
        if (email == null || StringUtils.isEmpty(email)) {
            throw new CustomValidationException("이메일을 입력해 주세요.");
        } else if (!Utils.isValidEmail(email)) {
            throw new CustomValidationException("잘못된 이메일 주소 입니다.");
        }

        if (password == null || StringUtils.isEmpty(password)) {
            throw new CustomValidationException("비밀번호를 입력해 주세요.");
        }

        // 권한은 적어도 1개 이상
        if (roles == null || roles.isEmpty()) {
            throw new CustomValidationException("권한을 입력해 주세요.");
        }

        // 정의된 권한이 아닌 값이 있으면 오류 리턴
        for (String role : roles) {
            try {
                UserRoleEnum.valueOf(role);
            } catch (IllegalArgumentException e) {
                throw new CustomValidationException("잘못된 권한 입니다.");
            }
        }
    }
}
