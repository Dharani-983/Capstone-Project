package com.user_service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Change this to any password you want to hash
        String rawPassword = "Dharu@983";
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("BCrypt Hash: " + hashedPassword);
    }
}