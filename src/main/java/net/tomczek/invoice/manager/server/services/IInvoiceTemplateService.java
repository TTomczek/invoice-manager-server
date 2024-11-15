package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.InvoiceTemplate;

import java.util.List;

public interface IInvoiceTemplateService {

    public InvoiceTemplate createInvoiceTemplate(InvoiceTemplate invoiceTemplate);

    public InvoiceTemplate deleteInvoiceTemplateById(Integer id);

    public List<InvoiceTemplate> getAllInvoiceTemplates();

    public InvoiceTemplate getInvoiceTemplateById(Integer id);

    public InvoiceTemplate updateInvoiceTemplateById(Integer id, InvoiceTemplate invoiceTemplate);
}
