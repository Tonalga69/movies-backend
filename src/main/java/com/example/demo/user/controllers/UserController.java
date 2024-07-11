package com.example.demo.user.controllers;


import com.example.demo.user.entities.User;
import com.example.demo.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/")
    private ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    private ResponseEntity<User> getUserById(@PathVariable Long id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    private ResponseEntity<List<User>> getUserByName(@PathVariable String role) {
        final List<User> users = userRepository.findByUserRole(role).orElse(null);
        if (users==null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        users.forEach(user -> user.setPassword(""));
        return ResponseEntity.ok(users);
    }




}
