package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.SalesTax;

import java.util.List;

public interface ISalesTaxService {

    SalesTax createSalesTax(SalesTax salesTax);

    SalesTax deleteSalesTaxById(Integer id);

    List<SalesTax> getAllSalesTaxs();

    SalesTax getSalesTaxById(Integer id);

    SalesTax updateSalesTaxById(Integer id, SalesTax salesTax);

    boolean exists(Integer id);
}
