package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.InvoiceTemplate;

import java.util.List;

public interface IInvoiceTemplateService {

    InvoiceTemplate createInvoiceTemplate(InvoiceTemplate invoiceTemplate);

    InvoiceTemplate deleteInvoiceTemplateById(Integer id);

    List<InvoiceTemplate> getAllInvoiceTemplates();

    InvoiceTemplate getInvoiceTemplateById(Integer id);

    InvoiceTemplate updateInvoiceTemplateById(Integer id, InvoiceTemplate invoiceTemplate);

    boolean exists(Integer id);
}
