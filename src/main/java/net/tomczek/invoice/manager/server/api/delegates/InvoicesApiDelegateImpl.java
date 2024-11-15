package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicesApiDelegateImpl implements InvoicesApiDelegate {

    @Override
    public ResponseEntity<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO) {
        return InvoicesApiDelegate.super.createInvoice(invoiceDTO);
    }

    @Override
    public ResponseEntity<Void> deleteInvoiceById(Integer id) {
        return InvoicesApiDelegate.super.deleteInvoiceById(id);
    }

    @Override
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return InvoicesApiDelegate.super.getAllInvoices();
    }

    @Override
    public ResponseEntity<InvoiceDTO> getInvoiceById(Integer id) {
        return InvoicesApiDelegate.super.getInvoiceById(id);
    }

    @Override
    public ResponseEntity<InvoiceDTO> updateInvoiceById(Integer id, InvoiceDTO invoiceDTO) {
        return InvoicesApiDelegate.super.updateInvoiceById(id, invoiceDTO);
    }
}
