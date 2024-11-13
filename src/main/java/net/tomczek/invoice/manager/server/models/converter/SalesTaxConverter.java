package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import net.tomczek.invoice.manager.server.entities.SalesTaxDAO;
import net.tomczek.invoice.manager.server.models.SalesTax;
import org.springframework.stereotype.Service;

@Service
public class SalesTaxConverter {

    public SalesTaxDTO toDTO(SalesTax salesTax) {
        SalesTaxDTO salesTaxDTO = new SalesTaxDTO();
        salesTaxDTO.setId(salesTax.getId());
        salesTaxDTO.setRate(salesTax.getRate());
        salesTaxDTO.setName(salesTax.getName());
        return salesTaxDTO;
    }

    public SalesTax toEntityFromDTO(SalesTaxDTO salesTaxDTO) {
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDTO.getId());
        salesTax.setRate(salesTaxDTO.getRate());
        salesTax.setName(salesTaxDTO.getName());
        return salesTax;
    }

    public SalesTax toEntityFromDAO(SalesTaxDAO salesTaxDAO) {
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDAO.getId());
        salesTax.setRate(salesTaxDAO.getRate());
        salesTax.setName(salesTaxDAO.getName());
        return salesTax;
    }

    public SalesTaxDAO toDAO(SalesTax salesTax) {
        SalesTaxDAO salesTaxDAO = new SalesTaxDAO();
        salesTaxDAO.setId(salesTax.getId());
        salesTaxDAO.setRate(salesTax.getRate());
        salesTaxDAO.setName(salesTax.getName());
        return salesTaxDAO;
    }
}
