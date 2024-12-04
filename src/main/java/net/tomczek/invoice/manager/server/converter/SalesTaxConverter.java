package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import net.tomczek.invoice.manager.server.entities.SalesTax;

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

    public static SalesTax toEntity(SalesTaxDTO salesTaxDTO) {
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDTO.getId());
        salesTax.setRate(salesTaxDTO.getRate());
        salesTax.setName(salesTaxDTO.getName());
        return salesTax;
    }

    public static List<SalesTax> toEntity(List<SalesTaxDTO> salesTaxeDTOs) {
        return salesTaxeDTOs.stream().map(SalesTaxConverter::toEntity).toList();
    }
}
