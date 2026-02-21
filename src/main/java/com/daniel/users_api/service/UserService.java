package com.daniel.users_api.service;

import org.springframework.stereotype.Service;
import com.daniel.users_api.model.Address;
import com.daniel.users_api.model.User;

import java.time.LocalDateTime;
import java.util.*;

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
                "OIMB123456789",
                LocalDateTime.now(),
                Arrays.asList(address1, address2));
        users.add(user1);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
