package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class ContactPerson extends BaseEntity<Integer> {

    private String name;

    private String firstName;

    private String email;

    @ManyToOne
    private Address address;

    @ManyToOne
    private BusinessPartner businessPartner;

    @Enumerated(EnumType.STRING)
    private SalutationET salutation;
}
