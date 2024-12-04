package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Integer> {
}
