package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route to UserService
                .route("user-service", r -> r.path("/api/v1/customers/**")
                        .uri("http://localhost:8081")) // UserService port
                // Route to LoanService
//                .route("loan-service", r -> r.path("/api/v1/loans/**")
//                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers['Authorization']}"))
//                        .uri("http://localhost:8082")) // LoanService port
//                // Route to Admin endpoints
//                .route("admin-service", r -> r.path("/api/v1/admin/**")
//                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers['Authorization']}"))
//                        .uri("http://localhost:8082")) // Admin is in LoanService
                .build();
    }
}

