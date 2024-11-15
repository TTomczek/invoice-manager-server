package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.Invoice;

import java.util.List;

public interface IInvoiceService {

    public Invoice createInvoice(Invoice invoice);

    public Invoice deleteInvoiceById(Integer id);

    public List<Invoice> getAllInvoices();

    public Invoice getInvoiceById(Integer id);

    public Invoice updateInvoiceById(Integer id, Invoice invoice);
}
