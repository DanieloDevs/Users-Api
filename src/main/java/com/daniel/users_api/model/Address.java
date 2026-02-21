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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

}
