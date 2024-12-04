package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.SalesTaxesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import net.tomczek.invoice.manager.server.entities.SalesTax;
import net.tomczek.invoice.manager.server.converter.SalesTaxConverter;
import net.tomczek.invoice.manager.server.services.ISalesTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesTaxApiDelegateImpl implements SalesTaxesApiDelegate {

    @Autowired
    public SalesTaxApiDelegateImpl(ISalesTaxService salesTaxService) {
        this.salesTaxService = salesTaxService;
    }

    private final ISalesTaxService salesTaxService;

    @Override
    public ResponseEntity<SalesTaxDTO> deleteSalesTaxById(Integer id) {
        SalesTax deletedSalesTax = this.salesTaxService.deleteSalesTaxById(id);
        if (deletedSalesTax == null) {
            return ResponseEntity.notFound().build();
        } else {
            SalesTaxDTO deletedSalesTaxDTO = SalesTaxConverter.toDTO(deletedSalesTax);
            return ResponseEntity.ok(deletedSalesTaxDTO);
        }
    }

    @Override
    public ResponseEntity<List<SalesTaxDTO>> getAllSalesTaxes() {
        List<SalesTax> salesTaxes = this.salesTaxService.getAllSalesTaxs();
        List<SalesTaxDTO> salesTaxDTOs = SalesTaxConverter.toDTO(salesTaxes);
        return ResponseEntity.ok(salesTaxDTOs);
    }

    @Override
    public ResponseEntity<SalesTaxDTO> getSalesTaxById(Integer id) {
        SalesTax salesTax = this.salesTaxService.getSalesTaxById(id);
        if (salesTax == null) {
            return ResponseEntity.notFound().build();
        } else {
            SalesTaxDTO salesTaxDTO = SalesTaxConverter.toDTO(salesTax);
            return ResponseEntity.ok(salesTaxDTO);
        }
    }

    @Override
    public ResponseEntity<SalesTaxDTO> createSalesTax(SalesTaxDTO salesTaxDTO) {
        SalesTax salesTax = SalesTaxConverter.toEntity(salesTaxDTO);
        SalesTax createdSalesTax = this.salesTaxService.createSalesTax(salesTax);
        SalesTaxDTO createdSalesTaxDTO = SalesTaxConverter.toDTO(createdSalesTax);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSalesTaxDTO);
    }

    @Override
    public ResponseEntity<SalesTaxDTO> updateSalesTaxById(Integer id, SalesTaxDTO salesTaxDTO) {
        SalesTax salesTax = SalesTaxConverter.toEntity(salesTaxDTO);
        SalesTax updatedSalesTax = this.salesTaxService.updateSalesTaxById(id, salesTax);
        if (updatedSalesTax == null) {
            return ResponseEntity.notFound().build();
        } else {
            SalesTaxDTO updatedSalesTaxDTO = SalesTaxConverter.toDTO(updatedSalesTax);
            return ResponseEntity.ok().body(updatedSalesTaxDTO);
        }
    }
}
