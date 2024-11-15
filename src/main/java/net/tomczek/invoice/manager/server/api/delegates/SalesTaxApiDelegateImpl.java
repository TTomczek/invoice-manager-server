package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.SalesTaxesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesTaxApiDelegateImpl implements SalesTaxesApiDelegate {

    @Override
    public ResponseEntity<Void> deleteSalesTaxById(Integer id) {
        return SalesTaxesApiDelegate.super.deleteSalesTaxById(id);
    }

    @Override
    public ResponseEntity<List<SalesTaxDTO>> getAllSalesTaxes() {
        return SalesTaxesApiDelegate.super.getAllSalesTaxes();
    }

    @Override
    public ResponseEntity<SalesTaxDTO> getSalesTaxById(Integer id) {
        return SalesTaxesApiDelegate.super.getSalesTaxById(id);
    }

    @Override
    public ResponseEntity<SalesTaxDTO> salesTaxesPost(SalesTaxDTO salesTaxDTO) {
        return SalesTaxesApiDelegate.super.salesTaxesPost(salesTaxDTO);
    }

    @Override
    public ResponseEntity<SalesTaxDTO> updateSalesTaxById(Integer id, SalesTaxDTO salesTaxDTO) {
        return SalesTaxesApiDelegate.super.updateSalesTaxById(id, salesTaxDTO);
    }
}
