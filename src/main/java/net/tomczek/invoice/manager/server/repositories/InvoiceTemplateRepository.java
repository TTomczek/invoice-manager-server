package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.InvoiceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplate, Integer> {
}
