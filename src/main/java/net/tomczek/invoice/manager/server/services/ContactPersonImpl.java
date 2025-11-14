package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.Address;
import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import net.tomczek.invoice.manager.server.entities.ContactPerson;
import net.tomczek.invoice.manager.server.jpa.specifications.ContactPersonSpecifications;
import net.tomczek.invoice.manager.server.repositories.ContactPersonsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonImpl implements IContactPersonService {

    private static final Logger logger = LoggerFactory.getLogger(ContactPersonImpl.class);

    @Autowired
    public ContactPersonImpl(ContactPersonsRepository contactPersonsRepository, IAddressService addressService) {
        this.contactPersonsRepository = contactPersonsRepository;
        this.addressService = addressService;
    }

    private final ContactPersonsRepository contactPersonsRepository;
    private final IAddressService addressService;


    @Override
    public ContactPerson createContactPerson(ContactPerson contactPerson) {
        Address address = contactPerson.getAddress();
        Address savedAddress = addressService.createAddress(address);
        contactPerson.setAddress(savedAddress);
        ContactPerson savedContactPerson = contactPersonsRepository.save(contactPerson);
        logger.debug("ContactPerson saved: {}", savedContactPerson);
        return savedContactPerson;
    }

    @Override
    public ContactPerson deleteContactPersonById(Integer id) {
        ContactPerson contactPerson = contactPersonsRepository.findById(id).orElse(null);
        if (contactPerson == null) {
            return null;
        }

        contactPersonsRepository.deleteById(id);
        logger.debug("ContactPerson deleted: {}", contactPerson);
        return contactPerson;
    }

    @Override
    public List<ContactPerson> getAllContactPersons(String name, String firstName, Integer businessPartner) {
        Specification<ContactPerson> specification = Specification
            .where(ContactPersonSpecifications.byName(name))
            .and(ContactPersonSpecifications.byFirstName(firstName))
            .and(ContactPersonSpecifications.byBusinessPartnerId(businessPartner));
        List<ContactPerson> contactPeople = contactPersonsRepository.findAll(specification);
        logger.debug("Fetched contactPersons with filters - name: '{}', firstName: '{}', businessPartner: '{}', count: {}",
                    name, firstName, businessPartner, contactPeople.size());
        return contactPeople;
    }

    @Override
    public ContactPerson getContactPersonById(Integer id) {
        ContactPerson contactPerson = contactPersonsRepository.findById(id).orElse(null);
        logger.debug("Fetched contactPerson: {}", contactPerson);
        return contactPerson;
    }

    @Override
    public ContactPerson updateContactPersonById(Integer id, ContactPerson contactPerson) {
        ContactPerson contactPersonToUpdate = contactPersonsRepository.findById(id).orElse(null);
        if (contactPersonToUpdate == null) {
            return null;
        }

        contactPersonToUpdate.setName(contactPerson.getName());
        contactPersonToUpdate.setFirstName(contactPerson.getFirstName());
        contactPersonToUpdate.setEmail(contactPerson.getEmail());

        contactPersonToUpdate.getAddress().setCity(contactPerson.getAddress().getCity());
        contactPersonToUpdate.getAddress().setCountry(contactPerson.getAddress().getCountry());
        contactPersonToUpdate.getAddress().setStreet(contactPerson.getAddress().getStreet());
        contactPersonToUpdate.getAddress().setZipCode(contactPerson.getAddress().getZipCode());
        contactPersonToUpdate.getAddress().setHouseNumber(contactPerson.getAddress().getHouseNumber());

        BusinessPartner businessPartner = contactPerson.getBusinessPartner();
        contactPersonToUpdate.setBusinessPartner(businessPartner);
        contactPersonToUpdate.setSalutation(contactPerson.getSalutation());

        ContactPerson updatedContactPerson = contactPersonsRepository.save(contactPersonToUpdate);
        logger.debug("ContactPerson updated: {}", updatedContactPerson);
        return updatedContactPerson;
    }

    @Override
    public List<ContactPerson> getAllContactPersonByIds(List<Integer> ids) {
        List<ContactPerson> contactPersons = contactPersonsRepository.findAllById(ids);
        logger.debug("Fetched contactPersons by ids: {}", contactPersons.size());
        return contactPersons;
    }

    @Override
    public boolean exists(Integer id) {
        return contactPersonsRepository.existsById(id);
    }
}
