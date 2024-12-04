package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessPartnersRepository extends JpaRepository<BusinessPartner, Integer> {
}
