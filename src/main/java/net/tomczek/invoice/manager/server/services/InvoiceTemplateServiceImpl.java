package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.InvoiceTemplate;
import net.tomczek.invoice.manager.server.converter.InvoiceTemplateConverter;
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
    public InvoiceTemplateServiceImpl( InvoiceTemplateRepository invoiceTemplateRepository, IFileStorageService fileStorageService) {
        this.invoiceTemplateRepository = invoiceTemplateRepository;
        this.fileStorageService = fileStorageService;
    }

    private final InvoiceTemplateRepository invoiceTemplateRepository;
    private final IFileStorageService fileStorageService;

    @Override
    public InvoiceTemplate createInvoiceTemplate(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplate savedInvoiceTemplate = invoiceTemplateRepository.save(invoiceTemplate);
        logger.debug("InvoiceTemplate saved: {}", savedInvoiceTemplate);
        return savedInvoiceTemplate;
    }

    @Override
    public InvoiceTemplate deleteInvoiceTemplateById(Integer id) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplate == null) {
            return null;
        }
        try {
            this.fileStorageService.deleteFile(invoiceTemplate.getBackgroundPdfId());
        } catch (Exception e) {
            logger.warn("Could not delete file with id [{}]", invoiceTemplate.getBackgroundPdfId());
        }

        invoiceTemplateRepository.deleteById(id);
        logger.debug("InvoiceTemplate deleted: {}", invoiceTemplate);
        return invoiceTemplate;
    }

    @Override
    public List<InvoiceTemplate> getAllInvoiceTemplates() {
        List<InvoiceTemplate> invoiceTemplates = invoiceTemplateRepository.findAll();
        logger.debug("Fetched invoiceTemplates: {}", invoiceTemplates.size());
        return invoiceTemplates;
    }

    @Override
    public InvoiceTemplate getInvoiceTemplateById(Integer id) {
        InvoiceTemplate invoiceTemplate = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplate == null) {
            return null;
        }
        logger.debug("Fetched invoiceTemplate: {}", invoiceTemplate);
        return invoiceTemplate;
    }

    @Override
    public InvoiceTemplate updateInvoiceTemplateById(Integer id, InvoiceTemplate invoiceTemplate) {
        InvoiceTemplate invoiceTemplateToUpdate = invoiceTemplateRepository.findById(id).orElse(null);
        if (invoiceTemplateToUpdate == null) {
            return null;
        }

        invoiceTemplateToUpdate.setName(invoiceTemplate.getName());
        invoiceTemplateToUpdate.setMarginTopFirstPage(invoiceTemplate.getMarginTopFirstPage());
        invoiceTemplateToUpdate.setMarginBottomFirstPage(invoiceTemplate.getMarginBottomFirstPage());
        invoiceTemplateToUpdate.setMarginTopOtherPages(invoiceTemplate.getMarginTopOtherPages());
        invoiceTemplateToUpdate.setMarginBottomOtherPages(invoiceTemplate.getMarginBottomOtherPages());
        invoiceTemplateToUpdate.setBackgroundPdfId(invoiceTemplate.getBackgroundPdfId());
        InvoiceTemplate updatedInvoiceTemplate = invoiceTemplateRepository.save(invoiceTemplateToUpdate);
        logger.debug("InvoiceTemplate updated: {}", updatedInvoiceTemplate);
        return updatedInvoiceTemplate;
    }

    @Override
    public boolean exists(Integer id) {
        return invoiceTemplateRepository.existsById(id);
    }
}
