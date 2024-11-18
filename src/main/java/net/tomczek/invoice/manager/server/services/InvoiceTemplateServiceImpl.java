package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoiceTemplateDAO;
import net.tomczek.invoice.manager.server.models.InvoiceTemplate;
import net.tomczek.invoice.manager.server.models.converter.InvoiceTemplateConverter;
import net.tomczek.invoice.manager.server.repositories.InvoiceTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTemplateServiceImpl implements IInvoiceTemplateService {

    @Autowired
    public InvoiceTemplateServiceImpl(InvoiceTemplateConverter invoiceTemplateConverter, InvoiceTemplateRepository invoiceTemplateRepository) {
        this.invoiceTemplateConverter = invoiceTemplateConverter;
        this.invoiceTemplateRepository = invoiceTemplateRepository;
    }

    private final InvoiceTemplateConverter invoiceTemplateConverter;
    private final InvoiceTemplateRepository invoiceTemplateRepository;

    @Override
    public InvoiceTemplate createInvoiceTemplate(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDAO invoiceTemplateDAOToSave = invoiceTemplateConverter.toDAO(invoiceTemplate);
        InvoiceTemplateDAO savedInvoiceTemplateDAO = invoiceTemplateRepository.save(invoiceTemplateDAOToSave);
        InvoiceTemplate savedInvoiceTemplate = invoiceTemplateConverter.toEntityFromDAO(savedInvoiceTemplateDAO);
        return savedInvoiceTemplate;
    }

    @Override
    public InvoiceTemplate deleteInvoiceTemplateById(Integer id) {
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplateDAO == null) {
            return null;
        }

        invoiceTemplateRepository.deleteById(id);
        return invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAO);
    }

    @Override
    public List<InvoiceTemplate> getAllInvoiceTemplates() {
        return invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateRepository.findAll());
    }

    @Override
    public InvoiceTemplate getInvoiceTemplateById(Integer id) {
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(id).orElse(null);
        return invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAO);
    }

    @Override
    public InvoiceTemplate updateInvoiceTemplateById(Integer id, InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplateDAO == null) {
            return null;
        }

        invoiceTemplateDAO.setName(invoiceTemplate.getName());
        invoiceTemplateDAO.setMarginTopFirstPage(invoiceTemplate.getMarginTopFirstPage());
        invoiceTemplateDAO.setMarginBottomFirstPage(invoiceTemplate.getMarginBottomFirstPage());
        invoiceTemplateDAO.setMarginTopOtherPages(invoiceTemplate.getMarginTopOtherPages());
        invoiceTemplateDAO.setMarginBottomOtherPages(invoiceTemplate.getMarginBottomOtherPages());
        invoiceTemplateDAO.setBackgroundPdfId(invoiceTemplate.getBackgroundPdfId());
        InvoiceTemplateDAO updatedInvoiceTemplateDAO = invoiceTemplateRepository.save(invoiceTemplateDAO);
        return invoiceTemplateConverter.toEntityFromDAO(updatedInvoiceTemplateDAO);
    }

    @Override
    public boolean exists(Integer id) {
        return invoiceTemplateRepository.existsById(id);
    }
}
