package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.server.converter.InvoiceConverter;
import net.tomczek.invoice.manager.server.converter.InvoicePositionConverter;
import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.entities.InvoicePosition;
import net.tomczek.invoice.manager.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicesApiDelegateImpl implements InvoicesApiDelegate {

    @Autowired
    public InvoicesApiDelegateImpl(IInvoiceService invoiceService, IContactPersonService contactPersonService, IBusinessPartnerService businessPartnerService, IInvoicePositionService invoicePositionService, IInvoiceTemplateService invoiceTemplateService, ISalesTaxService salesTaxService) {
        this.invoiceService = invoiceService;
        this.contactPersonService = contactPersonService;
        this.businessPartnerService = businessPartnerService;
        this.invoicePositionService = invoicePositionService;
        this.invoiceTemplateService = invoiceTemplateService;
        this.salesTaxService = salesTaxService;
    }

    private final IInvoiceService invoiceService;
    private final IContactPersonService contactPersonService;
    private final IBusinessPartnerService businessPartnerService;
    private final IInvoicePositionService invoicePositionService;
    private final IInvoiceTemplateService invoiceTemplateService;
    private final ISalesTaxService salesTaxService;

    @Override
    public ResponseEntity<InvoiceDTO> createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = InvoiceConverter.toEntity(invoiceDTO, invoiceTemplateService, contactPersonService, businessPartnerService, invoicePositionService, salesTaxService);
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        InvoiceDTO createdInvoiceDTO = InvoiceConverter.toDTO(createdInvoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceDTO);
    }

    @Override
    public ResponseEntity<InvoiceDTO> deleteInvoiceById(Integer id) {
        Invoice deletedInvoice = invoiceService.deleteInvoiceById(id);
        if (deletedInvoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceDTO deletedInvoiceDTO = InvoiceConverter.toDTO(deletedInvoice);
            return ResponseEntity.ok(deletedInvoiceDTO);
        }
    }

    @Override
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices(Boolean paid, Integer customerNumber, Integer receiver, String orderNumber) {
        List<Invoice> invoices = invoiceService.getAllInvoices(paid, customerNumber, receiver, orderNumber);
        List<InvoiceDTO> invoiceDTOS = InvoiceConverter.toDTO(invoices);
        return ResponseEntity.ok().body(invoiceDTOS);
    }

    @Override
    public ResponseEntity<InvoiceDTO> getInvoiceById(Integer id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceDTO invoiceDTO = InvoiceConverter.toDTO(invoice);
            return ResponseEntity.ok().body(invoiceDTO);
        }
    }

    @Override
    public ResponseEntity<InvoiceDTO> updateInvoiceById(Integer id, InvoiceDTO invoiceDTO) {
        Invoice invoice = InvoiceConverter.toEntity(invoiceDTO, invoiceTemplateService, contactPersonService, businessPartnerService, invoicePositionService, salesTaxService);
        Invoice updatedInvoice = invoiceService.updateInvoiceById(id, invoice);
        InvoiceDTO updatedInvoiceDTO = InvoiceConverter.toDTO(updatedInvoice);
        return ResponseEntity.ok().body(updatedInvoiceDTO);
    }

    @Override
    public ResponseEntity<List<InvoicePositionDTO>> getAllPositionsOfInvoice(Integer id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<InvoicePosition> invoicePositions = invoice.getInvoicePositions();
            List<InvoicePositionDTO> invoicePositionDTOS = InvoicePositionConverter.toDTO(invoicePositions);
            return ResponseEntity.ok().body(invoicePositionDTOS);
        }
    }

    @Override
    public ResponseEntity<Integer> getInvoicePdfById(Integer id) {
        Integer fileId = invoiceService.generateInvoicePdf(id);

        if (fileId == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().body(fileId);
        }
    }
}
