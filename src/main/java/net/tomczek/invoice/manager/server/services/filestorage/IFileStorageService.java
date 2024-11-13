package net.tomczek.invoice.manager.server.services.filestorage;

import net.tomczek.invoice.manager.server.models.FileWithContent;

import java.io.File;

/**
 * Interface for file storage service
 */
public interface IFileStorageService {

    int storeFile(String fileName, byte[] fileContent) throws Exception;

    FileWithContent getFile(int fileId) throws Exception;

    boolean deleteFile(int fileId) throws Exception;

    boolean fileExists(int fileId) throws Exception;
}
