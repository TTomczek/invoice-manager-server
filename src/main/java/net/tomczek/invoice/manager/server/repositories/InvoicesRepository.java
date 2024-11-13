package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.InvoiceDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<InvoiceDAO, Integer> {
}
