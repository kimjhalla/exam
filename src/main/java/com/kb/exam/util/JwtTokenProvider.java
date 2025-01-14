package com.kb.exam.util;

import com.kb.exam.domain.user.entity.User;
import com.kb.exam.domain.user.entity.UserRole;
import com.kb.exam.domain.user.enums.TokenTypeEnum;
import com.kb.exam.domain.user.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.access-token.secret-key}")
    private String accessSecretKey;

    @Value("${jwt.refresh-token.secret-key}")
    private String refreshSecretKey;

    @Value("${jwt.access-token.expiration-minutes}")
    private int accessExpirationMinutes;

    @Value("${jwt.refresh-token.expiration-minutes}")
    private int refreshExpirationMinutes;

    private final CustomUserDetailsService userDetailsService;

    public SecretKey getSigningKey(String tokenType) {
        if (TokenTypeEnum.ACCESS_TOKEN.name().equals(tokenType)) {
            return Keys.hmacShaKeyFor(accessSecretKey.getBytes());
        } else if (TokenTypeEnum.REFRESH_TOKEN.name().equals(tokenType)) {
            return Keys.hmacShaKeyFor(refreshSecretKey.getBytes());
        }
        return null;
    }

    // 토큰 생성
    public String generateToken(User user, List<UserRole> userRoles, String tokenType) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("roles", userRoles);
        Date expirationDate = new Date();
        if (TokenTypeEnum.ACCESS_TOKEN.name().equals(tokenType)) {
            expirationDate = new Date(System.currentTimeMillis() + ((long) accessExpirationMinutes * 60 * 1000));
        } else if (TokenTypeEnum.REFRESH_TOKEN.name().equals(tokenType)) {
            expirationDate = new Date(System.currentTimeMillis() + ((long) refreshExpirationMinutes * 60 * 1000));
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getSigningKey(tokenType), SignatureAlgorithm.HS512)
                .compact();
    }

    // 토큰에서 유저 email 조회
    public String extractEmail(String token, String tokenType) {
        return getClaims(token, tokenType).getSubject();
    }

    public long getUserSeq() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException();
        }
        final var user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getSeq();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token, String tokenType) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey(tokenType)).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    // 인증 키 발급
    public Authentication getAuthentication(String token, String tokenType) {
        String email = this.extractEmail(token, tokenType);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    // 토큰에서 클레임 추출
    private Claims getClaims(String token, String tokenType) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey(tokenType)).build().parseClaimsJws(token).getBody();
    }
}
