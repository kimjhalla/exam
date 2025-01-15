package com.kb.exam.domain.user.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.user.service.UserService;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "회원 관련 API")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입
    @RequestMapping(method = RequestMethod.POST, value = "/join")
    @Operation(summary = "회원 가입 API", description = "가입 회원 정보를 입력한다. email정보는 고유값이고 유저 권한은 POST_USER,COMMENT_USER 두개의 권한 존재 각 권한별로 접근가능한 API 상이")
    public ResponseEntity<CommonResponse> join(@RequestBody UserJoinVO vo) {
        return ResponseEntity.ok(userService.join(vo));
    }

    // 회원 로그인
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @Operation(summary = "회원 로그인 API", description = "이메일,비밀번호 로그인 처리 응답은 accessToken,refreshToken을 리턴한다.")
    public ResponseEntity<CommonResponse> login(@RequestBody UserLoginVO vo) {
        return ResponseEntity.ok(userService.login(vo));
    }

    // 토큰 재발급
    @RequestMapping(method = RequestMethod.GET, value = "/access-token")
    @Operation(summary = "회원 토큰 재발급 API", description = "refreshToken을 헤더에 실어서 전달하면 새로운 accessToken, refreshToken 값을 전달한다.")
    public ResponseEntity<CommonResponse> token() {
        return ResponseEntity.ok(userService.regenerateToken());
    }

}
