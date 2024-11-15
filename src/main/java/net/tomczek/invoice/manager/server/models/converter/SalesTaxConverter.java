package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import net.tomczek.invoice.manager.server.entities.SalesTaxDAO;
import net.tomczek.invoice.manager.server.models.SalesTax;

import java.util.List;

public class SalesTaxConverter {

    public static SalesTaxDTO toDTO(SalesTax salesTax) {
        SalesTaxDTO salesTaxDTO = new SalesTaxDTO();
        salesTaxDTO.setId(salesTax.getId());
        salesTaxDTO.setRate(salesTax.getRate());
        salesTaxDTO.setName(salesTax.getName());
        return salesTaxDTO;
    }

    public static List<SalesTaxDTO> toDTO(List<SalesTax> salesTaxes) {
        return salesTaxes.stream().map(SalesTaxConverter::toDTO).toList();
    }

    public static SalesTax toEntityFromDTO(SalesTaxDTO salesTaxDTO) {
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDTO.getId());
        salesTax.setRate(salesTaxDTO.getRate());
        salesTax.setName(salesTaxDTO.getName());
        return salesTax;
    }

    public static List<SalesTax> toEntityFromDTO(List<SalesTaxDTO> salesTaxDTOs) {
        return salesTaxDTOs.stream().map(SalesTaxConverter::toEntityFromDTO).toList();
    }

    public static SalesTax toEntityFromDAO(SalesTaxDAO salesTaxDAO) {
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDAO.getId());
        salesTax.setRate(salesTaxDAO.getRate());
        salesTax.setName(salesTaxDAO.getName());
        return salesTax;
    }

    public static List<SalesTax> toEntityFromDAO(List<SalesTaxDAO> salesTaxDAOs) {
        return salesTaxDAOs.stream().map(SalesTaxConverter::toEntityFromDAO).toList();
    }

    public static SalesTaxDAO toDAO(SalesTax salesTax) {
        SalesTaxDAO salesTaxDAO = new SalesTaxDAO();
        salesTaxDAO.setId(salesTax.getId());
        salesTaxDAO.setRate(salesTax.getRate());
        salesTaxDAO.setName(salesTax.getName());
        return salesTaxDAO;
    }

    public static List<SalesTaxDAO> toDAO(List<SalesTax> salesTaxes) {
        return salesTaxes.stream().map(SalesTaxConverter::toDAO).toList();
    }
}
