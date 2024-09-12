package com.example.demo.user.controllers;


import com.example.demo.user.DTOs.UserDTO;
import com.example.demo.user.entities.User;
import com.example.demo.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    private ResponseEntity<List<UserDTO>> getAllUsers() {
        final List<User> users = userRepository.findAll();
        final List<UserDTO> userDTOS= users.stream().map(UserDTO::fromUser).toList();
        return ResponseEntity.ok(userDTOS);
    }



    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDTO.fromUser(user));
    }

    @GetMapping("/role/{role}")
    private ResponseEntity<List<UserDTO>> getUserByName(@PathVariable String role) {
        final List<User> users = userRepository.findByUserRole(role).orElse(null);
        if (users==null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final List<UserDTO> userDTOS= users.stream().map(UserDTO::fromUser).toList();
        return ResponseEntity.ok(userDTOS);
    }






}
