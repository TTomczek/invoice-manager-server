package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.InvoicePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicePositionsRepository extends JpaRepository<InvoicePosition, Integer> {
}
