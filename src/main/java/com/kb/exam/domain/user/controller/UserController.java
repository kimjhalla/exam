package com.kb.exam.domain.user.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.user.service.UserService;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입
    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public ResponseEntity<CommonResponse> join(@RequestBody UserJoinVO vo) {
        return ResponseEntity.ok(userService.join(vo));
    }

    // 회원 로그인
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ResponseEntity<CommonResponse> login(@RequestBody UserLoginVO vo) {
        return ResponseEntity.ok(userService.login(vo));
    }

    // 토큰 재발급
    @RequestMapping(method = RequestMethod.GET, value = "/access-token")
    public ResponseEntity<CommonResponse> token() {
        return ResponseEntity.ok(userService.regenerateToken());
    }

}
