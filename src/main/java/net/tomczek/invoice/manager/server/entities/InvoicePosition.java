package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.*;

@Entity
public class InvoicePosition extends BaseEntity<Integer> {

    private String description;

    private float amount;

    @Enumerated(EnumType.STRING)
    private UnitET unitEt;

    private int pricePerUnitInCents;

    @ManyToOne
    private Invoice invoice;
}
