package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.Invoice;

import java.util.List;

public interface IInvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice deleteInvoiceById(Integer id);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Integer id);

    Invoice updateInvoiceById(Integer id, Invoice invoice);

    boolean exists(Integer id);
}
