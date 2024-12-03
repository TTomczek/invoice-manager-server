package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.entities.InvoiceDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessPartnerConverter {

    @Autowired
    BusinessPartnerConverter(ContactPersonConverter contactPersonConverter, InvoiceConverter invoiceConverter) {
        this.invoiceConverter = invoiceConverter;
    }

    private final InvoiceConverter invoiceConverter;

    public BusinessPartnerDTO toDTO(BusinessPartner businessPartner) {
        BusinessPartnerDTO businessPartnerDTO = new BusinessPartnerDTO();
        businessPartnerDTO.setId(businessPartner.getId());
        businessPartnerDTO.setName(businessPartner.getName());
        businessPartnerDTO.setAddress(AddressConverter.toDTO(businessPartner.getAddress()));
        businessPartnerDTO.setDescription(businessPartner.getDescription());

        List<Invoice> invoices = businessPartner.getInvoices();
        if (invoices != null) {
            businessPartnerDTO.setInvoices(invoices.stream().map(Invoice::getId).collect(Collectors.toList()));
        }
        List<ContactPerson> contactPersons = businessPartner.getContactPersons();
        if (contactPersons != null) {
            businessPartnerDTO.setContactPersons(contactPersons.stream().map(ContactPerson::getId).collect(Collectors.toList()));
        }
        return businessPartnerDTO;
    }

    public List<BusinessPartnerDTO> toDTO(List<BusinessPartner> businessPartners) {
        return businessPartners.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public BusinessPartner toEntityFromDTO(BusinessPartnerDTO businessPartnerDTO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDTO.getId());
        businessPartner.setName(businessPartnerDTO.getName());
        businessPartner.setDescription(businessPartnerDTO.getDescription());
        businessPartner.setAddress(AddressConverter.toEntityFromDTO(businessPartnerDTO.getAddress()));
        return businessPartner;
    }

    public List<BusinessPartner> toEntityFromDTO(List<BusinessPartnerDTO> businessPartnerDTOs) {
        return businessPartnerDTOs.stream().map(this::toEntityFromDTO).collect(Collectors.toList());
    }

    public BusinessPartner toEntityFromDAO(BusinessPartnerDAO businessPartnerDAO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDAO.getId());
        businessPartner.setName(businessPartnerDAO.getName());
        businessPartner.setDescription(businessPartnerDAO.getDescription());

        List<InvoiceDAO> invoiceDAOs = businessPartnerDAO.getInvoiceDAOS();
        if (invoiceDAOs != null) {
            businessPartner.setInvoices(invoiceDAOs.stream().map(invoiceConverter::toEntityFromDAO).collect(Collectors.toList()));
        }
        businessPartner.setAddress(AddressConverter.toEntityFromDAO(businessPartnerDAO.getAddress()));
        return businessPartner;
    }

    public List<BusinessPartner> toEntityFromDAO(List<BusinessPartnerDAO> businessPartnerDAOs) {
        return businessPartnerDAOs.stream().map(this::toEntityFromDAO).collect(Collectors.toList());
    }

    public BusinessPartnerDAO toDAO(BusinessPartner businessPartner) {
        BusinessPartnerDAO businessPartnerDAO = new BusinessPartnerDAO();
        businessPartnerDAO.setId(businessPartner.getId());
        businessPartnerDAO.setName(businessPartner.getName());
        businessPartnerDAO.setDescription(businessPartner.getDescription());
        businessPartnerDAO.setAddress(AddressConverter.toDAO(businessPartner.getAddress()));
        return businessPartnerDAO;
    }

    public List<BusinessPartnerDAO> toDAO(List<BusinessPartner> businessPartners) {
        return businessPartners.stream().map(this::toDAO).collect(Collectors.toList());
    }
}
