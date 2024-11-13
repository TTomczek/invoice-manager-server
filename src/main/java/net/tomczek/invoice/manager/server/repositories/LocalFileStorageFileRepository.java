package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.LocalFileStorageFileDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalFileStorageFileRepository extends JpaRepository<LocalFileStorageFileDAO, Integer> {
}
