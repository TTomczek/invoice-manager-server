package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.models.Invoice;
import net.tomczek.invoice.manager.server.models.converter.*;
import net.tomczek.invoice.manager.server.repositories.InvoicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    public InvoiceServiceImpl(InvoiceConverter invoiceConverter, InvoicesRepository invoiceRepository, ContactPersonConverter contactPersonConverter, InvoiceTemplateConverter invoiceTemplateConverter) {
        this.invoiceConverter = invoiceConverter;
        this.invoiceRepository = invoiceRepository;
        this.contactPersonConverter = contactPersonConverter;
        this.invoiceTemplateConverter = invoiceTemplateConverter;
    }

    private final InvoiceConverter invoiceConverter;
    private final InvoicesRepository invoiceRepository;
    private final ContactPersonConverter contactPersonConverter;
    private final InvoiceTemplateConverter invoiceTemplateConverter;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        InvoiceDAO invoiceDAOToSave = invoiceConverter.toDAO(invoice);
        InvoiceDAO savedInvoiceDAO = invoiceRepository.save(invoiceDAOToSave);
        logger.debug("Invoice saved: {}", savedInvoiceDAO);
        return invoiceConverter.toEntityFromDAO(savedInvoiceDAO);
    }

    @Override
    public Invoice deleteInvoiceById(Integer id) {
        InvoiceDAO invoiceDAO = invoiceRepository.findById(id).orElse(null);
        if (invoiceDAO == null) {
            return null;
        }

        invoiceRepository.deleteById(id);
        logger.debug("Invoice deleted: {}", invoiceDAO);
        return invoiceConverter.toEntityFromDAO(invoiceDAO);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<InvoiceDAO> invoiceDAOs = invoiceRepository.findAll();
        logger.debug("Fetched invoices: {}", invoiceDAOs.size());
        return invoiceConverter.toEntityFromDAO(invoiceDAOs);
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        InvoiceDAO invoiceDAO = invoiceRepository.findById(id).orElse(null);
        if (invoiceDAO == null) {
            return null;
        }
        logger.debug("Fetched invoice: {}", invoiceDAO);

        return invoiceConverter.toEntityFromDAO(invoiceDAO);
    }

    @Override
    public Invoice updateInvoiceById(Integer id, Invoice invoice) {
        InvoiceDAO invoiceDAO = invoiceRepository.findById(id).orElse(null);
        if (invoiceDAO == null) {
            return null;
        }

        invoiceDAO.setDescription(invoice.getDescription());
        invoiceDAO.setPerMail(invoice.isPerMail());
        invoiceDAO.setPreText(invoice.getPreText());
        invoiceDAO.setPostText(invoice.getPostText());
        invoiceDAO.setServiceProvidedFrom(invoice.getServiceProvidedFrom());
        invoiceDAO.setServiceProvidedTo(invoice.getServiceProvidedTo());
        invoiceDAO.setOrderNumber(invoice.getOrderNumber());
        invoiceDAO.setGeneratedInvoiceId(invoice.getGeneratedInvoiceId());
        invoiceDAO.setSalexTax(SalesTaxConverter.toDAO(invoice.getSalexTax()));
        List<InvoicePositionDAO> invoicePositionDAOs = InvoicePositionConverter.toDAO(invoice.getInvoicePositions());
        invoiceDAO.setInvoicePosition(invoicePositionDAOs);
        ContactPersonDAO contactPersonDAO = contactPersonConverter.toDAO(invoice.getReceiver());
        invoiceDAO.setReceiver(contactPersonDAO);
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateConverter.toDAO(invoice.getInvoiceTemplate());
        invoiceDAO.setInvoiceTemplateDAO(invoiceTemplateDAO);
        BusinessPartnerDAO businessPartnerDAO = BusinessPartnerConverter.toDAO(invoice.getCustomer());
        invoiceDAO.setCustomer(businessPartnerDAO);

        InvoiceDAO updatedInvoiceDAO = invoiceRepository.save(invoiceDAO);
        logger.debug("Invoice updated: {}", updatedInvoiceDAO);
        return invoiceConverter.toEntityFromDAO(updatedInvoiceDAO);
    }

    @Override
    public boolean exists(Integer id) {
        return invoiceRepository.existsById(id);
    }
}
