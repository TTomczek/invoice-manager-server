package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.SalesTaxDAO;
import net.tomczek.invoice.manager.server.models.SalesTax;
import net.tomczek.invoice.manager.server.models.converter.SalesTaxConverter;
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
        SalesTaxDAO salesTaxDAO = SalesTaxConverter.toDAO(salesTax);
        SalesTaxDAO savedSalesTaxDAO = salesTaxRepository.save(salesTaxDAO);
        logger.debug("SalesTax saved: {}", savedSalesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(savedSalesTaxDAO);
    }

    @Override
    public SalesTax deleteSalesTaxById(Integer id) {
        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(id).orElse(null);
        if (salesTaxDAO == null) {
            return null;
        }
        logger.debug("SalesTax deleted: {}", salesTaxDAO);

        salesTaxRepository.delete(salesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(salesTaxDAO);
    }

    @Override
    public List<SalesTax> getAllSalesTaxs() {
        List<SalesTaxDAO> salesTaxDAOs = salesTaxRepository.findAll();
        logger.debug("Fetched salesTaxes: {}", salesTaxDAOs.size());
        return SalesTaxConverter.toEntityFromDAO(salesTaxDAOs);
    }

    @Override
    public SalesTax getSalesTaxById(Integer id) {
        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(id).orElse(null);
        if (salesTaxDAO == null) {
            return null;
        }
        logger.debug("Fetched salesTax: {}", salesTaxDAO);
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
        logger.debug("SalesTax updated: {}", updatedSalesTaxDAO);
        return SalesTaxConverter.toEntityFromDAO(updatedSalesTaxDAO);
    }

    @Override
    public boolean exists(Integer id) {
        return salesTaxRepository.existsById(id);
    }
}
