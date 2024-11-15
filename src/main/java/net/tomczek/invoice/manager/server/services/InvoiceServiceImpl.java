package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.models.Invoice;
import net.tomczek.invoice.manager.server.models.converter.*;
import net.tomczek.invoice.manager.server.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private final ContactPersonConverter contactPersonConverter;

    @Autowired
    public InvoiceServiceImpl(InvoiceConverter invoiceConverter, InvoicesRepository invoiceRepository, ContactPersonConverter contactPersonConverter, InvoiceTemplateConverter invoiceTemplateConverter) {
        this.invoiceConverter = invoiceConverter;
        this.invoiceRepository = invoiceRepository;
        this.contactPersonConverter = contactPersonConverter;
        this.invoiceTemplateConverter = invoiceTemplateConverter;
    }

    private final InvoiceConverter invoiceConverter;
    private final InvoicesRepository invoiceRepository;
    private final InvoiceTemplateConverter invoiceTemplateConverter;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        InvoiceDAO invoiceDAOToSave = invoiceConverter.toDAO(invoice);
        InvoiceDAO savedInvoiceDAO = invoiceRepository.save(invoiceDAOToSave);
        return invoiceConverter.toEntityFromDAO(savedInvoiceDAO);
    }

    @Override
    public Invoice deleteInvoiceById(Integer id) {
        InvoiceDAO invoiceDAO = invoiceRepository.findById(id).orElse(null);
        if (invoiceDAO == null) {
            return null;
        }

        invoiceRepository.deleteById(id);
        return invoiceConverter.toEntityFromDAO(invoiceDAO);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<InvoiceDAO> invoiceDAOs = invoiceRepository.findAll();
        return invoiceConverter.toEntityFromDAO(invoiceDAOs);
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        InvoiceDAO invoiceDAO = invoiceRepository.findById(id).orElse(null);
        if (invoiceDAO == null) {
            return null;
        }

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
        return invoiceConverter.toEntityFromDAO(updatedInvoiceDAO);

    }
}
