package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Umsatzsteuer
 */
@Table(name = "sales_taxes")
@Entity
public class SalesTaxDAO extends BaseEntity<Integer> {

    public SalesTaxDAO(Integer id, String name, BigDecimal rate) {
        super(id);
        this.name = name;
        this.rate = rate;
    }

    public SalesTaxDAO() {
    }

    private String name;

    private BigDecimal rate;

    public String getName() {
        return name;
    }

    public SalesTaxDAO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public SalesTaxDAO setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public String toString() {
        return "SalesTaxDAO{" +
            "name='" + name + '\'' +
            ", rate=" + rate +
            ", id=" + id +
            '}';
    }
}
