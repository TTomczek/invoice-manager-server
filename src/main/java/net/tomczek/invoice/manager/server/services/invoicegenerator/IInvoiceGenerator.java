package net.tomczek.invoice.manager.server.services.invoicegenerator;

import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.models.FileWithContent;

public interface IInvoiceGenerator {
    byte[] generateInvoice(Invoice invoice);
}
