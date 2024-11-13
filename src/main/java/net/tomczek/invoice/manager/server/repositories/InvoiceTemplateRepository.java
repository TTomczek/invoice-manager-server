package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.InvoiceTemplateDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplateDAO, Integer> {
}
