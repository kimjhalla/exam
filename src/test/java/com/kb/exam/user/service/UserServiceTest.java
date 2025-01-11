package com.kb.exam.user.service;

import com.kb.exam.domain.user.service.UserService;
import com.kb.exam.domain.user.vo.UserLoginVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    }
}