package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Profile;

@Table(name = "local_file_storage_files")
@Entity
@Profile("localfilestorage")
public class LocalFileStorageFile extends BaseEntity<Integer> {

    public LocalFileStorageFile(Integer id, String filename) {
        super(id);
        this.filename = filename;
    }

    public LocalFileStorageFile() {
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "LocalFileStorageFile" +
            "filename='" + filename + '\'' +
            ", id=" + id +
            '}';
    }
}
