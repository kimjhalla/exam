package com.kb.exam.config;

import com.kb.exam.domain.user.enums.UserRoleEnum;
import com.kb.exam.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",   // OpenAPI 문서 경로
                                "/swagger-ui/**",    // Swagger UI 경로
                                "/swagger-resources/**", // Swagger 리소스 경로
                                "/webjars/**"        // Swagger Webjars 경로
                        ).permitAll() // 위의 경로에 대해 접근 허용
                        .requestMatchers("/v1/user/**").permitAll() // 로그인, 가입은 전체 접근 가능
                        // 댓글 작성/수정/삭제는 댓글 권한 유저만 가능
                        .requestMatchers(HttpMethod.POST, "/v1/posts/*/comments").hasAuthority(UserRoleEnum.COMMENT_USER.name())
                        .requestMatchers(HttpMethod.PUT, "/v1/posts/*/comments/*").hasAuthority(UserRoleEnum.COMMENT_USER.name())
                        .requestMatchers(HttpMethod.DELETE, "/v1/posts/*/comments/*").hasAuthority(UserRoleEnum.COMMENT_USER.name())
                        // 게시글 댓글 목록은 특정 권한을 가진 유저만 가능
                        .requestMatchers(HttpMethod.GET, "/v1/posts/*/comments").hasAuthority(UserRoleEnum.POST_USER.name())
                        // 게시글은 로그인 하고 특정 권한을 가진 유저만 가능
                        .requestMatchers("/v1/posts").hasAuthority(UserRoleEnum.POST_USER.name())
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
