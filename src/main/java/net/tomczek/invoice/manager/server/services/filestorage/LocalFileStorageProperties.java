package net.tomczek.invoice.manager.server.services.filestorage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file-storage.local")
public class LocalFileStorageProperties {

    public LocalFileStorageProperties() {
    }

    public LocalFileStorageProperties(String storagePath) {
        this.storagePath = storagePath;
    }

    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }
}
