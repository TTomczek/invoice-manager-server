package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.AddressDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressDAO, Integer> {
}
