package com.example.payment_service.config;

public class JwtUtil {
    public boolean validateToken(String token) {
        return true;   // accept any token for testing
    }

    public String extractUsername(String token) {
        return "testuser";
    }
}
