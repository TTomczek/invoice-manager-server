package net.tomczek.invoice.manager.server.repositories;

import net.tomczek.invoice.manager.server.entities.LocalFileStorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalFileStorageFileRepository extends JpaRepository<LocalFileStorageFile, Integer> {
}
