package net.tomczek.invoice.manager.server.services.filestorage;

import net.tomczek.invoice.manager.server.entities.LocalFileStorageFileDAO;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import net.tomczek.invoice.manager.server.repositories.LocalFileStorageFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Saves files on the local file system
 */
@Service
@Profile("localfilestorage")
public class LocalFileStorageService implements IFileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(LocalFileStorageService.class);

    @Autowired
    public LocalFileStorageService(LocalFileStorageProperties properties, LocalFileStorageFileRepository localFileStorageFileRepository) {
        this.properties = properties;
        this.localFileStorageFileRepository = localFileStorageFileRepository;
    }

    private final LocalFileStorageFileRepository localFileStorageFileRepository;
    private final LocalFileStorageProperties properties;

    @Override
    public int storeFile(String fileName, byte[] fileContent) throws Exception {
        boolean creationSuccess = initStorageDirectoryIfNecessary();
        if (!creationSuccess) {
            logger.error("Could not create storage directory at path [{}]", properties.getStoragePath());
            throw new Exception("Could not create storage directory");
        }

        LocalFileStorageFileDAO fileDAO = new LocalFileStorageFileDAO();
        fileDAO.setFilename(fileName);
        LocalFileStorageFileDAO savedFile = localFileStorageFileRepository.save(fileDAO);

        String path = properties.getStoragePath() + "/" + savedFile.getId() + ".pdf";
        logger.debug("Storing file with id [{}] in: [{}]", savedFile.getId(), path);

        File file = new File(path);
        boolean fileCreated = file.createNewFile();
        if (!fileCreated) {
            logger.error("Could not create file at path [{}]", path);
            throw new Exception("Could not create file");
        }
        Path newPath = Files.write(file.toPath(), fileContent);
        logger.debug("File stored in: [{}]", newPath);

        return savedFile.getId();
    }

    @Override
    public FileWithContent getFile(int fileId) throws Exception {
        Optional<LocalFileStorageFileDAO> fileOptional = localFileStorageFileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            LocalFileStorageFileDAO fileNameMapping = fileOptional.get();
            String path = properties.getStoragePath() + "/" + fileId + ".pdf";
            logger.debug("Retrieving file with id [{}] from: [{}]", fileId, path);
            return new FileWithContent(fileNameMapping.getId(), fileNameMapping.getFilename(), Files.readAllBytes(Paths.get(path)));
        }
        return null;
    }

    @Override
    public boolean deleteFile(int fileId) throws Exception {
        localFileStorageFileRepository.findById(fileId).ifPresent(localFileStorageFileRepository::delete);
        String path = properties.getStoragePath() + "/" + fileId + ".pdf";
        logger.debug("Deleting file with id [{}] from: [{}]", fileId, path);
        return new File(path).delete();
    }

    @Override
    public boolean fileExists(int fileId) throws Exception {
        String path = properties.getStoragePath() + "/" + fileId + ".pdf";
        return new File(path).exists();
    }

    private boolean initStorageDirectoryIfNecessary() {
        File storageDirectory = new File(properties.getStoragePath());
        if (!storageDirectory.exists()) {
            return storageDirectory.mkdirs();
        }
        return true;
    }
}
