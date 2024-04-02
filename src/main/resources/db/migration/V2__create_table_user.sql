create table "user"
(
    id                 varchar(50),
    name               varchar(100),
    preferred_username varchar(100),
    version            integer,
    constraint user_pk primary key (id)
);

delete from genre_id;
delete from movie;

alter table movie
    add column added_by varchar(50);

alter table movie
    add constraint movie_user_added_by_fk foreign key (added_by) references "user" (id);