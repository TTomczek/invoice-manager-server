package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Profile;

@Table(name = "local_file_storage_files")
@Entity
@Profile("localfilestorage")
public class LocalFileStorageFileDAO extends BaseEntity<Integer> {

    public LocalFileStorageFileDAO(Integer id, String filename) {
        super(id);
        this.filename = filename;
    }

    public LocalFileStorageFileDAO() {
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
