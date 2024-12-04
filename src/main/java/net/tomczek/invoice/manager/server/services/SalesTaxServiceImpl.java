package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.SalesTax;
import net.tomczek.invoice.manager.server.repositories.SalesTaxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesTaxServiceImpl implements ISalesTaxService {

    private static final Logger logger = LoggerFactory.getLogger(SalesTaxServiceImpl.class);

    @Autowired
    public SalesTaxServiceImpl(SalesTaxRepository salesTaxRepository) {
        this.salesTaxRepository = salesTaxRepository;
    }

    private final SalesTaxRepository salesTaxRepository;

    @Override
    public SalesTax createSalesTax(SalesTax salesTax) {
        SalesTax savedSalesTax = salesTaxRepository.save(salesTax);
        logger.debug("SalesTax saved: {}", savedSalesTax);
        return savedSalesTax;
    }

    @Override
    public SalesTax deleteSalesTaxById(Integer id) {
        SalesTax salesTax = salesTaxRepository.findById(id).orElse(null);
        if (salesTax == null) {
            return null;
        }
        logger.debug("SalesTax deleted: {}", salesTax);

        salesTaxRepository.delete(salesTax);
        return salesTax;
    }

    @Override
    public List<SalesTax> getAllSalesTaxs() {
        List<SalesTax> salesTaxes = salesTaxRepository.findAll();
        logger.debug("Fetched salesTaxes: {}", salesTaxes.size());
        return salesTaxes;
    }

    @Override
    public SalesTax getSalesTaxById(Integer id) {
        SalesTax salesTax = salesTaxRepository.findById(id).orElse(null);
        if (salesTax == null) {
            return null;
        }
        logger.debug("Fetched salesTax: {}", salesTax);
        return salesTax;
    }

    @Override
    public SalesTax updateSalesTaxById(Integer id, SalesTax salesTax) {
        SalesTax salesTaxToUpdate = salesTaxRepository.findById(id).orElse(null);
        if (salesTaxToUpdate == null) {
            return null;
        }

        salesTaxToUpdate.setRate(salesTax.getRate());
        salesTaxToUpdate.setName(salesTax.getName());
        SalesTax updatedSalesTax = salesTaxRepository.save(salesTaxToUpdate);
        logger.debug("SalesTax updated: {}", updatedSalesTax);
        return updatedSalesTax;
    }

    @Override
    public boolean exists(Integer id) {
        return salesTaxRepository.existsById(id);
    }
}
