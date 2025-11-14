package net.tomczek.invoice.manager.server.jpa.specifications;

import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import org.springframework.data.jpa.domain.Specification;

public class BusinessPartnerSpecifications {

    public static Specification<BusinessPartner> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction(); // keine Einschränkung
            }
            return cb.like(root.get("name"), "%" + name + "%");
        };
    }
}
