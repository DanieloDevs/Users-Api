package com.daniel.users_api.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;

    public User() {
    }

    public User(UUID id, String email, String name, String phone, String password, String taxId,
            LocalDateTime createdAt, List<Address> addresses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.taxId = taxId;
        this.createdAt = createdAt;
        this.addresses = addresses;
    }

}
