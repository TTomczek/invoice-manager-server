package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class BusinessPartner extends BaseEntity<Integer> {

        private String name;

        private String description;

        @ManyToOne
        private Address address;

        @OneToMany(mappedBy = "businessPartner")
        private List<ContactPerson> contactPersons;
}
