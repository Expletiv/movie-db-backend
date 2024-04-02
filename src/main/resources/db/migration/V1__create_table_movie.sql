create table movie
(
    adult             boolean,
    backdrop_path     text,
    id                integer,
    original_language text,
    original_title    text,
    overview          text,
    popularity        double precision,
    poster_path       text,
    release_date      text,
    title             text,
    video             boolean,
    vote_average      double precision,
    vote_count        integer,
    version           integer,
    constraint movie_pk primary key (id)
);

create table genre_id (
    id integer,
    movie integer,
    constraint movie_genre_fk foreign key (movie) references movie(id)
)