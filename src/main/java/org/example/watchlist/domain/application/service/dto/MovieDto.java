package org.example.watchlist.domain.application.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record MovieDto(
        @JsonProperty("adult")
        Boolean adult,
        @JsonProperty("backdrop_path")
        String backdropPath,
        @JsonProperty("genre_ids")
        Set<Integer> genreIds,
        @JsonProperty("id")
        Integer id,
        @JsonProperty("original_language")
        String originalLanguage,
        @JsonProperty("original_title")
        String originalTitle,
        @JsonProperty("overview")
        String overview,
        @JsonProperty("popularity")
        Double popularity,
        @JsonProperty("poster_path")
        String posterPath,
        @JsonProperty("release_date")
        String releaseDate,
        @JsonProperty("title")
        String title,
        @JsonProperty("video")
        Boolean video,
        @JsonProperty("vote_average")
        Double voteAverage,
        @JsonProperty("vote_count")
        Integer voteCount,
        WatchlistMovieDataDto watchlistData
) {
}
