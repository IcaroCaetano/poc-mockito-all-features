package com.myproject.poc_mockito_all_features.repository;

import com.myproject.poc_mockito_all_features.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
    void save(User user);
}
