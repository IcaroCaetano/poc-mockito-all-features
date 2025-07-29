package com.myproject.poc_mockito_all_features.service;

import com.myproject.poc_mockito_all_features.model.User;
import com.myproject.poc_mockito_all_features.repository.UserRepository;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String getUserName(String id) {
        return repository.findById(id)
                .map(User::getName)
                .orElse("Unknown");
    }

    public void registerUser(String id, String name) {
        repository.save(new User(id, name));
    }
}
