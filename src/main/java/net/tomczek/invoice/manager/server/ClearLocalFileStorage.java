package net.tomczek.invoice.manager.server;

import net.tomczek.invoice.manager.server.services.filestorage.LocalFileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Profile("localfilestorage")
public class ClearLocalFileStorage implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ClearLocalFileStorage.class);

    @Autowired
    ClearLocalFileStorage(LocalFileStorageProperties localFileStorageProperties) {
        this.localFileStorageProperties = localFileStorageProperties;
    }

    private final LocalFileStorageProperties localFileStorageProperties;

    @Override
    public void run(String... args) throws Exception {
        File dir = new File(localFileStorageProperties.getStoragePath());
        if (dir.exists() ) {
            if (dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    file.delete();
                }
            }
            dir.delete();
        }
        logger.debug("Local file storage cleared at path: [{}]", localFileStorageProperties.getStoragePath());
    }
}
