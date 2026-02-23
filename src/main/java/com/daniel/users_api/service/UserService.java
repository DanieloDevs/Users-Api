package com.daniel.users_api.service;

import org.springframework.stereotype.Service;

import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.model.Address;
import com.daniel.users_api.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        initializeUsers();
    }

    private void initializeUsers() {
        Address address1 = new Address(1L, "workaddress", "street No.1", "US");
        Address address2 = new Address(2L, "homeaddress", "street No.2", "AU");

        User user1 = new User(
                UUID.randomUUID(),
                "daniel@gmail.com",
                "Daniel",
                "5555555555",
                "danielo10",
                "OIMD123456789",
                LocalDateTime.now(),
                Arrays.asList(address1, address2));
        users.add(user1);

        Address address3 = new Address(3L, "workaddress", "street No.3", "US");
        Address address4 = new Address(4L, "homeaddress", "street No.4", "UK");

        User user2 = new User(
                UUID.randomUUID(),
                "brandon@gmail.com",
                "Brandon",
                "5444444444",
                "brandon10",
                "OIMB123456789",
                LocalDateTime.now(),
                Arrays.asList(address3, address4));
        users.add(user2);

        Address address5 = new Address(5L, "workaddress", "street No.5", "UA");
        Address address6 = new Address(6L, "homeaddress", "street No.6", "UL");

        User user3 = new User(
                UUID.randomUUID(),
                "juanito@gmail.com",
                "Juanito",
                "4444444444",
                "juanito10",
                "OIMJ123456789",
                LocalDateTime.now(),
                Arrays.asList(address5, address6));
        users.add(user3);
    }

    public List<UserResponseDTO> getAllUsers() {
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getPhone(),
                        user.getTaxId(),
                        user.getCreatedAt(),
                        user.getAddresses()))
                .collect(Collectors.toList());
    }
}
