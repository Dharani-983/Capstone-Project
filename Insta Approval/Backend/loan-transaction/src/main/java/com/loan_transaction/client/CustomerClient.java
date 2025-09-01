package com.loan_transaction.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.loan_transaction.dto.UserDTO;

@FeignClient(
		name = "user-service", 
		url = "${customer.service.url}",
		configuration = com.loan_transaction.config.FeignConfig.class)
public interface CustomerClient {
    @GetMapping("/api/v1/customers/validate")
    UserDTO validateToken(@RequestHeader("Authorization") String token);
}