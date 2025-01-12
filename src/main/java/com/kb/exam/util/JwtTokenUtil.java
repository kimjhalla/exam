package com.kb.exam.util;

import com.kb.exam.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "your-secret-keyyour-secret-keyyour-secret-keyyour-secret-keyyour-secret-keyyour-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    public static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 토큰 생성
    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getSeq() + "");
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // 토큰에서 사용자 시퀀스
    public static Long extractUserSeq(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    // 토큰 유효성 검증
    public static boolean validateToken(String token, String username) {
        return username.equals(extractUserSeq(token)) && !isTokenExpired(token);
    }

    // 토큰 만료 여부 확인
    private static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // 토큰에서 클레임 추출
    private static Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();

    }
}
