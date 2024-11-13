package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.ContactPersonDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonsRepository extends JpaRepository<ContactPersonDAO, Integer> {
}
