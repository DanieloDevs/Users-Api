package com.daniel.users_api.dto;

import com.daniel.users_api.model.Address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserResponseDTO {

    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;

    public UserResponseDTO() {
    }

    public UserResponseDTO(UUID id, String email, String name,
            String phone, String taxId,
            LocalDateTime createdAt,
            List<Address> addresses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.taxId = taxId;
        this.createdAt = createdAt;
        this.addresses = addresses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}