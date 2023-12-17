package com.example.interestmarket;

import com.example.interestmarket.repository.*;
import com.example.interestmarket.service.CommentService;
import com.example.interestmarket.service.PostService;
import com.example.interestmarket.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public PostRepository postRepository() {
        return new FireStorePostRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new FireStoreUserRepository();
    }

    @Bean
    public CommentRepository commentRepository() {
        return new FireStoreCommentRepository();
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public CommentService commentService() {
        return new CommentService(commentRepository());
    }
}
