package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.InvoicePosition;

import java.util.List;

public interface IInvoicePositionsService {

    InvoicePosition createInvoicePosition(InvoicePosition invoicePosition);

    InvoicePosition deleteInvoicePositionById(Integer id);

    List<InvoicePosition> getAllInvoicePositions();

    InvoicePosition getInvoicePositionById(Integer id);

    InvoicePosition updateInvoicePositionById(Integer id, InvoicePosition invoicePosition);

    boolean exists(Integer id);
}
