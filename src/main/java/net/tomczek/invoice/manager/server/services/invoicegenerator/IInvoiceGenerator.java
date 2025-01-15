package net.tomczek.invoice.manager.server.services.invoicegenerator;

import net.tomczek.invoice.manager.server.entities.Invoice;

public interface IInvoiceGenerator {
    byte[] generateInvoice(Invoice invoice);
}
