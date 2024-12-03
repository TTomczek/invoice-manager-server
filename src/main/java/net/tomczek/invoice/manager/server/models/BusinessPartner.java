package net.tomczek.invoice.manager.server.models;

import java.util.List;

/**
 * Internal model for BusinessPartnerDAO/BusinessPartnerDTO
 */
public class BusinessPartner {

    private Integer id;
    private String name;
    private String description;
    private Address address;
    private List<ContactPerson> contactPersons;
    private List<Invoice> invoices;

    public BusinessPartner(Integer id, String name, String description, Address address, List<ContactPerson> contactPersons, List<Invoice> invoices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactPersons = contactPersons;
        this.invoices = invoices;
    }

    public BusinessPartner() {}

    public Integer getId() {
        return id;
    }

    public BusinessPartner setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BusinessPartner setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BusinessPartner setDescription(String description) {
        this.description = description;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public BusinessPartner setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public BusinessPartner setContactPersons(List<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
        return this;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public BusinessPartner setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    @Override
    public String toString() {
        return "BusinessPartner{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", address=" + address +
            ", contactPersons=" + contactPersons +
            ", invoices=" + invoices +
            '}';
    }
}
