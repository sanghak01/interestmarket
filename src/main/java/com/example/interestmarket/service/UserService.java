package com.example.interestmarket.service;

import com.example.interestmarket.domain.User;
import com.example.interestmarket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(String id, String name, String password) {
        User user = new User();
        user.setName(name);
        user.setId(id);
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }

    public User login(String id, String password) throws InterruptedException, ExecutionException {
        List<User> a = userRepository.findById(id);
        User b = null;
        log.info("a : {}", a.toString());

        if (a.isEmpty()) {
            return null;
        } else {
            for (User user : a) {
                b = user;
            }

            log.info("b : {}", b);
            log.info("b.getId : {}", b.getId());
            log.info("b.getPassword : {}", b.getPassword());

            if (b.getPassword().equals(password)) {
                return b;
            } else {
                return null;
            }
        }
    }
}
