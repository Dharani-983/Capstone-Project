package com.gateway.filter;

//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import com.gateway.util.JwtUtil;
//
//import reactor.core.publisher.Mono;
//
//@Component
//public class JwtAuthenticationFilter 
//
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getPath().toString();
//
//        
//        if (path.contains("/api/v1/customers/login") || path.contains("/api/v1/customers/register")) {
//            return chain.filter(exchange);
//        }
//
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        String token = authHeader.substring(7);
//
//        if (!jwtUtil.validateToken(token)) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        
//        exchange = exchange.mutate()
//                .request(r -> r.header(HttpHeaders.AUTHORIZATION, authHeader))
//                .build();
//
//        return chain.filter(exchange);
//    }
//
//}