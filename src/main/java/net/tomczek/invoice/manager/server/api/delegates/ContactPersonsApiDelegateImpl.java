package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.ContactPersonsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import net.tomczek.invoice.manager.server.entities.ContactPerson;
import net.tomczek.invoice.manager.server.converter.ContactPersonConverter;
import net.tomczek.invoice.manager.server.services.IBusinessPartnerService;
import net.tomczek.invoice.manager.server.services.IContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactPersonsApiDelegateImpl implements ContactPersonsApiDelegate {

    @Autowired
    public ContactPersonsApiDelegateImpl(IContactPersonService contactPersonService, IBusinessPartnerService businessPartnerService) {
        this.contactPersonService = contactPersonService;
        this.businessPartnerService = businessPartnerService;
    }

    private final IContactPersonService contactPersonService;
    private final IBusinessPartnerService businessPartnerService;

    @Override
    public ResponseEntity<List<ContactPersonDTO>> getAllContactPersons(String name, String firstName, Integer businessPartner) {
        List<ContactPerson> contactPersons = this.contactPersonService.getAllContactPersons(name, firstName, businessPartner);
        List<ContactPersonDTO> contactPersonDTOs = ContactPersonConverter.toDTO(contactPersons);
        return ResponseEntity.ok(contactPersonDTOs);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> createContactPerson(ContactPersonDTO contactPersonDTO) {
        ContactPerson contactPerson = ContactPersonConverter.toEntity(contactPersonDTO, businessPartnerService);
        ContactPerson createdContactPerson = this.contactPersonService.createContactPerson(contactPerson);
        ContactPersonDTO createdContactPersonDTO = ContactPersonConverter.toDTO(createdContactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactPersonDTO);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> deleteContactPersonById(Integer id) {
        ContactPerson deletedContactPerson = this.contactPersonService.deleteContactPersonById(id);
        if (deletedContactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO deletedContactPersonDTO = ContactPersonConverter.toDTO(deletedContactPerson);
            return ResponseEntity.ok(deletedContactPersonDTO);
        }
    }

    @Override
    public ResponseEntity<ContactPersonDTO> getContactPersonById(Integer id) {
        ContactPerson contactPerson = this.contactPersonService.getContactPersonById(id);
        if (contactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO contactPersonDTO = ContactPersonConverter.toDTO(contactPerson);
            return ResponseEntity.ok(contactPersonDTO);
        }
    }

    @Override
    public ResponseEntity<ContactPersonDTO> updateContactPersonById(Integer id, ContactPersonDTO contactPersonDTO) {
        ContactPerson contactPerson = ContactPersonConverter.toEntity(contactPersonDTO, businessPartnerService);
        ContactPerson updatedContactPerson = this.contactPersonService.updateContactPersonById(id, contactPerson);
        if (updatedContactPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            ContactPersonDTO updatedContactPersonDTO = ContactPersonConverter.toDTO(updatedContactPerson);
            return ResponseEntity.ok(updatedContactPersonDTO);
        }
    }
}
