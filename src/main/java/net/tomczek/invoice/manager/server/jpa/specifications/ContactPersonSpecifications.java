package net.tomczek.invoice.manager.server.jpa.specifications;

import net.tomczek.invoice.manager.server.entities.ContactPerson;
import org.springframework.data.jpa.domain.Specification;

public class ContactPersonSpecifications {

    public static Specification<ContactPerson> byFirstName(String firstName) {
        return (root, query, cb) -> {
            if (firstName == null || firstName.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<ContactPerson> byName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<ContactPerson> byBusinessPartnerId(Integer businessPartnerId) {
        return (root, query, cb) -> {
            if (businessPartnerId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("businessPartner").get("id"), businessPartnerId);
        };
    }
}
