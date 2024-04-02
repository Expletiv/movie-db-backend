package org.example.watchlist.domain.model.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Objects;
import java.util.Set;

public final class Movie {
    private final Boolean adult;
    private final String backdropPath;
    private final Set<GenreId> genreIds;
    @Id
    private final Integer id;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final Double popularity;
    private final String posterPath;
    private final String releaseDate;
    private final String title;
    private final Boolean video;
    private final Double voteAverage;
    private final Integer voteCount;
    @Version
    private final Integer version;
    private String addedBy;

    public Movie(
            // Data by TMDB
            Boolean adult,
            String backdropPath,
            Set<GenreId> genreIds,
            Integer id,
            String originalLanguage,
            String originalTitle,
            String overview,
            Double popularity,
            String posterPath,
            String releaseDate,
            String title,
            Boolean video,
            Double voteAverage,
            Integer voteCount,

            // My own data
            Integer version,
            String addedBy
    ) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.version = version;
        this.addedBy = addedBy;
    }

    public Boolean adult() {
        return adult;
    }

    public String backdropPath() {
        return backdropPath;
    }

    public Set<GenreId> genreIds() {
        return genreIds;
    }

    @Id
    public Integer id() {
        return id;
    }

    public String originalLanguage() {
        return originalLanguage;
    }

    public String originalTitle() {
        return originalTitle;
    }

    public String overview() {
        return overview;
    }

    public Double popularity() {
        return popularity;
    }

    public String posterPath() {
        return posterPath;
    }

    public String releaseDate() {
        return releaseDate;
    }

    public String title() {
        return title;
    }

    public Boolean video() {
        return video;
    }

    public Double voteAverage() {
        return voteAverage;
    }

    public Integer voteCount() {
        return voteCount;
    }

    @Version
    public Integer version() {
        return version;
    }

    public String addedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie[" +
                "adult=" + adult + ", " +
                "backdropPath=" + backdropPath + ", " +
                "genreIds=" + genreIds + ", " +
                "id=" + id + ", " +
                "originalLanguage=" + originalLanguage + ", " +
                "originalTitle=" + originalTitle + ", " +
                "overview=" + overview + ", " +
                "popularity=" + popularity + ", " +
                "posterPath=" + posterPath + ", " +
                "releaseDate=" + releaseDate + ", " +
                "title=" + title + ", " +
                "video=" + video + ", " +
                "voteAverage=" + voteAverage + ", " +
                "voteCount=" + voteCount + ", " +
                "version=" + version + ", " +
                "addedBy=" + addedBy + ']';
    }
}
