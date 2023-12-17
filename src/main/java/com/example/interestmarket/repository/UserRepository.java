package com.example.interestmarket.repository;

import com.example.interestmarket.domain.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserRepository {
    User save(User user);
    List<User> findById(String id) throws InterruptedException, ExecutionException;
    Boolean validateUserIsExist(String id) throws InterruptedException, ExecutionException;
}
