package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
