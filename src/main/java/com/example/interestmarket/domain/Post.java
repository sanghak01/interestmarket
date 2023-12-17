package com.example.interestmarket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.cloud.Timestamp;

import java.util.Date;
import java.util.List;

public class Post {
    private String postId;
    private String title;
    private String content;
    private String writer; // id로?
    private Timestamp creationTime;

    private List<Comment> comments;

    public Post() {}

    public Post(String postId, String title, String content, String writer, Timestamp creationTime, List<Comment> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.creationTime = creationTime;
        this.comments = comments;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
