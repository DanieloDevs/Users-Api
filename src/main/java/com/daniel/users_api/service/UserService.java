package com.daniel.users_api.service;

import org.springframework.stereotype.Service;

import com.daniel.users_api.dto.UserRequestDTO;
import com.daniel.users_api.dto.UserResponseDTO;
import com.daniel.users_api.dto.UserUpdateDTO;
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

    public List<UserResponseDTO> getAllUsers(String sortedBy, String filter) {

        List<User> filteredUsers = new ArrayList<>(users);

        if (filter != null && !filter.isBlank()) {

            String[] parts = filter.split(" ");

            if (parts.length != 3) {
                throw new RuntimeException("Invalid filter format");
            }

            String field = parts[0];
            String operator = parts[1];
            String value = parts[2].toLowerCase();

            filteredUsers = filteredUsers.stream()
                    .filter(user -> applyFilter(user, field, operator, value))
                    .toList();
        }
        List<User> sortedUsers = new ArrayList<>(filteredUsers);

        if (sortedBy != null && !sortedBy.isBlank()) {

            switch (sortedBy) {
                case "email":
                    sortedUsers.sort(Comparator.comparing(User::getEmail));
                    break;
                case "id":
                    sortedUsers.sort(Comparator.comparing(User::getId));
                    break;
                case "name":
                    sortedUsers.sort(Comparator.comparing(User::getName));
                    break;
                case "phone":
                    sortedUsers.sort(Comparator.comparing(User::getPhone));
                    break;
                case "tax_id":
                    sortedUsers.sort(Comparator.comparing(User::getTaxId));
                    break;
                case "created_at":
                    sortedUsers.sort(Comparator.comparing(User::getCreatedAt));
                    break;
                default:
                    throw new RuntimeException("Invalid sortedBy parameter");
            }
        }
        return sortedUsers.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getPhone(),
                        user.getTaxId(),
                        user.getCreatedAt(),
                        user.getAddresses()))
                .toList();
    }

    private boolean applyFilter(User user, String field, String operator, String value) {

        String fieldValue;

        switch (field) {
            case "email":
                fieldValue = user.getEmail();
                break;
            case "name":
                fieldValue = user.getName();
                break;
            case "phone":
                fieldValue = user.getPhone();
                break;
            case "tax_id":
                fieldValue = user.getTaxId();
                break;
            default:
                throw new RuntimeException("Invalid filter field");
        }

        fieldValue = fieldValue.toLowerCase();

        switch (operator) {
            case "co":
                return fieldValue.contains(value);
            case "eq":
                return fieldValue.equals(value);
            case "sw":
                return fieldValue.startsWith(value);
            case "ew":
                return fieldValue.endsWith(value);
            default:
                throw new RuntimeException("Invalid filter operator");
        }
    }

    private long addressCounter = 7;

    public UserResponseDTO createUser(UserRequestDTO request) {

        boolean exists = users.stream()
                .anyMatch(user -> user.getTaxId().equals(request.getTaxId()));

        if (exists) {
            throw new RuntimeException("Tax ID already exists");
        }

        List<Address> addressesWithIds = new ArrayList<>();

        if (request.getAddresses() != null) {
            for (Address address : request.getAddresses()) {

                Address newAddress = new Address(
                        addressCounter++,
                        address.getName(),
                        address.getStreet(),
                        address.getCountryCode());

                addressesWithIds.add(newAddress);
            }
        }

        User newUser = new User(
                UUID.randomUUID(),
                request.getEmail(),
                request.getName(),
                request.getPhone(),
                request.getPassword(),
                request.getTaxId(),
                LocalDateTime.now(),
                addressesWithIds);

        users.add(newUser);

        return new UserResponseDTO(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getName(),
                newUser.getPhone(),
                newUser.getTaxId(),
                newUser.getCreatedAt(),
                newUser.getAddresses());
    }

    public void deleteUser(UUID id) {

        boolean removed = users.removeIf(user -> user.getId().equals(id));

        if (!removed) {
            throw new RuntimeException("User not found");
        }
    }

    public UserResponseDTO updateUser(UUID id, UserUpdateDTO request) {

        User user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

        if (request.getTaxId() != null) {
            boolean exists = users.stream()
                    .anyMatch(u -> !u.getId().equals(id)
                            && u.getTaxId().equals(request.getTaxId()));

            if (exists) {
                throw new RuntimeException("Tax ID already exists");
            }

            user.setTaxId(request.getTaxId());
        }

        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getTaxId(),
                user.getCreatedAt(),
                user.getAddresses());
    }

}
