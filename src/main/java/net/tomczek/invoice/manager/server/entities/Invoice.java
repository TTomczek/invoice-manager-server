package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Rechnung
 */
@Table(name = "invoices")
@Entity
public class Invoice extends BaseEntity<Integer> {

    public Invoice(Integer id, String description, boolean perMail, String preText, String postText, LocalDate serviceProvidedFrom, LocalDate serviceProvidedTo, String orderNumber, Integer generatedInvoiceId, SalesTax salexTax, List<InvoicePosition> invoicePosition, ContactPerson receiver, InvoiceTemplate invoiceTemplate, BusinessPartner customer) {
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
        this.invoiceTemplate = invoiceTemplate;
        this.customer = customer;
    }

    public Invoice() {
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
    private SalesTax salexTax;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoicePosition> invoicePosition;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContactPerson receiver;

    @OneToOne(fetch = FetchType.LAZY)
    private InvoiceTemplate invoiceTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessPartner customer;

    public String getDescription() {
        return description;
    }

    public Invoice setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isPerMail() {
        return perMail;
    }

    public Invoice setPerMail(boolean perMail) {
        this.perMail = perMail;
        return this;
    }

    public String getPreText() {
        return preText;
    }

    public Invoice setPreText(String preText) {
        this.preText = preText;
        return this;
    }

    public String getPostText() {
        return postText;
    }

    public Invoice setPostText(String postText) {
        this.postText = postText;
        return this;
    }

    public LocalDate getServiceProvidedFrom() {
        return serviceProvidedFrom;
    }

    public Invoice setServiceProvidedFrom(LocalDate serviceProvidedFrom) {
        this.serviceProvidedFrom = serviceProvidedFrom;
        return this;
    }

    public LocalDate getServiceProvidedTo() {
        return serviceProvidedTo;
    }

    public Invoice setServiceProvidedTo(LocalDate serviceProvidedTo) {
        this.serviceProvidedTo = serviceProvidedTo;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Invoice setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Integer getGeneratedInvoiceId() {
        return generatedInvoiceId;
    }

    public Invoice setGeneratedInvoiceId(Integer generatedInvoiceId) {
        this.generatedInvoiceId = generatedInvoiceId;
        return this;
    }

    public SalesTax getSalexTax() {
        return salexTax;
    }

    public Invoice setSalexTax(SalesTax salexTax) {
        this.salexTax = salexTax;
        return this;
    }

    public List<InvoicePosition> getInvoicePosition() {
        return invoicePosition;
    }

    public Invoice setInvoicePosition(List<InvoicePosition> invoicePositions) {
        this.invoicePosition = invoicePositions;
        return this;
    }

    public ContactPerson getReceiver() {
        return receiver;
    }

    public Invoice setReceiver(ContactPerson receiver) {
        this.receiver = receiver;
        return this;
    }

    public InvoiceTemplate getInvoiceTemplate() {
        return invoiceTemplate;
    }

    public Invoice setInvoiceTemplate(InvoiceTemplate invoiceTemplate) {
        this.invoiceTemplate = invoiceTemplate;
        return this;
    }

    public BusinessPartner getCustomer() {
        return customer;
    }

    public Invoice setCustomer(BusinessPartner customer) {
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
            ", invoiceTemplateDAO=" + invoiceTemplate +
            ", customer=" + customer +
            ", id=" + id +
            '}';
    }
}
