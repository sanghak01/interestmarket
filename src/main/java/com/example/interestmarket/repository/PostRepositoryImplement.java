package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Post;

import java.util.Optional;

public class PostRepositoryImplement implements PostRepository {
    @Override
    public Post save(String title, String content) {
        private Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        Firestore.collection(post).set(post)
    }

    @Override
    public Optional<Post> findByTitle() {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findByContent() {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findById() {
        return Optional.empty();
    }

    @Override
    public void delete() {

    }
}
