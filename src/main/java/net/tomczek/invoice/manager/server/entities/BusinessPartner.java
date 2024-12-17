package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "business_partners")
@Entity
public class BusinessPartner extends BaseEntity<Integer> {

    public BusinessPartner(Integer id, String name, String description, Address address, List<ContactPerson> contactPeople, List<Invoice> invoices) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactPeople = contactPeople;
        this.invoices = invoices;
    }

    public BusinessPartner() {}

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY)
    private List<ContactPerson> contactPeople;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Invoice> invoices;

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
        return contactPeople;
    }

    public BusinessPartner setContactPersons(List<ContactPerson> contactPeople) {
        this.contactPeople = contactPeople;
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
        return "BusinessPartnerDAO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", address=" + address +
            ", contactPersonDAOS=" + contactPeople.size() +
            ", invoiceDAOS=" + invoices.size() +
            ", id=" + id +
            '}';
    }
}
