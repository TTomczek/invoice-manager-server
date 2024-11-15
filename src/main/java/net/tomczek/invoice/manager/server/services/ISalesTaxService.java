package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.SalesTax;

import java.util.List;

public interface ISalesTaxService {

    public SalesTax createSalesTax(SalesTax salesTax);

    public SalesTax deleteSalesTaxById(Integer id);

    public List<SalesTax> getAllSalesTaxs();

    public SalesTax getSalesTaxById(Integer id);

    public SalesTax updateSalesTaxById(Integer id, SalesTax salesTax);
}
