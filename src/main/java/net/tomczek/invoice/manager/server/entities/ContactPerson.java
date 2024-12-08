package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

@Table(name = "contact_persons")
@Entity
public class ContactPerson extends BaseEntity<Integer> {

    public ContactPerson(Integer id, String name, String firstName, String email, Address address, BusinessPartner businessPartner, SalutationET salutation) {
        super(id);
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.businessPartner = businessPartner;
        this.salutation = salutation;
    }

    public ContactPerson() {
    }

    private String name;

    private String firstName;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessPartner businessPartner;

    @Enumerated(EnumType.STRING)
    private SalutationET salutation;

    public String getName() {
        return name;
    }

    public ContactPerson setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactPerson setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactPerson setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public ContactPerson setAddress(Address address) {
        this.address = address;
        return this;
    }

    public BusinessPartner getBusinessPartner() {
        return businessPartner;
    }

    public ContactPerson setBusinessPartner(BusinessPartner businessPartner) {
        this.businessPartner = businessPartner;
        return this;
    }

    public SalutationET getSalutation() {
        return salutation;
    }

    public ContactPerson setSalutation(SalutationET salutation) {
        this.salutation = salutation;
        return this;
    }

    @Override
    public String toString() {
        return "ContactPersonDAO{" +
            "name='" + name + '\'' +
            ", firstName='" + firstName + '\'' +
            ", email='" + email + '\'' +
            ", address=" + address +
            ", businessPartner=" + businessPartner.getName() +
            ", salutation=" + salutation +
            ", id=" + id +
            '}';
    }
}
