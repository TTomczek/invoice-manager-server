package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.models.Invoice;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessPartnerConverter {

    public static BusinessPartnerDTO toDTO(BusinessPartner businessPartner) {
        BusinessPartnerDTO businessPartnerDTO = new BusinessPartnerDTO();
        businessPartnerDTO.setId(businessPartner.getId());
        businessPartnerDTO.setName(businessPartner.getName());
        businessPartnerDTO.setAddress(AddressConverter.toDTO(businessPartner.getAddress()));
        businessPartnerDTO.setDescription(businessPartner.getDescription());

        businessPartnerDTO.setInvoices(businessPartner.getInvoices().stream().map(Invoice::getId).collect(Collectors.toList()));
        businessPartnerDTO.setContactPersons(businessPartner.getContactPersons().stream().map(ContactPerson::getId).collect(Collectors.toList()));
        return businessPartnerDTO;
    }

    public static List<BusinessPartnerDTO> toDTO(List<BusinessPartner> businessPartners) {
        return businessPartners.stream().map(BusinessPartnerConverter::toDTO).collect(Collectors.toList());
    }

    public static BusinessPartner toEntityFromDTO(BusinessPartnerDTO businessPartnerDTO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDTO.getId());
        businessPartner.setName(businessPartnerDTO.getName());
        businessPartner.setAddress(AddressConverter.toEntityFromDTO(businessPartnerDTO.getAddress()));
        return businessPartner;
    }

    public static List<BusinessPartner> toEntityFromDTO(List<BusinessPartnerDTO> businessPartnerDTOs) {
        return businessPartnerDTOs.stream().map(BusinessPartnerConverter::toEntityFromDTO).collect(Collectors.toList());
    }

    public static BusinessPartner toEntityFromDAO(BusinessPartnerDAO businessPartnerDAO) {
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDAO.getId());
        businessPartner.setName(businessPartnerDAO.getName());
        businessPartner.setAddress(AddressConverter.toEntityFromDAO(businessPartnerDAO.getAddress()));
        return businessPartner;
    }

    public static List<BusinessPartner> toEntityFromDAO(List<BusinessPartnerDAO> businessPartnerDAOs) {
        return businessPartnerDAOs.stream().map(BusinessPartnerConverter::toEntityFromDAO).collect(Collectors.toList());
    }

    public static BusinessPartnerDAO toDAO(BusinessPartner businessPartner) {
        BusinessPartnerDAO businessPartnerDAO = new BusinessPartnerDAO();
        businessPartnerDAO.setId(businessPartner.getId());
        businessPartnerDAO.setName(businessPartner.getName());
        businessPartnerDAO.setAddress(AddressConverter.toDAO(businessPartner.getAddress()));
        return businessPartnerDAO;
    }

    public static List<BusinessPartnerDAO> toDAO(List<BusinessPartner> businessPartners) {
        return businessPartners.stream().map(BusinessPartnerConverter::toDAO).collect(Collectors.toList());
    }
}
