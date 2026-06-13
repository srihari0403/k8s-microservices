package com.netflix.user;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<Map<String, String>> users = List.of(
        Map.of("id", "1", "name", "Alice", "plan", "premium"),
        Map.of("id", "2", "name", "Bob",   "plan", "basic")
    );

    @GetMapping
    public List<Map<String, String>> getAll() { return users; }

    @GetMapping("/{id}")
    public Map<String, String> getById(@PathVariable String id) {
        return users.stream().filter(u -> u.get("id").equals(id))
            .findFirst().orElseThrow(() -> new RuntimeException("Not found"));
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "user-service");
    }
}
