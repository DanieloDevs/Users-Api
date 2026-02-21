package com.daniel.users_api.model;

public class Address {
    private Long id;
    private String name;
    private String street;
    private String CountryCode;

    public Address() {
    }

    public Address(Long id, String name, String street, String CountryCode) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.CountryCode = CountryCode;
    }

}
