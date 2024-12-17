package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.models.FileWithContent;

import java.util.List;

public interface IInvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice deleteInvoiceById(Integer id);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Integer id);

    Invoice updateInvoiceById(Integer id, Invoice invoice);

    List<Invoice> getAllInvoiceByIds(List<Integer> ids);

    boolean exists(Integer id);

    Integer generateInvoicePdf(Integer id);
}
