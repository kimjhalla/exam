package com.kb.exam.util;

import com.kb.exam.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class JwtTokenUtilTest {


    @Test
    void generateToken() {
        System.out.println(JwtTokenUtil.generateToken(new User(
                1,
                "email@email.com",
                "password",
                "name",
                "nickname",
                LocalDateTime.now(),
                LocalDateTime.now()
        )));
    }
}