package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.converter.BusinessPartnerConverter;
import net.tomczek.invoice.manager.server.models.converter.ContactPersonConverter;
import net.tomczek.invoice.manager.server.models.converter.InvoiceConverter;
import net.tomczek.invoice.manager.server.repositories.BusinessPartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessPartnerServiceImpl implements IBusinessPartnerService {

    @Autowired
    public BusinessPartnerServiceImpl(ContactPersonConverter contactPersonConverter, BusinessPartnersRepository businessPartnersRepository, InvoiceConverter invoiceConverter) {
        this.contactPersonConverter = contactPersonConverter;
        this.businessPartnersRepository = businessPartnersRepository;
        this.invoiceConverter = invoiceConverter;
    }

    private final ContactPersonConverter contactPersonConverter;
    private final BusinessPartnersRepository businessPartnersRepository;
    private final InvoiceConverter invoiceConverter;


    @Override
    public BusinessPartner createBusinessPartner(BusinessPartner businessPartner) {
        BusinessPartnerDAO businessPartnerDAOToSave = BusinessPartnerConverter.toDAO(businessPartner);
        BusinessPartnerDAO savedBusinessPartnerDAO = businessPartnersRepository.save(businessPartnerDAOToSave);
        BusinessPartner savedBusinessPartner = BusinessPartnerConverter.toEntityFromDAO(savedBusinessPartnerDAO);
        return savedBusinessPartner;
    }

    @Override
    public BusinessPartner deleteBusinessPartnerById(Integer id) {
        BusinessPartnerDAO businessPartnerDAO = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartnerDAO == null) {
            return null;
        }

        businessPartnersRepository.deleteById(id);
        return BusinessPartnerConverter.toEntityFromDAO(businessPartnerDAO);
    }

    @Override
    public List<BusinessPartner> getAllBusinessPartners() {
        List<BusinessPartnerDAO> businessPartnerDAOs = businessPartnersRepository.findAll();
        return BusinessPartnerConverter.toEntityFromDAO(businessPartnerDAOs);
    }

    @Override
    public BusinessPartner getBusinessPartnerById(Integer id) {
        BusinessPartnerDAO businessPartnerDAO = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartnerDAO == null) {
            return null;
        }

        return BusinessPartnerConverter.toEntityFromDAO(businessPartnerDAO);
    }

    @Override
    public BusinessPartner updateBusinessPartnerById(Integer id, BusinessPartner businessPartner) {
        BusinessPartnerDAO businessPartnerDAO = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartnerDAO == null) {
            return null;
        }

        businessPartnerDAO.setName(businessPartner.getName());
        businessPartnerDAO.setDescription(businessPartner.getDescription());

        businessPartnerDAO.getAddress().setStreet(businessPartner.getAddress().getStreet());
        businessPartnerDAO.getAddress().setHouseNumber(businessPartner.getAddress().getHouseNumber());
        businessPartnerDAO.getAddress().setZipCode(businessPartner.getAddress().getZipCode());
        businessPartnerDAO.getAddress().setCity(businessPartner.getAddress().getCity());
        businessPartnerDAO.getAddress().setCountry(businessPartner.getAddress().getCountry());

        businessPartnerDAO.setContactPersonDAOS(contactPersonConverter.toDAO(businessPartner.getContactPersons()));
        businessPartnerDAO.setInvoiceDAOS(invoiceConverter.toDAO(businessPartner.getInvoices()));

        BusinessPartnerDAO updatedBusinessPartnerDAO = businessPartnersRepository.save(businessPartnerDAO);
        BusinessPartner updatedBusinessPartner = BusinessPartnerConverter.toEntityFromDAO(updatedBusinessPartnerDAO);
        return updatedBusinessPartner;
    }

    @Override
    public boolean exists(Integer id) {
        return businessPartnersRepository.existsById(id);
    }
}
