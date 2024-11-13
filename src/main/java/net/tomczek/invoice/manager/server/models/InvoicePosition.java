package net.tomczek.invoice.manager.server.models;

import net.tomczek.invoice.manager.server.entities.UnitET;

import java.math.BigDecimal;

/**
 * Internal model for InvoicePositionDAO/InvoicePositionDTO
 */
public class InvoicePosition {

    private Integer id;
    private String description;
    private float quantity;
    private UnitET unit;
    private BigDecimal pricePerUnitInCents;
    private Invoice invoice;

    public InvoicePosition(Integer id, String description, float quantity, UnitET unit, BigDecimal pricePerUnitInCents, Invoice invoice) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnitInCents = pricePerUnitInCents;
        this.invoice = invoice;
    }

    public InvoicePosition() {}

    public Integer getId() {
        return id;
    }

    public InvoicePosition setId(Integer id) {
        this.id = id;
        return this;
    }

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

    public UnitET getUnit() {
        return unit;
    }

    public InvoicePosition setUnit(UnitET unit) {
        this.unit = unit;
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
}
