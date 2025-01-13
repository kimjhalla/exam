package com.kb.exam.util;

import com.kb.exam.domain.user.entity.User;
import com.kb.exam.domain.user.entity.UserRole;
import com.kb.exam.domain.user.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JwtTokenProviderTest {
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Test
    void generateToken() {

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1, UserRoleEnum.POST_USER.name()));
        userRoles.add(new UserRole(1, UserRoleEnum.COMMENT_USER.name()));

        System.out.println(jwtTokenProvider.generateToken(new User(
                1,
                "email@email.com",
                "password",
                "name",
                "nickname",
                LocalDateTime.now(),
                LocalDateTime.now(),
                userRoles
        )));
    }
}