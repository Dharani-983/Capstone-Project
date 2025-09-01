package com.user_service.utils;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.user_service.model.Role;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String base64Secret;

    @Value("${jwt.expiration-ms:86400000}")
    private long expirationMs;

    private Key getKey() {
        byte[] decoded = Base64.getDecoder().decode(base64Secret);
        return Keys.hmacShaKeyFor(decoded);
    }

    public String generateToken(String email, Set<Role> roles) {
        Key key = getKey();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream().map(Role::getName).collect(Collectors.toList()));
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    /**
     * Validate token and return claims. Throws JwtException on invalid.
     */
    public Claims validateTokenAndGetClaims(String token) {
        Key key = getKey();
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}