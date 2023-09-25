package com.awsstudies.estudos.repositories;

import com.awsstudies.estudos.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String login);
}
