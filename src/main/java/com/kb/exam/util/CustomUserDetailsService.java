package com.kb.exam.util;

import com.kb.exam.domain.user.enums.UserRoleEnum;
import com.kb.exam.domain.user.vo.UserVO;
import com.kb.exam.entity.User;
import com.kb.exam.entity.UserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

//    private final UserRepository userRepository;

//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // TODO 유저 정보 캐시 설정
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO email 값으로 회원 정보 조회 및 셋팅

//        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
//
//        if (userEntityOptional.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//
//        UserEntity userEntity = userEntityOptional.get();

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(1, UserRoleEnum.POST_USER.name()));
//        userRoles.add(new UserRole(1,UserRoleEnum.COMMENT_USER.name()));

        User user = new User(
                1,
                "email@email.com",
                "password",
                "name",
                "nickname",
                LocalDateTime.now(),
                LocalDateTime.now(),
                userRoles
        );

        return new UserVO(user);
    }
}