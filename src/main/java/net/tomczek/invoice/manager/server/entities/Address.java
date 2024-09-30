package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;

@Entity
public class Address extends BaseEntity<Integer> {

        private String street;

        private String houseNumber;

        private String zipCode;

        private String city;

        private String country;
}
