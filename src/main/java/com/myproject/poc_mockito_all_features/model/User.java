package com.myproject.poc_mockito_all_features.model;

public class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "User{id='%s', name='%s'}".formatted(id, name);
    }
}
