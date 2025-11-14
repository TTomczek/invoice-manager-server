package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessPartnersRepository extends JpaRepository<BusinessPartner, Integer>, JpaSpecificationExecutor<BusinessPartner> {
    List<BusinessPartner> findAllByNameContains(String name);
}
