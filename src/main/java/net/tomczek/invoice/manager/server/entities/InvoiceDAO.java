package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Rechnung
 */
@Table(name = "invoices")
@Entity
public class InvoiceDAO extends BaseEntity<Integer> {

    public InvoiceDAO(Integer id, String description, boolean perMail, String preText, String postText, LocalDate serviceProvidedFrom, LocalDate serviceProvidedTo, String orderNumber, Integer generatedInvoiceId, SalesTaxDAO salexTax, List<InvoicePositionDAO> invoicePosition, ContactPersonDAO receiver, InvoiceTemplateDAO invoiceTemplateDAO, BusinessPartnerDAO customer) {
        super(id);
        this.description = description;
        this.perMail = perMail;
        this.preText = preText;
        this.postText = postText;
        this.serviceProvidedFrom = serviceProvidedFrom;
        this.serviceProvidedTo = serviceProvidedTo;
        this.orderNumber = orderNumber;
        this.generatedInvoiceId = generatedInvoiceId;
        this.salexTax = salexTax;
        this.invoicePosition = invoicePosition;
        this.receiver = receiver;
        this.invoiceTemplateDAO = invoiceTemplateDAO;
        this.customer = customer;
    }

    public InvoiceDAO() {
    }

    private String description;

    private boolean perMail;

    private String preText;

    private String postText;

    private LocalDate serviceProvidedFrom;

    private LocalDate serviceProvidedTo;

    private String orderNumber;

    private Integer generatedInvoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    private SalesTaxDAO salexTax;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoicePositionDAO> invoicePosition;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContactPersonDAO receiver;

    @OneToOne(fetch = FetchType.LAZY)
    private InvoiceTemplateDAO invoiceTemplateDAO;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessPartnerDAO customer;

    public String getDescription() {
        return description;
    }

    public InvoiceDAO setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isPerMail() {
        return perMail;
    }

    public InvoiceDAO setPerMail(boolean perMail) {
        this.perMail = perMail;
        return this;
    }

    public String getPreText() {
        return preText;
    }

    public InvoiceDAO setPreText(String preText) {
        this.preText = preText;
        return this;
    }

    public String getPostText() {
        return postText;
    }

    public InvoiceDAO setPostText(String postText) {
        this.postText = postText;
        return this;
    }

    public LocalDate getServiceProvidedFrom() {
        return serviceProvidedFrom;
    }

    public InvoiceDAO setServiceProvidedFrom(LocalDate serviceProvidedFrom) {
        this.serviceProvidedFrom = serviceProvidedFrom;
        return this;
    }

    public LocalDate getServiceProvidedTo() {
        return serviceProvidedTo;
    }

    public InvoiceDAO setServiceProvidedTo(LocalDate serviceProvidedTo) {
        this.serviceProvidedTo = serviceProvidedTo;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public InvoiceDAO setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Integer getGeneratedInvoiceId() {
        return generatedInvoiceId;
    }

    public InvoiceDAO setGeneratedInvoiceId(Integer generatedInvoiceId) {
        this.generatedInvoiceId = generatedInvoiceId;
        return this;
    }

    public SalesTaxDAO getSalexTax() {
        return salexTax;
    }

    public InvoiceDAO setSalexTax(SalesTaxDAO salexTax) {
        this.salexTax = salexTax;
        return this;
    }

    public List<InvoicePositionDAO> getInvoicePosition() {
        return invoicePosition;
    }

    public InvoiceDAO setInvoicePosition(List<InvoicePositionDAO> invoicePositionDAOS) {
        this.invoicePosition = invoicePositionDAOS;
        return this;
    }

    public ContactPersonDAO getReceiver() {
        return receiver;
    }

    public InvoiceDAO setReceiver(ContactPersonDAO receiver) {
        this.receiver = receiver;
        return this;
    }

    public InvoiceTemplateDAO getInvoiceTemplateDAO() {
        return invoiceTemplateDAO;
    }

    public InvoiceDAO setInvoiceTemplateDAO(InvoiceTemplateDAO invoiceTemplateDAO) {
        this.invoiceTemplateDAO = invoiceTemplateDAO;
        return this;
    }

    public BusinessPartnerDAO getCustomer() {
        return customer;
    }

    public InvoiceDAO setCustomer(BusinessPartnerDAO customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "InvoiceDAO{" +
            "description='" + description + '\'' +
            ", perMail=" + perMail +
            ", preText='" + preText + '\'' +
            ", postText='" + postText + '\'' +
            ", serviceProvidedFrom=" + serviceProvidedFrom +
            ", serviceProvidedTo=" + serviceProvidedTo +
            ", orderNumber='" + orderNumber + '\'' +
            ", generatedInvoiceId=" + generatedInvoiceId +
            ", salexTax=" + salexTax +
            ", invoicePosition=" + invoicePosition +
            ", receiver=" + receiver +
            ", invoiceTemplateDAO=" + invoiceTemplateDAO +
            ", customer=" + customer +
            ", id=" + id +
            '}';
    }
}
