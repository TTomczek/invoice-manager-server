package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "invoice_positions")
@Entity
public class InvoicePosition extends BaseEntity<Integer> {

    public InvoicePosition(Integer id, String description, float quantity, UnitET unitEt, BigDecimal pricePerUnitInCents, Invoice invoice) {
        super(id);
        this.description = description;
        this.quantity = quantity;
        this.unitEt = unitEt;
        this.pricePerUnitInCents = pricePerUnitInCents;
        this.invoice = invoice;
    }

    public InvoicePosition() {
    }

    private String description;

    private float quantity;

    @Enumerated(EnumType.STRING)
    private UnitET unitEt;

    private BigDecimal pricePerUnitInCents;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    public String getDescription() {
        return description;
    }

    public InvoicePosition setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getQuantity() {
        return quantity;
    }

    public InvoicePosition setQuantity(float quantity) {
        this.quantity = quantity;
        return this;
    }

    public UnitET getUnitEt() {
        return unitEt;
    }

    public InvoicePosition setUnitEt(UnitET unitEt) {
        this.unitEt = unitEt;
        return this;
    }

    public BigDecimal getPricePerUnitInCents() {
        return pricePerUnitInCents;
    }

    public InvoicePosition setPricePerUnitInCents(BigDecimal pricePerUnitInCents) {
        this.pricePerUnitInCents = pricePerUnitInCents;
        return this;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoicePosition setInvoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    @Override
    public String toString() {
        return "InvoicePosition" +
            "description='" + description + '\'' +
            ", quantity=" + quantity +
            ", unitEt=" + unitEt +
            ", pricePerUnitInCents=" + pricePerUnitInCents +
            ", invoice=" + invoice.getDescription() +
            ", id=" + id +
            '}';
    }
}
