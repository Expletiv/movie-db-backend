package org.example.watchlist.domain.application.service;

import org.example.watchlist.domain.application.service.dto.MovieDto;
import org.example.watchlist.domain.application.service.dto.MovieResults;
import org.example.watchlist.domain.application.service.dto.WatchlistMovieDataDto;
import org.example.watchlist.domain.application.service.dto.mapper.DtoMapper;
import org.example.watchlist.domain.application.service.exceptions.MovieAlreadyInWatchlistException;
import org.example.watchlist.domain.application.service.exceptions.MovieNotInWatchlistException;
import org.example.watchlist.domain.application.service.exceptions.NoPermissionToDeleteMovieException;
import org.example.watchlist.domain.application.service.repositories.MovieRepository;
import org.example.watchlist.domain.application.service.repositories.UserRepository;
import org.example.watchlist.domain.model.movie.Movie;
import org.example.watchlist.domain.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WatchlistService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final static int PAGE_SIZE = 20;

    public WatchlistService(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public MovieDto addMovie(MovieDto movieDto, User user) {
        User savedUser = saveUser(user);
        if (movieRepository.existsById(movieDto.id())) {
            throw new MovieAlreadyInWatchlistException();
        }
        Movie movie = DtoMapper.mapToMovie(movieDto);
        movie.setAddedBy(savedUser.id());

        movieRepository.save(movie);
        return DtoMapper.mapToMovieDto(movie);
    }

    private User saveUser(User user) {
        Optional<User> maybeUser = userRepository.findById(user.id());
        return maybeUser.orElseGet(() -> userRepository.save(user));
    }

    public MovieDto deleteMovie(MovieDto movie, String oid) {
        Optional<Movie> maybeMovie = movieRepository.findById(movie.id());
        Movie loadedMovie = maybeMovie.orElseThrow(MovieNotInWatchlistException::new);
        if (loadedMovie.addedBy().equals(oid)) {
            movieRepository.deleteById(movie.id());
        } else {
            throw new NoPermissionToDeleteMovieException();
        }
        return movie;
    }

    public MovieResults getMovies(Integer page) {
        List<Movie> movies = movieRepository.findAll(Pageable.ofSize(PAGE_SIZE).withPage(page)).toList();
        return new MovieResults(page, DtoMapper.mapToMovieDto(movies), movieRepository.count(), countPages());
    }

    public List<WatchlistMovieDataDto> getWatchlistDataFor(List<Integer> ids) {
        return ids.stream().map(movieRepository::getWatchlistData).filter(Objects::nonNull).toList();
    }

    public Integer countPages() {
        return Math.floorDiv(movieRepository.count(), PAGE_SIZE + 1);
    }
}
