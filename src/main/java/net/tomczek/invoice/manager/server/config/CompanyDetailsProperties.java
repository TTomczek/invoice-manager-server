package net.tomczek.invoice.manager.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "company.details")
public class CompanyDetailsProperties {
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;

    public String getName() {
        return name;
    }

    public CompanyDetailsProperties setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public CompanyDetailsProperties setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public CompanyDetailsProperties setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public CompanyDetailsProperties setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CompanyDetailsProperties setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CompanyDetailsProperties setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CompanyDetailsProperties setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyDetailsProperties setEmail(String email) {
        this.email = email;
        return this;
    }
}
