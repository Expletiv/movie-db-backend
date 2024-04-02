package org.example.watchlist.domain.application.service.dto.mapper;

import org.example.watchlist.domain.application.service.dto.MovieDto;
import org.example.watchlist.domain.application.service.dto.WatchlistMovieDataDto;
import org.example.watchlist.domain.model.movie.GenreId;
import org.example.watchlist.domain.model.movie.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static Movie mapToMovie(MovieDto m) {
        return new Movie(
                m.adult(),
                m.backdropPath(),
                m.genreIds().stream().map(GenreId::new).collect(Collectors.toSet()),
                m.id(),
                m.originalLanguage(),
                m.originalTitle(),
                m.overview(),
                m.popularity(),
                m.posterPath(),
                m.releaseDate(),
                m.title(),
                m.video(),
                m.voteAverage(),
                m.voteCount(),
                null,
                null
        );
    }

    public static MovieDto mapToMovieDto(Movie m) {
        return new MovieDto(
                m.adult(),
                m.backdropPath(),
                m.genreIds().stream().map(GenreId::id).collect(Collectors.toSet()),
                m.id(),
                m.originalLanguage(),
                m.originalTitle(),
                m.overview(),
                m.popularity(),
                m.posterPath(),
                m.releaseDate(),
                m.title(),
                m.video(),
                m.voteAverage(),
                m.voteCount(),
                new WatchlistMovieDataDto(m.id(), m.addedBy())
        );
    }

    public static List<MovieDto> mapToMovieDto(List<Movie> movies) {
        return movies.stream().map(DtoMapper::mapToMovieDto).toList();
    }
}
