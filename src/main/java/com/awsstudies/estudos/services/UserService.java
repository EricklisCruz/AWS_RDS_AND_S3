package com.awsstudies.estudos.services;

import com.awsstudies.estudos.model.entities.MyUserPrincipal;
import com.awsstudies.estudos.model.entities.User;
import com.awsstudies.estudos.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User update(Long id, User user) {
        try {
            User entity = userRepository.findById(id).get();
            updateDate(entity, user);
            return userRepository.save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((MyUserPrincipal) principal).user();
        return user;
    }

    private void updateDate(User entity, User user) {
        entity.setUrlImage(user.getUrlImage());
    }
}
