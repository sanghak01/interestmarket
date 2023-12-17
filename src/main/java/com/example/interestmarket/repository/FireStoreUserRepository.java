package com.example.interestmarket.repository;

import com.example.interestmarket.domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FireStoreUserRepository implements UserRepository{

    Logger log = LoggerFactory.getLogger(FireStoreUserRepository.class);

    Firestore db = FirestoreClient.getFirestore();

    @Override
    public User save(User user) {
        db.collection("User").add(user);
        return user;
    }

    @Override
    public List<User> findById(String id) throws InterruptedException, ExecutionException {
        List<User> user = new ArrayList<User>();
        log.info("in repository id : {}", id);

        ApiFuture<QuerySnapshot> a = db.collection("User")
                .whereEqualTo("id", id)
                .get();

        List<QueryDocumentSnapshot> documents = a.get().getDocuments();

        for (DocumentSnapshot document : documents) {
            user.add(document.toObject(User.class));
        }

        log.info("user : {}", user);

        return user;
    }

    @Override
    public Boolean validateUserIsExist(String id) throws ExecutionException, InterruptedException {
        if (!findById(id).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
