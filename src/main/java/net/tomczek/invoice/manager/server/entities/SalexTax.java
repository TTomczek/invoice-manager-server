package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;

/**
 * Umsatzsteuer
 */
@Entity
public class SalexTax extends BaseEntity<Integer> {

    private String name;

    private float rate;
}
