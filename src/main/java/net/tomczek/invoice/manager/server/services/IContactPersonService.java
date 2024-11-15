package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.ContactPerson;

import java.util.List;

public interface IContactPersonService {

    public ContactPerson createContactPerson(ContactPerson contactPerson);

    public ContactPerson deleteContactPersonById(Integer id);

    public List<ContactPerson> getAllContactPersons();

    public ContactPerson getContactPersonById(Integer id);

    public ContactPerson updateContactPersonById(Integer id, ContactPerson contactPerson);
}
