package org.example.watchlist.domain.application.service.repositories;

import org.example.watchlist.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    @NonNull
    Optional<User> findById(@NonNull String id);

    @NonNull
    List<User> findAll();

}
