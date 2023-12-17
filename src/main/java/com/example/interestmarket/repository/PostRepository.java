package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PostRepository {
    Post save(Post post);
    Post findByPostId(String postId) throws InterruptedException, ExecutionException;
    List<Post> findByTitle(String title) throws InterruptedException, ExecutionException;
    List<Post> findByContent(String content) throws InterruptedException, ExecutionException;
    List<Post> findById(String id) throws InterruptedException, ExecutionException;
    List<Post> findAll() throws InterruptedException, ExecutionException;
    Boolean delete(String id, String writer, String postId);
}
