package com.awsstudies.estudos.services;

import com.awsstudies.estudos.model.entities.User;
import com.awsstudies.estudos.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
