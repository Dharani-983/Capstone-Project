package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

//import java.util.Base64;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests()
                .anyRequest().permitAll();
        return http.build();
    }
}

    
//    @Bean
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }
//
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder(WebClient.Builder webClientBuilder) {
//        String secret = webClientBuilder.build()
//            .get()
//            .uri("http://localhost:8081/api/v1/secret")
//            .retrieve()
//            .bodyToMono(String.class)
//            .block();
//
//        return NimbusReactiveJwtDecoder.withSecretKey(
//            new javax.crypto.spec.SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256")
//        ).build();
//    }
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/api/v1/customers/register",
//                                      "/api/v1/customers/login").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt()) // tells gateway to validate JWT
//                .build();
//    }