package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Post;

import java.util.Optional;

public interface PostRepository {
    Post save();
    Optional<Post> findByTitle();
    Optional<Post> findByContent();
    Optional<Post> findById();
    void delete();
}
