package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.entities.ContactPersonDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.repositories.BusinessPartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactPersonConverter {

    @Autowired
    public ContactPersonConverter(BusinessPartnersRepository businessPartnersRepository, BusinessPartnerConverter businessPartnerConverter) {
        this.businessPartnersRepository = businessPartnersRepository;
        this.businessPartnerConverter = businessPartnerConverter;
    }

    private final BusinessPartnersRepository businessPartnersRepository;
    private final BusinessPartnerConverter businessPartnerConverter;

    public ContactPersonDAO toDAO(ContactPerson contactPerson) {
        ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
        contactPersonDAO.setId(contactPerson.getId());
        contactPersonDAO.setFirstName(contactPerson.getFirstName());
        contactPersonDAO.setName(contactPerson.getName());
        contactPersonDAO.setEmail(contactPerson.getEmail());
        contactPersonDAO.setSalutation(contactPerson.getSalutation());
        contactPersonDAO.setBusinessPartnerDAO(businessPartnerConverter.toDAO(contactPerson.getBusinessPartner()));
        contactPersonDAO.setAddress(contactPerson.getAddress());
        return contactPersonDAO;
    }

    public List<ContactPersonDAO> toDAO(List<ContactPerson> contactPersons) {
        return contactPersons.stream().map(this::toDAO).collect(Collectors.toList());
    }

    public ContactPerson toEntityFromDAO(ContactPersonDAO contactPersonDAO) {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(contactPersonDAO.getId());
        contactPerson.setFirstName(contactPersonDAO.getFirstName());
        contactPerson.setName(contactPersonDAO.getName());
        contactPerson.setEmail(contactPersonDAO.getEmail());
        contactPerson.setSalutation(contactPersonDAO.getSalutation());
        contactPerson.setBusinessPartner(businessPartnerConverter.toEntityFromDAO(contactPersonDAO.getBusinessPartnerDAO()));
        contactPerson.setAddress(contactPersonDAO.getAddress());
        return contactPerson;
    }

    public List<ContactPerson> toEntityFromDAO(List<ContactPersonDAO> contactPersonDAOs) {
        return contactPersonDAOs.stream().map(this::toEntityFromDAO).collect(Collectors.toList());
    }

    public ContactPerson toEntityFromDTO(ContactPersonDTO contactPersonDTO) {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(contactPersonDTO.getId());
        contactPerson.setFirstName(contactPersonDTO.getFirstName());
        contactPerson.setName(contactPersonDTO.getName());
        contactPerson.setEmail(contactPersonDTO.getEmail());
        contactPerson.setSalutation(SalutationConverter.toET(contactPersonDTO.getSalutation()));

        BusinessPartnerDAO businessPartnerDAO = businessPartnersRepository.findById(contactPersonDTO.getBusinessPartner()).orElse(null);
        BusinessPartner businessPartner = businessPartnerConverter.toEntityFromDAO(businessPartnerDAO);
        contactPerson.setBusinessPartner(businessPartner);
        return contactPerson;
    }

    public List<ContactPerson> toEntityFromDTO(List<ContactPersonDTO> contactPersonDTOs) {
        return contactPersonDTOs.stream().map(this::toEntityFromDTO).collect(Collectors.toList());
    }

    public ContactPersonDTO toDTO(ContactPerson contactPerson) {
        ContactPersonDTO contactPersonDTO = new ContactPersonDTO();
        contactPersonDTO.setId(contactPerson.getId());
        contactPersonDTO.setFirstName(contactPerson.getFirstName());
        contactPersonDTO.setName(contactPerson.getName());
        contactPersonDTO.setEmail(contactPerson.getEmail());
        return contactPersonDTO;
    }

    public List<ContactPersonDTO> toDTO(List<ContactPerson> contactPersons) {
        return contactPersons.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
