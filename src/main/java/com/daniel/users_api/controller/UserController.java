package com.daniel.users_api.controller;

import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.daniel.users_api.dto.ApiResponse;
import com.daniel.users_api.dto.UserRequestDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = userService.getAllUsers();

        return new ApiResponse<>(
                true,
                "Users retrieved successfully",
                users);
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(
            @RequestBody UserRequestDTO request) {

        UserResponseDTO createdUser = userService.createUser(request);

        return new ApiResponse<>(
                true,
                "User created successfully",
                createdUser);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);

        return new ApiResponse<>(
                true,
                "User deleted successfully",
                null);
    }
}