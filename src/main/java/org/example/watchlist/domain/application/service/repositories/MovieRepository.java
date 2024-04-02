package org.example.watchlist.domain.application.service.repositories;

import org.example.watchlist.domain.application.service.dto.WatchlistMovieDataDto;
import org.example.watchlist.domain.model.movie.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {

    void save(Movie movie);

    Optional<Movie> findById(Integer id);

    void deleteById(Integer id);

    Boolean existsById(Integer id);

    Integer count();

    @Query("select m.id, m.added_by from movie m where id = :id")
    WatchlistMovieDataDto getWatchlistData(Integer id);
}
