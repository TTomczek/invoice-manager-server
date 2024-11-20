package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "business_partners")
@Entity
public class BusinessPartnerDAO extends BaseEntity<Integer> {

    public BusinessPartnerDAO(Integer id, String name, String description, AddressDAO address, List<ContactPersonDAO> contactPersonDAOS, List<InvoiceDAO> invoiceDAOS) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactPersonDAOS = contactPersonDAOS;
        this.invoiceDAOS = invoiceDAOS;
    }

    public BusinessPartnerDAO() {}

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private AddressDAO address;

    @OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY)
    private List<ContactPersonDAO> contactPersonDAOS;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
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

    public AddressDAO getAddress() {
        return address;
    }

    public BusinessPartnerDAO setAddress(AddressDAO address) {
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
