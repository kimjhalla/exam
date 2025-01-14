package com.kb.exam.domain.user.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.user.entity.User;
import com.kb.exam.domain.user.entity.UserRole;
import com.kb.exam.domain.user.enums.TokenTypeEnum;
import com.kb.exam.domain.user.repository.UserRepository;
import com.kb.exam.domain.user.repository.UserRoleRepository;
import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.domain.user.vo.UserLoginResponseVO;
import com.kb.exam.domain.user.vo.UserLoginVO;
import com.kb.exam.util.JwtTokenProvider;
import com.kb.exam.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 로그인
    public CommonResponse login(UserLoginVO userLoginVO) {
        // 성공이면 access/refresh 토큰 전달
        Optional<User> optionalUser = userRepository.findByEmail(userLoginVO.email());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Utils.checkPassword(userLoginVO.password(), user.getPassword())) {
                List<UserRole> userRoles = userRoleRepository.findByUserSeq(user.getSeq());
                String accessToken = jwtTokenProvider.generateToken(user, userRoles, TokenTypeEnum.ACCESS_TOKEN.name());
                String refreshToken = jwtTokenProvider.generateToken(user, userRoles, TokenTypeEnum.REFRESH_TOKEN.name());
                return new CommonResponse(new UserLoginResponseVO(accessToken, refreshToken));
            } else {
                return CommonResponse.fail("로그인 실패");
            }
        } else {
            return CommonResponse.fail("로그인 실패");
        }
    }

    // 가입
    public CommonResponse join(UserJoinVO userJoinVO) {
        User user = new User(userJoinVO);
        userRepository.save(user);
        List<UserRole> userRoles = new ArrayList<>();
        userJoinVO.roles().forEach(role -> userRoles.add(new UserRole(user.getSeq(), role)));
        userRoleRepository.saveAll(userRoles);
        return new CommonResponse();
    }

    // 토큰 재발행
    public CommonResponse regenerateToken() {
        long userSeq = jwtTokenProvider.getUserSeq();
        Optional<User> optionalUser = userRepository.findById(userSeq);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<UserRole> userRoles = userRoleRepository.findByUserSeq(user.getSeq());
            String accessToken = jwtTokenProvider.generateToken(user, userRoles, TokenTypeEnum.ACCESS_TOKEN.name());
            String newRefreshToken = jwtTokenProvider.generateToken(user, userRoles, TokenTypeEnum.REFRESH_TOKEN.name());
            return new CommonResponse(new UserLoginResponseVO(accessToken, newRefreshToken));

        } else {
            return CommonResponse.fail("로그인 실패");
        }
    }
}
