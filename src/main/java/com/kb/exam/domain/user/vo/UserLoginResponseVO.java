package com.kb.exam.domain.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserLoginResponseVO {
    private String accessToken;
    private String refreshToken;

    public UserLoginResponseVO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
