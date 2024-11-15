package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.InvoicePosition;

import java.util.List;

public interface IInvoicePositionsService {

    public InvoicePosition createInvoicePosition(InvoicePosition invoicePosition);

    public InvoicePosition deleteInvoicePositionById(Integer id);

    public List<InvoicePosition> getAllInvoicePositions();

    public InvoicePosition getInvoicePositionById(Integer id);

    public InvoicePosition updateInvoicePositionById(Integer id, InvoicePosition invoicePosition);
}
