package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoicePosition;
import net.tomczek.invoice.manager.server.converter.InvoicePositionConverter;
import net.tomczek.invoice.manager.server.repositories.InvoicePositionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoicePositionServiceImpl implements IInvoicePositionService {

    private static final Logger logger = LoggerFactory.getLogger(InvoicePositionServiceImpl.class);

    @Autowired
    public InvoicePositionServiceImpl(InvoicePositionsRepository invoicePositionsRepository) {
        this.invoicePositionsRepository = invoicePositionsRepository;
    }

    private final InvoicePositionsRepository invoicePositionsRepository;

    @Override
    public InvoicePosition createInvoicePosition(InvoicePosition invoicePosition) {
        InvoicePosition savedInvoicePosition = invoicePositionsRepository.save(invoicePosition);
        logger.debug("InvoicePosition saved: {}", savedInvoicePosition);
        return savedInvoicePosition;
    }

    @Override
    public InvoicePosition deleteInvoicePositionById(Integer id) {
        InvoicePosition invoicePosition = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePosition == null) {
            return null;
        }

        invoicePositionsRepository.deleteById(id);
        logger.debug("InvoicePosition deleted: {}", invoicePosition);
        return invoicePosition;
    }

    @Override
    public List<InvoicePosition> getAllInvoicePositions() {
        List<InvoicePosition> invoicePositions = invoicePositionsRepository.findAll();
        logger.debug("Fetched invoicePositions: {}", invoicePositions.size());
        return invoicePositions;
    }

    @Override
    public InvoicePosition getInvoicePositionById(Integer id) {
        InvoicePosition invoicePosition = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePosition == null) {
            return null;
        }
        logger.debug("Fetched invoicePosition: {}", invoicePosition);

        return invoicePosition;
    }

    @Override
    public InvoicePosition updateInvoicePositionById(Integer id, InvoicePosition invoicePosition) {
        InvoicePosition invoicePositionToUpdate = invoicePositionsRepository.findById(id).orElse(null);
        if (invoicePositionToUpdate == null) {
            return null;
        }

        invoicePositionToUpdate.setDescription(invoicePosition.getDescription());
        invoicePositionToUpdate.setQuantity(invoicePosition.getQuantity());
        invoicePositionToUpdate.setUnitEt(invoicePosition.getUnitEt());
        invoicePositionToUpdate.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());

        InvoicePosition updatedInvoicePosition = invoicePositionsRepository.save(invoicePositionToUpdate);
        logger.debug("InvoicePosition updated: {}", updatedInvoicePosition);
        return updatedInvoicePosition;
    }

    @Override
    public List<InvoicePosition> getAllInvoicePositionByIds(List<Integer> ids) {
        List<InvoicePosition> invoicePositions = invoicePositionsRepository.findAllById(ids);
        logger.debug("Fetched invoicePositions by ids: {}", invoicePositions.size());
        return invoicePositions;
    }

    @Override
    public boolean exists(Integer id) {
        return invoicePositionsRepository.existsById(id);
    }
}
