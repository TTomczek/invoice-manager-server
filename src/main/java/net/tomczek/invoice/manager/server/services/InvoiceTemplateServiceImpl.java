package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoiceTemplateDAO;
import net.tomczek.invoice.manager.server.models.InvoiceTemplate;
import net.tomczek.invoice.manager.server.models.converter.InvoiceTemplateConverter;
import net.tomczek.invoice.manager.server.repositories.InvoiceTemplateRepository;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTemplateServiceImpl implements IInvoiceTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceTemplateServiceImpl.class);

    @Autowired
    public InvoiceTemplateServiceImpl(InvoiceTemplateConverter invoiceTemplateConverter, InvoiceTemplateRepository invoiceTemplateRepository, IFileStorageService fileStorageService) {
        this.invoiceTemplateConverter = invoiceTemplateConverter;
        this.invoiceTemplateRepository = invoiceTemplateRepository;
        this.fileStorageService = fileStorageService;
    }

    private final InvoiceTemplateConverter invoiceTemplateConverter;
    private final InvoiceTemplateRepository invoiceTemplateRepository;
    private final IFileStorageService fileStorageService;

    @Override
    public InvoiceTemplate createInvoiceTemplate(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDAO invoiceTemplateDAOToSave = invoiceTemplateConverter.toDAO(invoiceTemplate);
        InvoiceTemplateDAO savedInvoiceTemplateDAO = invoiceTemplateRepository.save(invoiceTemplateDAOToSave);
        logger.debug("InvoiceTemplate saved: {}", savedInvoiceTemplateDAO);
        InvoiceTemplate savedInvoiceTemplate = invoiceTemplateConverter.toEntityFromDAO(savedInvoiceTemplateDAO);
        return savedInvoiceTemplate;
    }

    @Override
    public InvoiceTemplate deleteInvoiceTemplateById(Integer id) {
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplateDAO == null) {
            return null;
        }
        try {
            this.fileStorageService.deleteFile(invoiceTemplateDAO.getBackgroundPdfId());
        } catch (Exception e) {
            logger.warn("Could not delete file with id [{}]", invoiceTemplateDAO.getBackgroundPdfId());
        }

        invoiceTemplateRepository.deleteById(id);
        logger.debug("InvoiceTemplate deleted: {}", invoiceTemplateDAO);
        return invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAO);
    }

    @Override
    public List<InvoiceTemplate> getAllInvoiceTemplates() {
        List<InvoiceTemplateDAO> invoiceTemplateDAOs = invoiceTemplateRepository.findAll();
        logger.debug("Fetched invoiceTemplates: {}", invoiceTemplateDAOs.size());
        return invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAOs);
    }

    @Override
    public InvoiceTemplate getInvoiceTemplateById(Integer id) {
        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplateDAO == null) {
            return null;
        }
        logger.debug("Fetched invoiceTemplate: {}", invoiceTemplateDAO);
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
        logger.debug("InvoiceTemplate updated: {}", updatedInvoiceTemplateDAO);
        return invoiceTemplateConverter.toEntityFromDAO(updatedInvoiceTemplateDAO);
    }

    @Override
    public boolean exists(Integer id) {
        return invoiceTemplateRepository.existsById(id);
    }
}
