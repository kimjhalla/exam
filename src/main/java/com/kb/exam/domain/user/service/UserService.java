package com.kb.exam.domain.user.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginResponseVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public CommonResponse login(UserLoginVO userLoginVO) {
        userLoginVO.email();
        userLoginVO.password();
        // 성공이면 access/refresh 토큰 전달

        // 실패이면 오류 전달
        return new CommonResponse(new UserLoginResponseVO("accessToken", "refreshToken"));
    }

    public CommonResponse join(UserJoinVO userJoinVO) {


        return new CommonResponse();


    }
}
