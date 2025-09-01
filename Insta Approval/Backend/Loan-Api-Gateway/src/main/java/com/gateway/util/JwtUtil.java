//package com.gateway.util;
//
//import java.security.Key;
//import java.util.Base64;
//import java.util.Date;
//
//import javax.crypto.spec.SecretKeySpec;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//@Component
//public class JwtUtil {
//
//    private final Key key;
//
//    public JwtUtil(WebClient.Builder webClientBuilder) {
//        // Fetch secret dynamically from customer-service
//        String secret = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8081/api/v1/secret")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        this.key = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
//
//        System.out.println("Gateway using shared JWT secret: " 
//                + Base64.getEncoder().encodeToString(key.getEncoded()));
//    }
//
//    public Claims getClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
//
//    public String extractEmail(String token) {
//        return getClaims(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        return getClaims(token).getExpiration().before(new Date());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            return extractEmail(token) != null && !isTokenExpired(token);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getBase64Secret() {
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//    }
//}