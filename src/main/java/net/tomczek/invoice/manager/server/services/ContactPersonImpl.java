package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import net.tomczek.invoice.manager.server.entities.ContactPersonDAO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.models.converter.BusinessPartnerConverter;
import net.tomczek.invoice.manager.server.models.converter.ContactPersonConverter;
import net.tomczek.invoice.manager.server.repositories.ContactPersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonImpl implements IContactPersonService {

    @Autowired
    public ContactPersonImpl(ContactPersonsRepository contactPersonsRepository, ContactPersonConverter contactPersonConverter) {
        this.contactPersonsRepository = contactPersonsRepository;
        this.contactPersonConverter = contactPersonConverter;
    }

    private final ContactPersonsRepository contactPersonsRepository;
    private final ContactPersonConverter contactPersonConverter;


    @Override
    public ContactPerson createContactPerson(ContactPerson contactPerson) {
        ContactPersonDAO contactPersonDAOToSave = contactPersonConverter.toDAO(contactPerson);
        ContactPersonDAO savedContactPersonDAO = contactPersonsRepository.save(contactPersonDAOToSave);
        ContactPerson savedContactPerson = contactPersonConverter.toEntityFromDAO(savedContactPersonDAO);
        return savedContactPerson;
    }

    @Override
    public ContactPerson deleteContactPersonById(Integer id) {
        ContactPersonDAO contactPersonDAO = contactPersonsRepository.findById(id).orElse(null);
        if (contactPersonDAO == null) {
            return null;
        }

        contactPersonsRepository.deleteById(id);
        return contactPersonConverter.toEntityFromDAO(contactPersonDAO);
    }

    @Override
    public List<ContactPerson> getAllContactPersons() {
        List<ContactPersonDAO> contactPersonDAOs = contactPersonsRepository.findAll();
        return contactPersonConverter.toEntityFromDAO(contactPersonDAOs);
    }

    @Override
    public ContactPerson getContactPersonById(Integer id) {
        ContactPersonDAO contactPersonDAO = contactPersonsRepository.findById(id).orElse(null);
        return contactPersonConverter.toEntityFromDAO(contactPersonDAO);
    }

    @Override
    public ContactPerson updateContactPersonById(Integer id, ContactPerson contactPerson) {
        ContactPersonDAO contactPersonDAO = contactPersonsRepository.findById(id).orElse(null);
        if (contactPersonDAO == null) {
            return null;
        }

        contactPersonDAO.setName(contactPerson.getName());
        contactPersonDAO.setFirstName(contactPerson.getFirstName());
        contactPersonDAO.setEmail(contactPerson.getEmail());

        contactPersonDAO.getAddress().setCity(contactPerson.getAddress().getCity());
        contactPersonDAO.getAddress().setCountry(contactPerson.getAddress().getCountry());
        contactPersonDAO.getAddress().setStreet(contactPerson.getAddress().getStreet());
        contactPersonDAO.getAddress().setZipCode(contactPerson.getAddress().getZipCode());
        contactPersonDAO.getAddress().setHouseNumber(contactPerson.getAddress().getHouseNumber());

        BusinessPartner businessPartner = contactPerson.getBusinessPartner();
        BusinessPartnerDAO businessPartnerDAO = BusinessPartnerConverter.toDAO(businessPartner);
        contactPersonDAO.setBusinessPartner(businessPartnerDAO);
        contactPersonDAO.setSalutation(contactPerson.getSalutation());

        ContactPersonDAO updatedContactPersonDAO = contactPersonsRepository.save(contactPersonDAO);
        return contactPersonConverter.toEntityFromDAO(updatedContactPersonDAO);
    }
}
