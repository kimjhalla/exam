package com.kb.exam.user.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.user.service.UserService;
import com.kb.exam.user.vo.UserJoinVO;
import com.kb.exam.user.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<CommonResponse> join(@RequestBody UserJoinVO vo) {
        return ResponseEntity.ok(userService.join(vo));
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<CommonResponse> login(UserLoginVO vo) {
        return ResponseEntity.ok(userService.login(vo));
    }

}
