package com.example.interestmarket.service;

import com.example.interestmarket.domain.Post;
import com.example.interestmarket.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(String id, String title, String content) {
        Post post = new Post();
        post.setWriter(id);
        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);
        return post;
    }

    public Post viewPost(String postId) throws ExecutionException, InterruptedException {
        Post post = postRepository.findByPostId(postId);
        return post;
    }

    public List<Post> viewPosts() throws ExecutionException, InterruptedException {
        return postRepository.findAll();
    }

    public List<Post> searchByTitle(String title) throws ExecutionException, InterruptedException {
        return postRepository.findByTitle(title);
    }

    public Boolean deletePosts(String id, String writer, String postId) {
        return postRepository.delete(id, writer, postId);
    }
}
