package com.daniel.users_api.controller;

import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.dto.UserUpdateDTO;
import com.daniel.users_api.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import com.daniel.users_api.dto.ApiResponse;
import com.daniel.users_api.dto.LoginRequestDTO;
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
    public ApiResponse<List<UserResponseDTO>> getAllUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {

        List<UserResponseDTO> users = userService.getAllUsers(sortedBy, filter);

        return new ApiResponse<>(
                true,
                "Users retrieved successfully",
                users);
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO request) {

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

    @PatchMapping("/{id}")
    public ApiResponse<UserResponseDTO> updateUser(
            @PathVariable UUID id,
            @RequestBody UserUpdateDTO request) {

        UserResponseDTO updatedUser = userService.updateUser(id, request);

        return new ApiResponse<>(
                true,
                "User updated successfully",
                updatedUser);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        UserResponseDTO user = userService.login(
                request.getEmail(),
                request.getPassword());

        return new ApiResponse<>(
                true,
                "Login successful",
                user);
    }
}