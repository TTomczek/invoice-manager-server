package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoicePositionDAO;
import net.tomczek.invoice.manager.server.models.InvoicePosition;
import net.tomczek.invoice.manager.server.models.converter.InvoicePositionConverter;
import net.tomczek.invoice.manager.server.repositories.InvoicePositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoicePositionsServiceImpl implements IInvoicePositionsService {

    @Autowired
    public InvoicePositionsServiceImpl(InvoicePositionsRepository invoicePositionsRepository) {
        this.invoicePositionsRepository = invoicePositionsRepository;
    }

    private final InvoicePositionsRepository invoicePositionsRepository;

    @Override
    public InvoicePosition createInvoicePosition(InvoicePosition invoicePosition) {
        InvoicePositionDAO invoicePositionDAOToSave = InvoicePositionConverter.toDAO(invoicePosition);
        InvoicePositionDAO savedInvoicePositionDAO = invoicePositionsRepository.save(invoicePositionDAOToSave);
        return InvoicePositionConverter.toEntityFromDAO(savedInvoicePositionDAO);
    }

    @Override
    public InvoicePosition deleteInvoicePositionById(Integer id) {
        InvoicePositionDAO invoicePositionDAO = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePositionDAO == null) {
            return null;
        }

        invoicePositionsRepository.deleteById(id);
        return InvoicePositionConverter.toEntityFromDAO(invoicePositionDAO);
    }

    @Override
    public List<InvoicePosition> getAllInvoicePositions() {
        List<InvoicePositionDAO> invoicePositionDAOs = invoicePositionsRepository.findAll();
        return InvoicePositionConverter.toEntityFromDAO(invoicePositionDAOs);
    }

    @Override
    public InvoicePosition getInvoicePositionById(Integer id) {
        InvoicePositionDAO invoicePositionDAO = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePositionDAO == null) {
            return null;
        }

        return InvoicePositionConverter.toEntityFromDAO(invoicePositionDAO);
    }

    @Override
    public InvoicePosition updateInvoicePositionById(Integer id, InvoicePosition invoicePosition) {
        InvoicePositionDAO invoicePositionDAO = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePositionDAO == null) {
            return null;
        }

        invoicePositionDAO.setDescription(invoicePosition.getDescription());
        invoicePositionDAO.setQuantity(invoicePosition.getQuantity());
        invoicePositionDAO.setUnitEt(invoicePosition.getUnit());
        invoicePositionDAO.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());

        InvoicePositionDAO updatedInvoicePositionDAO = invoicePositionsRepository.save(invoicePositionDAO);
        return InvoicePositionConverter.toEntityFromDAO(updatedInvoicePositionDAO);
    }
}
