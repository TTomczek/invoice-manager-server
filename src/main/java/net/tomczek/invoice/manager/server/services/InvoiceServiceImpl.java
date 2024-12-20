package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import net.tomczek.invoice.manager.server.repositories.InvoicesRepository;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import net.tomczek.invoice.manager.server.services.invoicegenerator.IInvoiceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    public InvoiceServiceImpl(InvoicesRepository invoiceRepository, IInvoiceGenerator invoiceGenerator, IFileStorageService fileStorageService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceGenerator = invoiceGenerator;
        this.fileStorageService = fileStorageService;
    }

    private final InvoicesRepository invoiceRepository;
    private final IInvoiceGenerator invoiceGenerator;
    private final IFileStorageService fileStorageService;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        logger.debug("Invoice saved: {}", savedInvoice);
        return savedInvoice;
    }

    @Override
    public Invoice deleteInvoiceById(Integer id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return null;
        }

        invoiceRepository.deleteById(id);
        logger.debug("Invoice deleted: {}", invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        logger.debug("Fetched invoices: {}", invoices.size());
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return null;
        }
        logger.debug("Fetched invoice: {}", invoice);

        return invoice;
    }

    @Override
    public Invoice updateInvoiceById(Integer id, Invoice invoice) {
        Invoice invoiceToUpdate = invoiceRepository.findById(id).orElse(null);
        if (invoiceToUpdate == null) {
            return null;
        }

        invoiceToUpdate.setDescription(invoice.getDescription());
        invoiceToUpdate.setPerMail(invoice.isPerMail());
        invoiceToUpdate.setPreText(invoice.getPreText());
        invoiceToUpdate.setPostText(invoice.getPostText());
        invoiceToUpdate.setServiceProvidedFrom(invoice.getServiceProvidedFrom());
        invoiceToUpdate.setServiceProvidedTo(invoice.getServiceProvidedTo());
        invoiceToUpdate.setOrderNumber(invoice.getOrderNumber());
        invoiceToUpdate.setGeneratedInvoiceId(invoice.getGeneratedInvoiceId());
        invoiceToUpdate.setSalesTax(invoice.getSalesTax());
        invoiceToUpdate.setInvoicePositions(invoice.getInvoicePositions());
        invoiceToUpdate.setReceiver(invoice.getReceiver());
        invoiceToUpdate.setInvoiceTemplate(invoice.getInvoiceTemplate());
        invoiceToUpdate.setCustomer(invoice.getCustomer());
        invoiceToUpdate.setPaid(invoice.isPaid());

        Invoice updatedInvoice = invoiceRepository.save(invoiceToUpdate);
        logger.debug("Invoice updated: {}", updatedInvoice);
        return updatedInvoice;
    }

    @Override
    public List<Invoice> getAllInvoiceByIds(List<Integer> ids) {
        List<Invoice> invoices = invoiceRepository.findAllById(ids);
        logger.debug("Fetched invoices by ids: {}", invoices.size());
        return invoices;
    }

    @Override
    public boolean exists(Integer id) {
        return invoiceRepository.existsById(id);
    }

    @Override
    public Integer generateInvoicePdf(Integer id) {

        Invoice invoice = this.getInvoiceById(id);
        if (invoice == null) {
            return null;
        }

        if (invoice.getGeneratedInvoiceId() != null) {
            return invoice.getGeneratedInvoiceId();
        }


        byte[] generatedInvoice = this.invoiceGenerator.generateInvoice(invoice);
        if (generatedInvoice == null) {
            return null;
        }

        try {
            FileWithContent file = new FileWithContent(null, "invoice_" + id + ".pdf", generatedInvoice);
            Integer storedFileId = this.fileStorageService.storeFile(file.getFileName(), generatedInvoice);
            file.setId(storedFileId);

            invoice.setGeneratedInvoiceId(storedFileId);
            this.updateInvoiceById(id, invoice);

            logger.debug("Generated invoice stored: {}", file);
            return file.getId();
        } catch (Exception e) {
            logger.error("Error while storing generated invoice", e);
            return null;
        }
    }
}
