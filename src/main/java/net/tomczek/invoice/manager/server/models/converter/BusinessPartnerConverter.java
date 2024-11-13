package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BusinessPartnerConverter {

    @Autowired
    public BusinessPartnerConverter(InvoiceConverter invoiceConverter, ContactPersonConverter contactPersonConverter) {
        this.invoiceConverter = invoiceConverter;
        this.contactPersonConverter = contactPersonConverter;
    }

    private final InvoiceConverter invoiceConverter;
    private final ContactPersonConverter contactPersonConverter;

    public BusinessPartnerDTO toDTO(BusinessPartner businessPartner) {
        BusinessPartnerDTO businessPartnerDTO = new BusinessPartnerDTO();
        businessPartnerDTO.setId(businessPartner.getId());
        businessPartnerDTO.setName(businessPartner.getName());
        businessPartnerDTO.setAddress(AddressConverter.toDTO(businessPartner.getAddress()));
        businessPartnerDTO.setDescription(businessPartner.getDescription());

        businessPartnerDTO.setInvoices(businessPartner.getInvoices().stream().map(Invoice::getId).collect(Collectors.toList()));
        businessPartnerDTO.setContactPersons(businessPartner.getContactPersons().stream().map(ContactPerson::getId).collect(Collectors.toList()));
        return businessPartnerDTO;
    }

    public BusinessPartner toEntityFromDTO(BusinessPartnerDTO businessPartnerDTO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDTO.getId());
        businessPartner.setName(businessPartnerDTO.getName());
        businessPartner.setAddress(AddressConverter.toEntityFromDTO(businessPartnerDTO.getAddress()));
        return businessPartner;
    }

    public BusinessPartner toEntityFromDAO(BusinessPartnerDAO businessPartnerDAO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDAO.getId());
        businessPartner.setName(businessPartnerDAO.getName());
        businessPartner.setAddress(businessPartnerDAO.getAddress());
        return businessPartner;
    }

    public BusinessPartnerDAO toDAO(BusinessPartner businessPartner) {
        BusinessPartnerDAO businessPartnerDAO = new BusinessPartnerDAO();
        businessPartnerDAO.setId(businessPartner.getId());
        businessPartnerDAO.setName(businessPartner.getName());
        businessPartnerDAO.setAddress(businessPartner.getAddress());
        return businessPartnerDAO;
    }
}
