package com.example.interestmarket.repository;

import com.example.interestmarket.domain.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FireStorePostRepository implements PostRepository{

    Logger logger = LoggerFactory.getLogger(FireStorePostRepository.class);

    Firestore db = FirestoreClient.getFirestore();

    @Override
    public Post save(Post post) {
        DocumentReference addedPostRef = db.collection("Post").document();
        post.setPostId(addedPostRef.getId());
        post.setCreationTime(Timestamp.now());
        addedPostRef.set(post);
        return post;
    }

    @Override
    public Post findByPostId(String postId) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Post").document(postId);
        ApiFuture<DocumentSnapshot> a = docRef.get();
        DocumentSnapshot b = a.get();
        Post post = b.toObject(Post.class);

        if (b.exists()) {
            return post;
        } else {
            return null;
        }
    }

    @Override
    public List<Post> findByTitle(String title) throws InterruptedException, ExecutionException {
        List<Post> postList = new ArrayList<Post>();
        Post post = null;

        ApiFuture<QuerySnapshot> a = db.collection("Post")
                .orderBy("title")
                .startAt(title)
                .endAt(title + "\uf8ff")
                .get();

        List<QueryDocumentSnapshot> b = a.get().getDocuments();
        logger.info("b : {}", b);

        for(DocumentSnapshot document : b) {
            post = document.toObject(Post.class);
            logger.info("post : {}", post);
            postList.add(post);
        }

        return postList;
    }

    @Override
    public List<Post> findByContent(String content) throws InterruptedException, ExecutionException {
        List<Post> postList = new ArrayList<Post>();
        Post post = null;

        ApiFuture<QuerySnapshot> a = db.collection("Post")
                .orderBy("content")
                .startAt(content)
                .endAt(content + "\uf8ff")
                .get();

        List<QueryDocumentSnapshot> b = a.get().getDocuments();

        for(QueryDocumentSnapshot document : b) {
            post = document.toObject(Post.class);
            postList.add(post);
        }

        return postList;
    }

    @Override
    public List<Post> findById(String id) throws InterruptedException, ExecutionException {
        List<Post> postList = new ArrayList<Post>();

        ApiFuture<QuerySnapshot> a = db.collection("Post")
                .orderBy("writer")
                .startAt(id)
                .endAt(id + "\uf8ff")
                .get();

        List<QueryDocumentSnapshot> b = a.get().getDocuments();

        for(DocumentSnapshot document : b) {
            postList.add(document.toObject(Post.class));
        }

        return postList;
    }

    @Override
    public List<Post> findAll() throws InterruptedException, ExecutionException {
        List<Post> postList = new ArrayList<>();

        ApiFuture<QuerySnapshot> a = db.collection("Post").get();

        List<QueryDocumentSnapshot> b = a.get().getDocuments();

        for (QueryDocumentSnapshot document : b) {
            postList.add(document.toObject(Post.class));
        }

        return postList;
    }

    @Override
    public Boolean delete(String id, String writer, String postId) {
        if (id.equals(writer)) {
            ApiFuture<WriteResult> writeResult = db.collection("Post").document(postId).delete();
            return true;
        } else {
            return false;
        }
    }
}
