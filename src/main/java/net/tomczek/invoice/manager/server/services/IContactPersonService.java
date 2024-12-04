package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.ContactPerson;

import java.util.List;

public interface IContactPersonService {

    ContactPerson createContactPerson(ContactPerson contactPerson);

    ContactPerson deleteContactPersonById(Integer id);

    List<ContactPerson> getAllContactPersons();

    ContactPerson getContactPersonById(Integer id);

    ContactPerson updateContactPersonById(Integer id, ContactPerson contactPerson);

    List<ContactPerson> getAllContactPersonByIds(List<Integer> ids);

    boolean exists(Integer id);
}
