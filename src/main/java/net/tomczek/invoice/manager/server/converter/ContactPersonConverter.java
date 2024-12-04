package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import net.tomczek.invoice.manager.server.entities.ContactPerson;
import net.tomczek.invoice.manager.server.services.IBusinessPartnerService;

import java.util.List;
import java.util.stream.Collectors;

public class ContactPersonConverter {

    public static ContactPerson toEntity(ContactPersonDTO contactPersonDTO, IBusinessPartnerService bps) {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(contactPersonDTO.getId());
        contactPerson.setFirstName(contactPersonDTO.getFirstName());
        contactPerson.setName(contactPersonDTO.getName());
        contactPerson.setEmail(contactPersonDTO.getEmail());
        contactPerson.setSalutation(SalutationConverter.toET(contactPersonDTO.getSalutation()));
        contactPerson.setAddress(AddressConverter.toEntity(contactPersonDTO.getAddress()));
        contactPerson.setBusinessPartner(bps.getBusinessPartnerById(contactPersonDTO.getBusinessPartner()));
        return contactPerson;
    }

    public static List<ContactPerson> toEntity(List<ContactPersonDTO> contactPersonsDTOs, IBusinessPartnerService bps) {
        return contactPersonsDTOs.stream().map((cp) -> toEntity(cp, bps)).collect(Collectors.toList());
    }

    public static ContactPersonDTO toDTO(ContactPerson contactPerson) {
        ContactPersonDTO contactPersonDTO = new ContactPersonDTO();
        contactPersonDTO.setId(contactPerson.getId());
        contactPersonDTO.setFirstName(contactPerson.getFirstName());
        contactPersonDTO.setName(contactPerson.getName());
        contactPersonDTO.setEmail(contactPerson.getEmail());
        contactPersonDTO.setSalutation(SalutationConverter.toDTO(contactPerson.getSalutation()));
        contactPersonDTO.setAddress(AddressConverter.toDTO(contactPerson.getAddress()));
        return contactPersonDTO;
    }

    public static List<ContactPersonDTO> toDTO(List<ContactPerson> contactPersons) {
        return contactPersons.stream().map(ContactPersonConverter::toDTO).collect(Collectors.toList());
    }
}
