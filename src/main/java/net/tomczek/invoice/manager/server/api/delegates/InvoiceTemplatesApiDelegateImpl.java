package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoiceTemplatesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import net.tomczek.invoice.manager.server.entities.InvoiceTemplate;
import net.tomczek.invoice.manager.server.converter.InvoiceTemplateConverter;
import net.tomczek.invoice.manager.server.services.IInvoiceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceTemplatesApiDelegateImpl implements InvoiceTemplatesApiDelegate {

    @Autowired
    public InvoiceTemplatesApiDelegateImpl(IInvoiceTemplateService invoiceTemplateService) {
        this.invoiceTemplateService = invoiceTemplateService;
    }

    private final IInvoiceTemplateService invoiceTemplateService;

    @Override
    public ResponseEntity<InvoiceTemplateDTO> deleteInvoiceTemplateById(Integer id) {
        InvoiceTemplate deleteInvoiceTemplate = invoiceTemplateService.deleteInvoiceTemplateById(id);
        if (deleteInvoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceTemplateDTO deleteInvoiceTemplateDTO = InvoiceTemplateConverter.toDTO(deleteInvoiceTemplate);
            return ResponseEntity.ok(deleteInvoiceTemplateDTO);
        }
    }

    @Override
    public ResponseEntity<List<InvoiceTemplateDTO>> getAllInvoiceTemplates() {
        List<InvoiceTemplate> invoiceTemplates = invoiceTemplateService.getAllInvoiceTemplates();
        List<InvoiceTemplateDTO> invoiceTemplateDTOS = InvoiceTemplateConverter.toDTO(invoiceTemplates);
        return ResponseEntity.ok().body(invoiceTemplateDTOS);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> getInvoiceTemplateById(Integer id) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateService.getInvoiceTemplateById(id);
        if (invoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceTemplateDTO invoiceTemplateDTO = InvoiceTemplateConverter.toDTO(invoiceTemplate);
            return ResponseEntity.ok().body(invoiceTemplateDTO);
        }
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> createInvoiceTemplate(InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = InvoiceTemplateConverter.toEntity(invoiceTemplateDTO);
        InvoiceTemplate createInvoiceTemplate = invoiceTemplateService.createInvoiceTemplate(invoiceTemplate);
        InvoiceTemplateDTO createdInvoiceTemplateDTO = InvoiceTemplateConverter.toDTO(createInvoiceTemplate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceTemplateDTO);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> updateInvoiceTemplateById(Integer id, InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = InvoiceTemplateConverter.toEntity(invoiceTemplateDTO);
        invoiceTemplate = invoiceTemplateService.updateInvoiceTemplateById(id, invoiceTemplate);
        if (invoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            invoiceTemplateDTO = InvoiceTemplateConverter.toDTO(invoiceTemplate);
            return ResponseEntity.ok().body(invoiceTemplateDTO);
        }
    }
}
