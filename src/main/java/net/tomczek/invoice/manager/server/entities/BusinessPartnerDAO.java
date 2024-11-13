package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;
import net.tomczek.invoice.manager.server.models.Address;

import java.util.List;

@Table(name = "business_partners")
@Entity
public class BusinessPartnerDAO extends BaseEntity<Integer> {

    public BusinessPartnerDAO(Integer id, String name, String description, Address address) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public BusinessPartnerDAO() {}

    private String name;

    private String description;

    private Address address;

    @OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY)
    private List<ContactPersonDAO> contactPersonDAOS;

    @OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY)
    private List<InvoiceDAO> invoiceDAOS;

    public String getName() {
        return name;
    }

    public BusinessPartnerDAO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BusinessPartnerDAO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public BusinessPartnerDAO setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<ContactPersonDAO> getContactPersonDAOS() {
        return contactPersonDAOS;
    }

    public BusinessPartnerDAO setContactPersonDAOS(List<ContactPersonDAO> contactPersonDAOS) {
        this.contactPersonDAOS = contactPersonDAOS;
        return this;
    }

    public List<InvoiceDAO> getInvoiceDAOS() {
        return invoiceDAOS;
    }

    public BusinessPartnerDAO setInvoiceDAOS(List<InvoiceDAO> invoiceDAOS) {
        this.invoiceDAOS = invoiceDAOS;
        return this;
    }
}
