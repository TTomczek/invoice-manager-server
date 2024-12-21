package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import net.tomczek.invoice.manager.server.entities.ContactPerson;
import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.services.IContactPersonService;
import net.tomczek.invoice.manager.server.services.IInvoiceService;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessPartnerConverter {

    public static BusinessPartnerDTO toDTO(BusinessPartner businessPartner) {
        if (businessPartner == null) {
            return null;
        }
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
        if (businessPartners == null) {
            return null;
        }
        return businessPartners.stream().map(BusinessPartnerConverter::toDTO).collect(Collectors.toList());
    }

    public static BusinessPartner toEntity(BusinessPartnerDTO businessPartnerDTO, IContactPersonService cps, IInvoiceService is) {
        if (businessPartnerDTO == null) {
            return null;
        }
        BusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setId(businessPartnerDTO.getId());
        businessPartner.setName(businessPartnerDTO.getName());
        businessPartner.setDescription(businessPartnerDTO.getDescription());
        businessPartner.setAddress(AddressConverter.toEntity(businessPartnerDTO.getAddress()));
        businessPartner.setInvoices(is.getAllInvoiceByIds(businessPartnerDTO.getInvoices()));
        businessPartner.setContactPersons(cps.getAllContactPersonByIds(businessPartnerDTO.getContactPersons()));
        return businessPartner;
    }

    public static List<BusinessPartner> toEntity(List<BusinessPartnerDTO> businessPartnerDTOs, IContactPersonService cps, IInvoiceService is) {
        if (businessPartnerDTOs == null) {
            return null;
        }
        return businessPartnerDTOs.stream().map((bp) -> toEntity(bp, cps, is)).collect(Collectors.toList());
    }
}
