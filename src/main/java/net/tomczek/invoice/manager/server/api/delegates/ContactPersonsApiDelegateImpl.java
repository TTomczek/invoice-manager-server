package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.ContactPersonsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import net.tomczek.invoice.manager.server.models.ContactPerson;
import net.tomczek.invoice.manager.server.models.converter.ContactPersonConverter;
import net.tomczek.invoice.manager.server.services.IContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactPersonsApiDelegateImpl implements ContactPersonsApiDelegate {

    @Autowired
    public ContactPersonsApiDelegateImpl(IContactPersonService contactPersonService, ContactPersonConverter contactPersonConverter) {
        this.contactPersonService = contactPersonService;
        this.contactPersonConverter = contactPersonConverter;
    }

    private final IContactPersonService contactPersonService;
    private final ContactPersonConverter contactPersonConverter;

    @Override
    public ResponseEntity<List<ContactPersonDTO>> getAllContactPersons() {
        List<ContactPerson> contactPersons = this.contactPersonService.getAllContactPersons();
        List<ContactPersonDTO> contactPersonDTOs = this.contactPersonConverter.toDTO(contactPersons);
        return ResponseEntity.ok(contactPersonDTOs);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> createContactPerson(ContactPersonDTO contactPersonDTO) {
        ContactPerson contactPerson = this.contactPersonConverter.toEntityFromDTO(contactPersonDTO);
        ContactPerson createdContactPerson = this.contactPersonService.createContactPerson(contactPerson);
        ContactPersonDTO createdContactPersonDTO = this.contactPersonConverter.toDTO(createdContactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactPersonDTO);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> deleteContactPersonById(Integer id) {
        ContactPerson deletedContactPerson = this.contactPersonService.deleteContactPersonById(id);
        if (deletedContactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO deletedContactPersonDTO = this.contactPersonConverter.toDTO(deletedContactPerson);
            return ResponseEntity.ok(deletedContactPersonDTO);
        }
    }

    @Override
    public ResponseEntity<ContactPersonDTO> getContactPersonById(Integer id) {
        ContactPerson contactPerson = this.contactPersonService.getContactPersonById(id);
        if (contactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO contactPersonDTO = this.contactPersonConverter.toDTO(contactPerson);
            return ResponseEntity.ok(contactPersonDTO);
        }
    }

    @Override
    public ResponseEntity<ContactPersonDTO> updateContactPersonById(Integer id, ContactPersonDTO contactPersonDTO) {
        ContactPerson contactPerson = this.contactPersonConverter.toEntityFromDTO(contactPersonDTO);
        ContactPerson updatedContactPerson = this.contactPersonService.updateContactPersonById(id, contactPerson);
        if (updatedContactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO updatedContactPersonDTO = this.contactPersonConverter.toDTO(updatedContactPerson);
            return ResponseEntity.ok(updatedContactPersonDTO);
        }
    }
}
