package net.tomczek.invoice.manager.server.jpa.specifications;

import net.tomczek.invoice.manager.server.entities.Invoice;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecifications {

    public static Specification<Invoice> byPaid(Boolean paid) {
        return (root, query, cb) -> {
            if (paid == null) {
                return cb.conjunction(); // keine Einschränkung
            }
            return cb.equal(root.get("paid"), paid);
        };
    }

    public static Specification<Invoice> byCustomerNumber(Integer customerNumber) {
        return (root, query, cb) -> {
            if (customerNumber == null) {
                return cb.conjunction(); // keine Einschränkung
            }
            return cb.equal(root.get("customer").get("id"), customerNumber);
        };
    }

    public static Specification<Invoice> byReceiver(Integer receiver) {
        return (root, query, cb) -> {
            if (receiver == null) {
                return cb.conjunction(); // keine Einschränkung
            }
            return cb.equal(root.get("receiver").get("id"), receiver);
        };
    }

    public static Specification<Invoice> byOrderNumber(String orderNumber) {
        return (root, query, cb) -> {
            if (orderNumber == null || orderNumber.isBlank()) {
                return cb.conjunction(); // keine Einschränkung
            }
            return cb.like(cb.lower(root.get("orderNumber")), "%" + orderNumber.toLowerCase() + "%");
        };
    }
}
