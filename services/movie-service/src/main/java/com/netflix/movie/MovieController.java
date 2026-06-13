package com.netflix.movie;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final List<Map<String, String>> movies = List.of(
        Map.of("id", "1", "title", "Stranger Things", "genre", "Sci-Fi"),
        Map.of("id", "2", "title", "The Crown",        "genre", "Drama"),
        Map.of("id", "3", "title", "Squid Game",       "genre", "Thriller")
    );

    @GetMapping
    public List<Map<String, String>> getAll() { return movies; }

    @GetMapping("/{id}")
    public Map<String, String> getById(@PathVariable String id) {
        return movies.stream().filter(m -> m.get("id").equals(id))
            .findFirst().orElseThrow(() -> new RuntimeException("Not found"));
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "movie-service");
    }
}
