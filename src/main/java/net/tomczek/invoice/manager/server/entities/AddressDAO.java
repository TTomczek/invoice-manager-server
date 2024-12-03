package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDAO extends BaseEntity<Integer> {

        public AddressDAO(Integer id, String street, String houseNumber, String zipCode, String city, String country) {
            super(id);
            this.street = street;
            this.houseNumber = houseNumber;
            this.zipCode = zipCode;
            this.city = city;
            this.country = country;
        }

        public AddressDAO() {
        }

        private String street;

        private String houseNumber;

        private String zipCode;

        private String city;

        private String country;

        public String getStreet() {
            return street;
        }

        public AddressDAO setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public AddressDAO setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public String getZipCode() {
            return zipCode;
        }

        public AddressDAO setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public String getCity() {
            return city;
        }

        public AddressDAO setCity(String city) {
            this.city = city;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public AddressDAO setCountry(String country) {
            this.country = country;
            return this;
        }

    @Override
    public String toString() {
        return "AddressDAO{" +
            "country='" + country + '\'' +
            ", id=" + id +
            ", city='" + city + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", houseNumber='" + houseNumber + '\'' +
            ", street='" + street + '\'' +
            '}';
    }
}
