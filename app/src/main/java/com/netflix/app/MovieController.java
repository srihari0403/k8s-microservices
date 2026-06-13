package com.netflix.app;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final List<Map<String, String>> movies = List.of(
        Map.of("id", "1", "title", "Stranger Things", "genre", "Sci-Fi"),
        Map.of("id", "2", "title", "The Crown",        "genre", "Drama"),
        Map.of("id", "3", "title", "Squid Game",       "genre", "Thriller")
    );

    @GetMapping("/movies")
    public List<Map<String, String>> getMovies() {
        return movies;
    }

    @GetMapping("/movies/{id}")
    public Map<String, String> getMovie(@PathVariable String id) {
        return movies.stream()
            .filter(m -> m.get("id").equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "netflix-app");
    }
}
