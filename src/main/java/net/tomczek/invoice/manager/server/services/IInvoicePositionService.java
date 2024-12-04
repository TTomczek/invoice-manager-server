package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoicePosition;

import java.util.List;

public interface IInvoicePositionService {

    InvoicePosition createInvoicePosition(InvoicePosition invoicePosition);

    InvoicePosition deleteInvoicePositionById(Integer id);

    List<InvoicePosition> getAllInvoicePositions();

    InvoicePosition getInvoicePositionById(Integer id);

    InvoicePosition updateInvoicePositionById(Integer id, InvoicePosition invoicePosition);

    List<InvoicePosition> getAllInvoicePositionByIds(List<Integer> ids);

    boolean exists(Integer id);
}
