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

// UserDetailsService를 커스텀 하기 위한 클래스
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    // TODO 유저 정보 변경시 캐시 삭제 필요 및 캐시 정책에 따라 만료일을 지정할 수도 있을 것 같음
    // TODO 캐싱키를 변수명으로 설정하였을때 매핑이 되지 않아 임시적으로 자리수 매핑 처리 추후 개선 필요
    @Override
    @Cacheable(value = "userDetail",key = "#p0")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        User user = optionalUser.get();
        List<UserRole> userRoles = userRoleRepository.findByUserSeq(user.getSeq());

        return new UserVO(user, userRoles);
    }
}