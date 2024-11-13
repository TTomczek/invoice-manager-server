package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.BusinessPartnerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessPartnersRepository extends JpaRepository<BusinessPartnerDAO, Integer> {
}
