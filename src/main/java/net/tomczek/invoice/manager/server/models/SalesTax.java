package net.tomczek.invoice.manager.server.models;

import java.math.BigDecimal;

/**
 * Internal model for SalesTaxDAO/SalesTaxDTO
 */
public class SalesTax {

    private Integer id;
    private String name;
    private BigDecimal rate;

    public SalesTax(Integer id, String name, BigDecimal rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public SalesTax() {}

    public Integer getId() {
        return id;
    }

    public SalesTax setId(Integer id) {
        this.id = id;
        return this;
    }

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
}
