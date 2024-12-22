package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Umsatzsteuer
 */
@Table(name = "sales_taxes")
@Entity
public class SalesTax extends BaseEntity<Integer> {

    public SalesTax(Integer id, String name, BigDecimal rate) {
        super(id);
        this.name = name;
        this.rate = rate;
    }

    public SalesTax() {
    }

    private String name;

    private BigDecimal rate;

    public String getName() {
        return name;
    }

    public SalesTax setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public SalesTax setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public String toString() {
        return "SalesTax" +
            "name='" + name + '\'' +
            ", rate=" + rate +
            ", id=" + id +
            '}';
    }
}
