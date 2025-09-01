package com.loan_transaction.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import com.loan_transaction.service.SecretProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final SecretProvider secretProvider;


    private SecretKey getSigningKey() {
        String secret = secretProvider.getSecret();
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }
}


