package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;
import net.tomczek.invoice.manager.server.models.Address;

@Table(name = "contact_persons")
@Entity
public class ContactPersonDAO extends BaseEntity<Integer> {

    public ContactPersonDAO(Integer id, String name, String firstName, String email, Address address, BusinessPartnerDAO businessPartnerDAO, SalutationET salutation) {
        super(id);
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.businessPartnerDAO = businessPartnerDAO;
        this.salutation = salutation;
    }

    public ContactPersonDAO() {
    }

    private String name;

    private String firstName;

    private String email;

    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessPartnerDAO businessPartnerDAO;

    @Enumerated(EnumType.STRING)
    private SalutationET salutation;

    public String getName() {
        return name;
    }

    public ContactPersonDAO setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactPersonDAO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactPersonDAO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public ContactPersonDAO setAddress(Address address) {
        this.address = address;
        return this;
    }

    public BusinessPartnerDAO getBusinessPartnerDAO() {
        return businessPartnerDAO;
    }

    public ContactPersonDAO setBusinessPartnerDAO(BusinessPartnerDAO businessPartnerDAO) {
        this.businessPartnerDAO = businessPartnerDAO;
        return this;
    }

    public SalutationET getSalutation() {
        return salutation;
    }

    public ContactPersonDAO setSalutation(SalutationET salutation) {
        this.salutation = salutation;
        return this;
    }
}
