package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.SalesTaxDAO;
import net.tomczek.invoice.manager.server.models.SalesTax;
import net.tomczek.invoice.manager.server.models.converter.SalesTaxConverter;
import net.tomczek.invoice.manager.server.repositories.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesTaxServiceImpl implements ISalesTaxService {

    @Autowired
    public SalesTaxServiceImpl(SalesTaxRepository salesTaxRepository) {
        this.salesTaxRepository = salesTaxRepository;
    }

    private final SalesTaxRepository salesTaxRepository;

    @Override
    public SalesTax createSalesTax(SalesTax salesTax) {
        SalesTaxDAO salesTaxDAO = SalesTaxConverter.toDAO(salesTax);
        SalesTaxDAO savedSalesTaxDAO = salesTaxRepository.save(salesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(savedSalesTaxDAO);
    }

    @Override
    public SalesTax deleteSalesTaxById(Integer id) {
        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(id).orElse(null);
        if (salesTaxDAO == null) {
            return null;
        }

        salesTaxRepository.delete(salesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(salesTaxDAO);
    }

    @Override
    public List<SalesTax> getAllSalesTaxs() {
        List<SalesTaxDAO> salesTaxDAOs = salesTaxRepository.findAll();
        return SalesTaxConverter.toEntityFromDAO(salesTaxDAOs);
    }

    @Override
    public SalesTax getSalesTaxById(Integer id) {
        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(id).orElse(null);
        return SalesTaxConverter.toEntityFromDAO(salesTaxDAO);
    }

    @Override
    public SalesTax updateSalesTaxById(Integer id, SalesTax salesTax) {
        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(id).orElse(null);
        if (salesTaxDAO == null) {
            return null;
        }

        salesTaxDAO.setRate(salesTax.getRate());
        salesTaxDAO.setName(salesTax.getName());
        SalesTaxDAO updatedSalesTaxDAO = salesTaxRepository.save(salesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(updatedSalesTaxDAO);
    }

    @Override
    public boolean exists(Integer id) {
        return salesTaxRepository.existsById(id);
    }
}
