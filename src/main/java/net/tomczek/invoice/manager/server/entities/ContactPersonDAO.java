package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;
import net.tomczek.invoice.manager.server.models.Address;

@Table(name = "contact_persons")
@Entity
public class ContactPersonDAO extends BaseEntity<Integer> {

    public ContactPersonDAO(Integer id, String name, String firstName, String email, AddressDAO address, BusinessPartnerDAO businessPartner, SalutationET salutation) {
        super(id);
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.businessPartner = businessPartner;
        this.salutation = salutation;
    }

    public ContactPersonDAO() {
    }

    private String name;

    private String firstName;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private AddressDAO address;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessPartnerDAO businessPartner;

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

    public AddressDAO getAddress() {
        return address;
    }

    public ContactPersonDAO setAddress(AddressDAO address) {
        this.address = address;
        return this;
    }

    public BusinessPartnerDAO getBusinessPartner() {
        return businessPartner;
    }

    public ContactPersonDAO setBusinessPartner(BusinessPartnerDAO businessPartnerDAO) {
        this.businessPartner = businessPartnerDAO;
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
