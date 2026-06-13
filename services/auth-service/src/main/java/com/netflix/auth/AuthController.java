package com.netflix.auth;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> creds) {
        // Simplified — in production use JWT + Spring Security
        if ("admin".equals(creds.get("username")) && "password".equals(creds.get("password"))) {
            return Map.of("token", "mock-jwt-token-" + System.currentTimeMillis(), "status", "success");
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/validate")
    public Map<String, String> validate(@RequestHeader("Authorization") String token) {
        return Map.of("valid", "true", "user", "admin");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "auth-service");
    }
}
