package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Comment;

public interface CommentRepository {
    Comment save();
    void delete();
}
