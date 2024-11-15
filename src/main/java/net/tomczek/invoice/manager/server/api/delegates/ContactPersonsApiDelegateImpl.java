package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.ContactPersonsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactPersonsApiDelegateImpl implements ContactPersonsApiDelegate {

    @Override
    public ResponseEntity<List<ContactPersonDTO>> contactPersonsGet() {
        return ContactPersonsApiDelegate.super.contactPersonsGet();
    }

    @Override
    public ResponseEntity<ContactPersonDTO> createContactPerson(ContactPersonDTO contactPersonDTO) {
        return ContactPersonsApiDelegate.super.createContactPerson(contactPersonDTO);
    }

    @Override
    public ResponseEntity<Void> deleteContactPersonById(Integer id) {
        return ContactPersonsApiDelegate.super.deleteContactPersonById(id);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> getContactPersonById(Integer id) {
        return ContactPersonsApiDelegate.super.getContactPersonById(id);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> updateContactPersonById(Integer id, ContactPersonDTO contactPersonDTO) {
        return ContactPersonsApiDelegate.super.updateContactPersonById(id, contactPersonDTO);
    }
}
