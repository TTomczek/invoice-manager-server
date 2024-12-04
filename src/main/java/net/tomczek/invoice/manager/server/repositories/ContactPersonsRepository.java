package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonsRepository extends JpaRepository<ContactPerson, Integer> {

}
