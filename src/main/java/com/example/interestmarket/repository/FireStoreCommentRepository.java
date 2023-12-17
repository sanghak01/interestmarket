package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Comment;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class FireStoreCommentRepository implements CommentRepository{

    Firestore db = FirestoreClient.getFirestore();

    @Override
    public Comment save(Comment comment) {
        DocumentReference addedPostRef = db.collection("Comment").document();
        comment.setId(addedPostRef.getId());
        comment.setCreationTime(Timestamp.now());
        addedPostRef.set(comment);
        return comment;
    }
}
