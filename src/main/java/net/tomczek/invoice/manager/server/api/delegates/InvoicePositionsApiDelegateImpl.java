package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicePositionsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicePositionsApiDelegateImpl implements InvoicePositionsApiDelegate {

    @Override
    public ResponseEntity<InvoicePositionDTO> createPosition(InvoicePositionDTO invoicePositionDTO) {
        return InvoicePositionsApiDelegate.super.createPosition(invoicePositionDTO);
    }

    @Override
    public ResponseEntity<Void> deleteInvoicePositionById(Integer id) {
        return InvoicePositionsApiDelegate.super.deleteInvoicePositionById(id);
    }

    @Override
    public ResponseEntity<List<InvoicePositionDTO>> getAllInvoicePositions() {
        return InvoicePositionsApiDelegate.super.getAllInvoicePositions();
    }

    @Override
    public ResponseEntity<InvoicePositionDTO> getInvoicePositionById(Integer id) {
        return InvoicePositionsApiDelegate.super.getInvoicePositionById(id);
    }

    @Override
    public ResponseEntity<InvoicePositionDTO> updateInvoicePositionById(Integer id, InvoicePositionDTO invoicePositionDTO) {
        return InvoicePositionsApiDelegate.super.updateInvoicePositionById(id, invoicePositionDTO);
    }
}
