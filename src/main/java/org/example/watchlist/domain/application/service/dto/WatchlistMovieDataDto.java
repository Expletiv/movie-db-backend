package org.example.watchlist.domain.application.service.dto;

// Contains all the movie data that is not from TMDB
public record WatchlistMovieDataDto(
        Integer id,
        String addedBy
) {
}
