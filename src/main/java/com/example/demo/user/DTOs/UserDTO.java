package com.example.demo.user.DTOs;

import com.example.demo.user.entities.User;

public class UserDTO {
    private Long id;
    private String name;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String role) {
        this.id = id;
        this.name = username;
        this.role = role;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getUserRole());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setRole(String role) {
        this.role = role;
    }
}
