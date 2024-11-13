package net.tomczek.invoice.manager.server.repositories;


import net.tomczek.invoice.manager.server.entities.SalesTaxDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRepository extends JpaRepository<SalesTaxDAO, Integer> {
}
