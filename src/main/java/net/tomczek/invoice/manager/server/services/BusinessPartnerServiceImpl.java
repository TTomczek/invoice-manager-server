package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.Address;
import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import net.tomczek.invoice.manager.server.jpa.specifications.BusinessPartnerSpecifications;
import net.tomczek.invoice.manager.server.repositories.BusinessPartnersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessPartnerServiceImpl implements IBusinessPartnerService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessPartnerServiceImpl.class);

    @Autowired
    public BusinessPartnerServiceImpl(BusinessPartnersRepository businessPartnersRepository, IAddressService addressService) {
        this.businessPartnersRepository = businessPartnersRepository;
        this.addressService = addressService;
    }

    private final BusinessPartnersRepository businessPartnersRepository;
    private final IAddressService addressService;

    @Override
    public BusinessPartner createBusinessPartner(BusinessPartner businessPartner) {
        if (businessPartner == null || businessPartner.getAddress() == null) {
            return null;
        }
        Address address = businessPartner.getAddress();
        Address savedAddress = addressService.createAddress(address);
        businessPartner.setAddress(savedAddress);
        BusinessPartner savedBusinessPartner = businessPartnersRepository.save(businessPartner);
        logger.debug("BusinessPartner saved: {}", savedBusinessPartner);
        return savedBusinessPartner;
    }

    @Override
    public BusinessPartner deleteBusinessPartnerById(Integer id) {
        BusinessPartner businessPartner = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartner == null) {
            return null;
        }

        businessPartnersRepository.deleteById(id);
        logger.debug("BusinessPartner deleted: {}", businessPartner);
        return businessPartner;
    }

    @Override
    public List<BusinessPartner> getAllBusinessPartners(String name) {
        List<BusinessPartner> businessPartners = businessPartnersRepository.findAll(BusinessPartnerSpecifications.nameContains(name));
        logger.debug("Fetched business partners: {}", businessPartners.size());
        return businessPartners;
    }

    @Override
    public BusinessPartner getBusinessPartnerById(Integer id) {
        BusinessPartner businessPartner = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartner == null) {
            return null;
        }
        logger.debug("Fetched business partner: {}", businessPartner);

        return businessPartner;
    }

    @Override
    public BusinessPartner updateBusinessPartnerById(Integer id, BusinessPartner businessPartner) {
        BusinessPartner businessPartnertoUpdate = businessPartnersRepository.findById(id).orElse(null);
        if (businessPartnertoUpdate == null) {
            return null;
        }

        businessPartnertoUpdate.setName(businessPartner.getName());
        businessPartnertoUpdate.setDescription(businessPartner.getDescription());

        businessPartnertoUpdate.getAddress().setStreet(businessPartner.getAddress().getStreet());
        businessPartnertoUpdate.getAddress().setHouseNumber(businessPartner.getAddress().getHouseNumber());
        businessPartnertoUpdate.getAddress().setZipCode(businessPartner.getAddress().getZipCode());
        businessPartnertoUpdate.getAddress().setCity(businessPartner.getAddress().getCity());
        businessPartnertoUpdate.getAddress().setCountry(businessPartner.getAddress().getCountry());

        businessPartnertoUpdate.setContactPersons(businessPartner.getContactPersons());
        businessPartnertoUpdate.setInvoices(businessPartner.getInvoices());

        BusinessPartner updatedBusinessPartner = businessPartnersRepository.save(businessPartnertoUpdate);
        logger.debug("BusinessPartner updated: {}", updatedBusinessPartner);
        return updatedBusinessPartner;
    }

    @Override
    public List<BusinessPartner> getAllBusinessPartnerByIds(List<Integer> ids) {
        List<BusinessPartner> businessPartners = businessPartnersRepository.findAllById(ids);
        logger.debug("Fetched business partners by ids: {}", businessPartners.size());
        return businessPartners;
    }

    @Override
    public boolean exists(Integer id) {
        return businessPartnersRepository.existsById(id);
    }
}
