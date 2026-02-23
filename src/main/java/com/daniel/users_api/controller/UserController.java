package com.daniel.users_api.controller;

import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.daniel.users_api.dto.ApiResponse;

import java.util.List;

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
}