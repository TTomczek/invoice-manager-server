package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoiceTemplatesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import net.tomczek.invoice.manager.server.models.InvoiceTemplate;
import net.tomczek.invoice.manager.server.models.converter.InvoiceTemplateConverter;
import net.tomczek.invoice.manager.server.services.IInvoiceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceTemplatesApiDelegateImpl implements InvoiceTemplatesApiDelegate {

    @Autowired
    public InvoiceTemplatesApiDelegateImpl(IInvoiceTemplateService invoiceTemplateService, InvoiceTemplateConverter invoiceTemplateConverter) {
        this.invoiceTemplateService = invoiceTemplateService;
        this.invoiceTemplateConverter = invoiceTemplateConverter;
    }

    private final IInvoiceTemplateService invoiceTemplateService;
    private final InvoiceTemplateConverter invoiceTemplateConverter;

    @Override
    public ResponseEntity<InvoiceTemplateDTO> deleteInvoiceTemplateById(Integer id) {
        InvoiceTemplate deleteInvoiceTemplate = invoiceTemplateService.deleteInvoiceTemplateById(id);
        if (deleteInvoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceTemplateDTO deleteInvoiceTemplateDTO = invoiceTemplateConverter.toDTO(deleteInvoiceTemplate);
            return ResponseEntity.ok(deleteInvoiceTemplateDTO);
        }
    }

    @Override
    public ResponseEntity<List<InvoiceTemplateDTO>> getAllInvoiceTemplates() {
        List<InvoiceTemplate> invoiceTemplates = invoiceTemplateService.getAllInvoiceTemplates();
        List<InvoiceTemplateDTO> invoiceTemplateDTOS = invoiceTemplateConverter.toDTO(invoiceTemplates);
        return ResponseEntity.ok().body(invoiceTemplateDTOS);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> getInvoiceTemplateById(Integer id) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateService.getInvoiceTemplateById(id);
        if (invoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoiceTemplateDTO invoiceTemplateDTO = invoiceTemplateConverter.toDTO(invoiceTemplate);
            return ResponseEntity.ok().body(invoiceTemplateDTO);
        }
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> createInvoiceTemplate(InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateConverter.toEntityFromDTO(invoiceTemplateDTO);
        InvoiceTemplate createInvoiceTemplate = invoiceTemplateService.createInvoiceTemplate(invoiceTemplate);
        InvoiceTemplateDTO createdInvoiceTemplateDTO = invoiceTemplateConverter.toDTO(createInvoiceTemplate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceTemplateDTO);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> updateInvoiceTemplateById(Integer id, InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateConverter.toEntityFromDTO(invoiceTemplateDTO);
        invoiceTemplate = invoiceTemplateService.updateInvoiceTemplateById(id, invoiceTemplate);
        if (invoiceTemplate == null) {
            return ResponseEntity.notFound().build();
        } else {
            invoiceTemplateDTO = invoiceTemplateConverter.toDTO(invoiceTemplate);
            return ResponseEntity.ok().body(invoiceTemplateDTO);
        }
    }
}
