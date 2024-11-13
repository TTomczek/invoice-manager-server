package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "invoice_positions")
@Entity
public class InvoicePositionDAO extends BaseEntity<Integer> {

    public InvoicePositionDAO(Integer id, String description, float quantity, UnitET unitEt, BigDecimal pricePerUnitInCents, InvoiceDAO invoiceDAO) {
        super(id);
        this.description = description;
        this.quantity = quantity;
        this.unitEt = unitEt;
        this.pricePerUnitInCents = pricePerUnitInCents;
        this.invoiceDAO = invoiceDAO;
    }

    public InvoicePositionDAO() {
    }

    private String description;

    private float quantity;

    @Enumerated(EnumType.STRING)
    private UnitET unitEt;

    private BigDecimal pricePerUnitInCents;

    @ManyToOne(fetch = FetchType.LAZY)
    private InvoiceDAO invoiceDAO;

    public String getDescription() {
        return description;
    }

    public InvoicePositionDAO setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getQuantity() {
        return quantity;
    }

    public InvoicePositionDAO setQuantity(float quantity) {
        this.quantity = quantity;
        return this;
    }

    public UnitET getUnitEt() {
        return unitEt;
    }

    public InvoicePositionDAO setUnitEt(UnitET unitEt) {
        this.unitEt = unitEt;
        return this;
    }

    public BigDecimal getPricePerUnitInCents() {
        return pricePerUnitInCents;
    }

    public InvoicePositionDAO setPricePerUnitInCents(BigDecimal pricePerUnitInCents) {
        this.pricePerUnitInCents = pricePerUnitInCents;
        return this;
    }

    public InvoiceDAO getInvoiceDAO() {
        return invoiceDAO;
    }

    public InvoicePositionDAO setInvoiceDAO(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
        return this;
    }
}
