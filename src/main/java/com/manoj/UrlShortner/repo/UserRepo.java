package com.manoj.UrlShortner.repo;

import com.manoj.UrlShortner.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByUsername(String username);
    boolean existsByEmail(String email);
}
