package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import net.tomczek.invoice.manager.server.entities.SalesTax;

import java.util.List;

public class SalesTaxConverter {

    public static SalesTaxDTO toDTO(SalesTax salesTax) {
        if (salesTax == null) {
            return null;
        }
        SalesTaxDTO salesTaxDTO = new SalesTaxDTO();
        salesTaxDTO.setId(salesTax.getId());
        salesTaxDTO.setRate(salesTax.getRate());
        salesTaxDTO.setName(salesTax.getName());
        return salesTaxDTO;
    }

    public static List<SalesTaxDTO> toDTO(List<SalesTax> salesTaxes) {
        if (salesTaxes == null) {
            return null;
        }
        return salesTaxes.stream().map(SalesTaxConverter::toDTO).toList();
    }

    public static SalesTax toEntity(SalesTaxDTO salesTaxDTO) {
        if (salesTaxDTO == null) {
            return null;
        }
        SalesTax salesTax = new SalesTax();
        salesTax.setId(salesTaxDTO.getId());
        salesTax.setRate(salesTaxDTO.getRate());
        salesTax.setName(salesTaxDTO.getName());
        return salesTax;
    }

    public static List<SalesTax> toEntity(List<SalesTaxDTO> salesTaxeDTOs) {
        if (salesTaxeDTOs == null) {
            return null;
        }
        return salesTaxeDTOs.stream().map(SalesTaxConverter::toEntity).toList();
    }
}
