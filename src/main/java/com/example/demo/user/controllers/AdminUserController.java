package com.example.demo.user.controllers;


import com.example.demo.user.entities.User;
import com.example.demo.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user);
        user.setId(null);
        final User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping
    private ResponseEntity<User> updateUser(@RequestBody User user) {
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUser(@PathVariable Long id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
