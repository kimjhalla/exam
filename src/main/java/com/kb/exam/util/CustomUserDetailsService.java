package com.kb.exam.util;

import com.kb.exam.domain.user.entity.User;
import com.kb.exam.domain.user.entity.UserRole;
import com.kb.exam.domain.user.repository.UserRepository;
import com.kb.exam.domain.user.repository.UserRoleRepository;
import com.kb.exam.domain.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    // 유저 정보 캐싱 처리
    // TODO 유저 정보 변경시 캐시 삭제 필요 및 캐시 정책에 따라 만료일을 지정할 수도 있을 것 같음
    @Override
    @Cacheable(value = "userDetail",key = "#p0")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();
        List<UserRole> userRoles = userRoleRepository.findByUserSeq(user.getSeq());

        return new UserVO(user, userRoles);
    }
}