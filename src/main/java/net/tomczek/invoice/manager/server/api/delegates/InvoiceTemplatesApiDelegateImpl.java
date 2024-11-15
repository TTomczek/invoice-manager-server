package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoiceTemplatesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceTemplatesApiDelegateImpl implements InvoiceTemplatesApiDelegate {

    @Override
    public ResponseEntity<Void> deleteInvoiceTemplateById(Integer id) {
        return InvoiceTemplatesApiDelegate.super.deleteInvoiceTemplateById(id);
    }

    @Override
    public ResponseEntity<List<InvoiceTemplateDTO>> getAllInvoiceTemplates() {
        return InvoiceTemplatesApiDelegate.super.getAllInvoiceTemplates();
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> getInvoiceTemplateById(Integer id) {
        return InvoiceTemplatesApiDelegate.super.getInvoiceTemplateById(id);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> invoiceTemplatesPost(InvoiceTemplateDTO invoiceTemplateDTO) {
        return InvoiceTemplatesApiDelegate.super.invoiceTemplatesPost(invoiceTemplateDTO);
    }

    @Override
    public ResponseEntity<InvoiceTemplateDTO> updateInvoiceTemplateById(Integer id, InvoiceTemplateDTO invoiceTemplateDTO) {
        return InvoiceTemplatesApiDelegate.super.updateInvoiceTemplateById(id, invoiceTemplateDTO);
    }
}
