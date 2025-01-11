package com.kb.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .requestMatchers("user/**").permitAll() // 특정 경로 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
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
