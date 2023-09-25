package com.awsstudies.estudos.controllers;

import com.awsstudies.estudos.dto.UserDTO;
import com.awsstudies.estudos.model.entities.User;
import com.awsstudies.estudos.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> insertUser(@RequestBody User user, UriComponentsBuilder builder) {
        user = userService.insertUser(user);
        URI uri = builder.path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUser() {
        List<User> users = userService.findAllUsers();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOS);
    }
}
