package org.example.watchlist.adapter.rest;

import org.example.watchlist.domain.application.service.WatchlistService;
import org.example.watchlist.domain.application.service.dto.MovieDto;
import org.example.watchlist.domain.application.service.dto.MovieResults;
import org.example.watchlist.domain.application.service.dto.WatchlistMovieDataDto;
import org.example.watchlist.domain.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('SCOPE_Watchlist.User')")
public class MovieController {

    private final WatchlistService watchlistService;

    public MovieController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping("/movies")
    public MovieResults getMovies(@RequestParam Integer page) {
        return watchlistService.getMovies(page);
    }

    @GetMapping("/data")
    public List<WatchlistMovieDataDto> getWatchlistData(@RequestParam String id) {
        if (id != null && !id.isBlank()) {
            List<Integer> ids = Arrays.stream(id.split(",")).map(Integer::valueOf).toList();
            return watchlistService.getWatchlistDataFor(ids);
        } else {
            return List.of();
        }
    }

    @PostMapping("/add/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@RequestBody MovieDto movie, JwtAuthenticationToken token) {
        return watchlistService.addMovie(movie, User.fromTokenAttributes(token.getTokenAttributes()));
    }

    @PostMapping("/delete/movie")
    public MovieDto deleteMovie(@RequestBody MovieDto movie, JwtAuthenticationToken token) {
        String oid = (String) token.getTokenAttributes().get("oid");
        return watchlistService.deleteMovie(movie, oid);
    }
}
