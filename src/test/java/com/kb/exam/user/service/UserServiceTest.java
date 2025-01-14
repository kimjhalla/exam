package com.kb.exam.user.service;

import com.kb.exam.domain.user.service.UserService;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void login() {
        userService.login(new UserLoginVO(null, null));
    }

    @Test
    void join() {
        List<String> roles = new ArrayList<>();
        roles.add("POST_USER");
        userService.join(new UserJoinVO("이름","닉네임","email@email.com","1234",roles));
    }
}