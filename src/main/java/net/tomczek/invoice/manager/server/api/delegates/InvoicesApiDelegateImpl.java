package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import net.tomczek.invoice.manager.server.models.Invoice;
import net.tomczek.invoice.manager.server.models.converter.InvoiceConverter;
import net.tomczek.invoice.manager.server.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicesApiDelegateImpl implements InvoicesApiDelegate {

    @Autowired
    public InvoicesApiDelegateImpl(IInvoiceService invoiceService, InvoiceConverter invoiceConverter) {
        this.invoiceService = invoiceService;
        this.invoiceConverter = invoiceConverter;
    }

    private final IInvoiceService invoiceService;
    private final InvoiceConverter invoiceConverter;

    @Override
    public ResponseEntity<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceConverter.toEntityFromDTO(invoiceDTO);
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        InvoiceDTO createdInvoiceDTO = invoiceConverter.toDTO(createdInvoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceDTO);
    }

    @Override
    public ResponseEntity<InvoiceDTO> deleteInvoiceById(Integer id) {
        Invoice deletedInvoice = invoiceService.deleteInvoiceById(id);
        if (deletedInvoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceDTO deletedInvoiceDTO = invoiceConverter.toDTO(deletedInvoice);
            return ResponseEntity.ok(deletedInvoiceDTO);
        }
    }

    @Override
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        List<InvoiceDTO> invoiceDTOS = invoiceConverter.toDTO(invoices);
        return ResponseEntity.ok().body(invoiceDTOS);
    }

    @Override
    public ResponseEntity<InvoiceDTO> getInvoiceById(Integer id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceDTO invoiceDTO = invoiceConverter.toDTO(invoice);
            return ResponseEntity.ok().body(invoiceDTO);
        }
    }

    @Override
    public ResponseEntity<InvoiceDTO> updateInvoiceById(Integer id, InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceConverter.toEntityFromDTO(invoiceDTO);
        Invoice updatedInvoice = invoiceService.updateInvoiceById(id, invoice);
        InvoiceDTO updatedInvoiceDTO = invoiceConverter.toDTO(updatedInvoice);
        return ResponseEntity.ok().body(updatedInvoiceDTO);
    }
}
