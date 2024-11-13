package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.models.*;
import net.tomczek.invoice.manager.server.repositories.BusinessPartnersRepository;
import net.tomczek.invoice.manager.server.repositories.ContactPersonsRepository;
import net.tomczek.invoice.manager.server.repositories.InvoiceTemplateRepository;
import net.tomczek.invoice.manager.server.repositories.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InvoiceConverter {

    @Autowired
    public InvoiceConverter(BusinessPartnersRepository businessPartnersRepository, ContactPersonsRepository contactPersonsRepository, SalesTaxRepository salesTaxRepository, InvoiceTemplateRepository invoiceTemplateRepository, BusinessPartnerConverter businessPartnerConverter, ContactPersonConverter contactPersonConverter, SalesTaxConverter salesTaxConverter, InvoiceTemplateConverter invoiceTemplateConverter) {
        this.businessPartnersRepository = businessPartnersRepository;
        this.contactPersonsRepository = contactPersonsRepository;
        this.salesTaxRepository = salesTaxRepository;
        this.invoiceTemplateRepository = invoiceTemplateRepository;
        this.businessPartnerConverter = businessPartnerConverter;
        this.contactPersonConverter = contactPersonConverter;
        this.salesTaxConverter = salesTaxConverter;
        this.invoiceTemplateConverter = invoiceTemplateConverter;
    }

    private final BusinessPartnersRepository businessPartnersRepository;
    private final BusinessPartnerConverter businessPartnerConverter;
    private final ContactPersonsRepository contactPersonsRepository;
    private final ContactPersonConverter contactPersonConverter;
    private final SalesTaxRepository salesTaxRepository;
    private final SalesTaxConverter salesTaxConverter;
    private final InvoiceTemplateRepository invoiceTemplateRepository;
    private final InvoiceTemplateConverter invoiceTemplateConverter;

    public InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setDescription(invoice.getDescription());
        invoiceDTO.setViaMail(invoice.isPerMail());
        invoiceDTO.setPreText(invoice.getPreText());
        invoiceDTO.setPostText(invoice.getPostText());
        invoiceDTO.setServiceFrom(invoice.getServiceProvidedFrom());
        invoiceDTO.setServiceTo(invoice.getServiceProvidedTo());
        invoiceDTO.setOrderNumber(invoice.getOrderNumber());
        invoiceDTO.setCustomerNumber(invoice.getCustomer().getId());
        invoiceDTO.setPositions(invoice.getInvoicePosition().stream().map(InvoicePosition::getId).collect(Collectors.toList()));
        invoiceDTO.setReceiver(invoice.getReceiver().getId());
        invoiceDTO.setSalesTax(invoice.getSalexTax().getId());
        invoiceDTO.setInvoiceTemplate(invoice.getInvoiceTemplate().getId());
        invoiceDTO.setFile(invoice.getGeneratedInvoiceId());

        return invoiceDTO;
    }

    public Invoice toEntityFromDTO(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setDescription(invoiceDTO.getDescription());
        invoice.setPerMail(invoiceDTO.getViaMail());
        invoice.setPreText(invoiceDTO.getPreText());
        invoice.setPostText(invoiceDTO.getPostText());
        invoice.setServiceProvidedFrom(invoiceDTO.getServiceFrom());
        invoice.setServiceProvidedTo(invoiceDTO.getServiceTo());
        invoice.setOrderNumber(invoiceDTO.getOrderNumber());

        BusinessPartnerDAO customerDAO = businessPartnersRepository.findById(invoiceDTO.getCustomerNumber()).orElse(null);
        BusinessPartner customer = businessPartnerConverter.toEntityFromDAO(customerDAO);
        invoice.setCustomer(customer);

        ContactPersonDAO receiverDAO = contactPersonsRepository.findById(invoiceDTO.getReceiver()).orElse(null);
        ContactPerson receiver = contactPersonConverter.toEntityFromDAO(receiverDAO);
        invoice.setReceiver(receiver);

        SalesTaxDAO salesTaxDAO = salesTaxRepository.findById(invoiceDTO.getSalesTax()).orElse(null);
        SalesTax salesTax = salesTaxConverter.toEntityFromDAO(salesTaxDAO);
        invoice.setSalexTax(salesTax);

        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(invoiceDTO.getInvoiceTemplate()).orElse(null);
        InvoiceTemplate invoiceTemplate = invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAO);
        invoice.setInvoiceTemplate(invoiceTemplate);
        invoice.setGeneratedInvoiceId(invoiceDTO.getFile());

        return invoice;
    }

    public Invoice toEntityFromDAO(InvoiceDAO invoiceDAO) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDAO.getId());
        invoice.setDescription(invoiceDAO.getDescription());
        invoice.setPerMail(invoiceDAO.isPerMail());
        invoice.setPreText(invoiceDAO.getPreText());
        invoice.setPostText(invoiceDAO.getPostText());
        invoice.setServiceProvidedFrom(invoiceDAO.getServiceProvidedFrom());
        invoice.setServiceProvidedTo(invoiceDAO.getServiceProvidedTo());
        invoice.setOrderNumber(invoiceDAO.getOrderNumber());

        BusinessPartnerDAO customerDAO = invoiceDAO.getCustomer();
        BusinessPartner customer = businessPartnerConverter.toEntityFromDAO(customerDAO);
        invoice.setCustomer(customer);

        ContactPersonDAO receiverDAO = invoiceDAO.getReceiver();
        ContactPerson receiver = contactPersonConverter.toEntityFromDAO(receiverDAO);
        invoice.setReceiver(receiver);

        SalesTaxDAO salesTaxDAO = invoiceDAO.getSalexTax();
        SalesTax salesTax = salesTaxConverter.toEntityFromDAO(salesTaxDAO);
        invoice.setSalexTax(salesTax);

        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(invoiceDAO.getInvoiceTemplateDAO().getId()).orElse(null);
        InvoiceTemplate invoiceTemplate = invoiceTemplateConverter.toEntityFromDAO(invoiceTemplateDAO);
        invoice.setInvoiceTemplate(invoiceTemplate);

        invoice.setGeneratedInvoiceId(invoiceDAO.getGeneratedInvoiceId());

        return invoice;
    }

    public InvoiceDAO toDAO(Invoice invoice) {
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        invoiceDAO.setId(invoice.getId());
        invoiceDAO.setDescription(invoice.getDescription());
        invoiceDAO.setPerMail(invoice.isPerMail());
        invoiceDAO.setPreText(invoice.getPreText());
        invoiceDAO.setPostText(invoice.getPostText());
        invoiceDAO.setServiceProvidedFrom(invoice.getServiceProvidedFrom());
        invoiceDAO.setServiceProvidedTo(invoice.getServiceProvidedTo());
        invoiceDAO.setOrderNumber(invoice.getOrderNumber());
        invoiceDAO.setCustomer(businessPartnerConverter.toDAO(invoice.getCustomer()));
        invoiceDAO.setReceiver(contactPersonConverter.toDAO(invoice.getReceiver()));
        invoiceDAO.setSalexTax(salesTaxConverter.toDAO(invoice.getSalexTax()));

        InvoiceTemplateDAO invoiceTemplateDAO = invoiceTemplateRepository.findById(invoice.getInvoiceTemplate().getId()).orElse(null);
        invoiceDAO.setInvoiceTemplateDAO(invoiceTemplateDAO);

        invoiceDAO.setGeneratedInvoiceId(invoice.getGeneratedInvoiceId());

        return invoiceDAO;
    }
}
