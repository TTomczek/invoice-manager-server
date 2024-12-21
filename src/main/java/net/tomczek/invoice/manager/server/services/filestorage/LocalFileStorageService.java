package net.tomczek.invoice.manager.server.services.filestorage;

import net.tomczek.invoice.manager.server.entities.LocalFileStorageFile;
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
import java.util.List;
import java.util.Objects;
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

        LocalFileStorageFile fileDAO = new LocalFileStorageFile();
        fileDAO.setFilename(fileName);
        LocalFileStorageFile savedFile = localFileStorageFileRepository.save(fileDAO);

        String extension = fileName.substring(fileName.lastIndexOf("."));
        String path = properties.getStoragePath() + "/" + savedFile.getId() + extension;
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
        Optional<LocalFileStorageFile> fileOptional = localFileStorageFileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            LocalFileStorageFile fileNameMapping = fileOptional.get();
            String extension = fileNameMapping.getFilename().substring(fileNameMapping.getFilename().lastIndexOf("."));
            String path = properties.getStoragePath() + "/" + fileId + extension;
            logger.debug("Retrieving file with id [{}] from: [{}]", fileId, path);
            return new FileWithContent(fileNameMapping.getId(), fileNameMapping.getFilename(), Files.readAllBytes(Paths.get(path)));
        }
        return null;
    }

    @Override
    public boolean deleteFile(int fileId) throws Exception {
        Optional<LocalFileStorageFile> storedFile = localFileStorageFileRepository.findById(fileId);
        if (storedFile.isEmpty()) {
            return false;
        }
        localFileStorageFileRepository.delete(storedFile.get());
        String extension = storedFile.get().getFilename().substring(storedFile.get().getFilename().lastIndexOf("."));

        String path = properties.getStoragePath() + "/" + fileId + extension;
        logger.debug("Deleting file with id [{}] from: [{}]", fileId, path);
        return new File(path).delete();
    }

    @Override
    public boolean fileExists(int fileId) throws Exception {
        File storageDirectory = new File(properties.getStoragePath());
        if (!storageDirectory.exists()) {
            return false;
        }

        File[] files = storageDirectory.listFiles((dir, name) -> name.startsWith(fileId + "."));
        if (Objects.isNull(files) || files.length == 0) {
            return false;
        }

        return files[0].exists();
    }

    private boolean initStorageDirectoryIfNecessary() {
        File storageDirectory = new File(properties.getStoragePath());
        if (!storageDirectory.exists()) {
            return storageDirectory.mkdirs();
        }
        return true;
    }
}
