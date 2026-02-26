package com.documind.common.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 * 基于 JJWT 0.12.x
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret:documind-ai-secret-key-please-change-in-production-2026}")
    private String secret;

    @Value("${jwt.expiration:86400}")
    private long expiration; // 单位：秒

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 Token
     *
     * @param userId   用户ID
     * @param username 用户名
     * @param roles    角色列表 (逗号分隔)
     * @return JWT Token
     */
    public String generateToken(Long userId, String username, String roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("roles", roles);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * 验证 Token
     *
     * @param token JWT Token
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 Token 中提取用户名
     */
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * 从 Token 中提取用户ID
     */
    public Long getUserIdFromToken(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }
    
    /**
     * 从 Token 中提取角色
     */
    public String getRolesFromToken(String token) {
        return extractAllClaims(token).get("roles", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
