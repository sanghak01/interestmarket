package com.example.interestmarket.repository;

import com.example.interestmarket.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save();
    Optional<User> findById();
    void delete();
}
