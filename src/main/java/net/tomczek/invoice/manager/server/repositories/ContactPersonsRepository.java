package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactPersonsRepository extends JpaRepository<ContactPerson, Integer>, JpaSpecificationExecutor<ContactPerson> {
}
