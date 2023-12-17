package com.example.interestmarket.service;

import com.example.interestmarket.domain.Comment;
import com.example.interestmarket.repository.CommentRepository;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(String content, String writer) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setWriter(writer);
        commentRepository.save(comment);

        return comment;
    }
}
