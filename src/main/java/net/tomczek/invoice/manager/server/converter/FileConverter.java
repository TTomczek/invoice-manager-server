package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.DownloadFileDTO;
import net.tomczek.invoice.manager.api.server.model.FileDTO;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FileConverter {

    public static FileDTO toDTO(FileWithContent fileWithContent) throws IOException {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(fileWithContent.getId());
        fileDTO.setFileName(fileWithContent.getFileName());
        ByteArrayInputStream bis = new ByteArrayInputStream(fileWithContent.getFileContent());
        InputStreamResource isr = new InputStreamResource(bis);
        fileDTO.setData(isr);
        return fileDTO;
    }

    public static DownloadFileDTO toDownloadDTO(FileWithContent fileWithContent) {
        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();
        downloadFileDTO.setId(fileWithContent.getId());
        downloadFileDTO.setFileName(fileWithContent.getFileName());
        downloadFileDTO.setData(fileWithContent.getFileContent());
        return downloadFileDTO;
    }

    public static FileWithContent toEntityFromDTO(Integer id, String filename, byte[] content) {
        try {
            FileWithContent fileWithContent = new FileWithContent();
            fileWithContent.setId(id);
            fileWithContent.setFileName(filename);
            fileWithContent.setFileContent(content);
            return fileWithContent;
        } catch (Exception e) {
            return null;
        }
    }
}
