package net.tomczek.invoice.manager.server.models;

import java.util.Arrays;

/**
 * Internal model for LocalFileStorageFileDAO
 */
public class FileWithContent {

    private Integer id;
    private String fileName;
    private byte[] fileContent;

    public FileWithContent(Integer id, String fileName, byte[] fileContent) {
        this.id = id;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public FileWithContent() {
    }

    public FileWithContent setId(Integer id) {
        this.id = id;
        return this;
    }

    public FileWithContent setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileWithContent setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    @Override
    public String toString() {
        return "FileWithContent{" +
            "id=" + id +
            ", fileName='" + fileName + '\'' +
            ", fileContent=" + Arrays.toString(fileContent) +
            '}';
    }
}
