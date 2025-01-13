package com.kb.exam.domain.user.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.user.enums.UserRoleEnum;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginResponseVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import com.kb.exam.entity.User;
import com.kb.exam.entity.UserRole;
import com.kb.exam.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;

    public CommonResponse login(UserLoginVO userLoginVO) {
        userLoginVO.email();
        userLoginVO.password();
        // 성공이면 access/refresh 토큰 전달

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1, UserRoleEnum.POST_USER.name()));
//        userRoles.add(new UserRole(1,UserRoleEnum.COMMENT_USER.name()));

        String accessToken =  jwtTokenProvider.generateToken(new User(
                1,
                "email@email.com",
                "password",
                "name",
                "nickname",
                LocalDateTime.now(),
                LocalDateTime.now(),
                userRoles
        ));

        // 실패이면 오류 전달
        return new CommonResponse(new UserLoginResponseVO(accessToken, "refreshToken"));
    }

    public CommonResponse join(UserJoinVO userJoinVO) {


        return new CommonResponse();


    }
}
