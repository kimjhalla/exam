package com.kb.exam.config;

import com.kb.exam.domain.user.enums.UserRoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("user/**").permitAll() // 로그인, 가입은 전체 접근 가능
                        .requestMatchers("posts/**").hasRole(UserRoleEnum.POST_USER.name()) // 게시글은 로그인 하고 특정 권한을 가진 유저만 가능
                        .requestMatchers(HttpMethod.GET, "posts/**/comments/**").hasRole(UserRoleEnum.POST_USER.name()) // 게시글 댓글 조회도 동일
                        // 댓글 작성/수정/삭제는 댓글 권한 유저만 가능
                        .requestMatchers(HttpMethod.POST, "posts/**/comments").hasRole(UserRoleEnum.COMMENT_USER.name())
                        .requestMatchers(HttpMethod.PUT, "posts/**/comments/**").hasRole(UserRoleEnum.COMMENT_USER.name())
                        .requestMatchers(HttpMethod.DELETE, "posts/**/comments/**").hasRole(UserRoleEnum.COMMENT_USER.name())
                        .anyRequest().authenticated()
                );
//                .formLogin(form -> form // 폼 기반 로그인 설정
//                        .loginPage("/login") // 커스텀 로그인 페이지 경로
//                        .permitAll()
//                )
//                .logout(logout -> logout // 로그아웃 설정
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                );
        return http.build();
    }
}
