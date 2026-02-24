package com.daniel.users_api.controller;

import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.dto.UserUpdateDTO;
import com.daniel.users_api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import com.daniel.users_api.dto.ApiResponse;
import com.daniel.users_api.dto.LoginRequestDTO;
import com.daniel.users_api.dto.UserRequestDTO;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users API", description = "Operations related to user management")
@RestController
@RequestMapping("/users")
public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
                this.userService = userService;
        }

        @GetMapping
        public ApiResponse<List<UserResponseDTO>> getAllUsers(
                        @Parameter(description = "Field to sort by (email, id, name, phone, tax_id, created_at)") @RequestParam(required = false) String sortedBy,
                        @Parameter(description = "Field to sort by (email, id, name, phone, tax_id, created_at)") @RequestParam(required = false) String filter) {

                List<UserResponseDTO> users = userService.getAllUsers(sortedBy, filter);

                return new ApiResponse<>(
                                true,
                                "Users retrieved successfully",
                                users);
        }

        @Operation(summary = "Create a new user", description = "Creates a new user with encrypted password")
        @PostMapping
        public ApiResponse<UserResponseDTO> createUser(
                        @Valid @RequestBody UserRequestDTO request) {

                UserResponseDTO createdUser = userService.createUser(request);

                return new ApiResponse<>(
                                true,
                                "User created successfully",
                                createdUser);
        }

        @Operation(summary = "Delete user", description = "Deletes a user by ID")
        @DeleteMapping("/{id}")
        public ApiResponse<Void> deleteUser(@PathVariable UUID id) {

                userService.deleteUser(id);

                return new ApiResponse<>(
                                true,
                                "User deleted successfully",
                                null);
        }

        @Operation(summary = "Update user", description = "Partially update a user by ID")
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

        @Operation(summary = "User login", description = "Authenticates user using encrypted password")
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