package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.FileDTO;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import org.springframework.core.io.ByteArrayResource;

public class FileConverter {

    public static FileDTO toDTO(FileWithContent fileWithContent) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(fileWithContent.getId());
        fileDTO.setFileName(fileWithContent.getFileName());
        fileDTO.setData(new ByteArrayResource(fileWithContent.getFileContent()));
        return fileDTO;
    }

    public static FileWithContent toEntityFromDTO(FileDTO fileDTO) {
        try {
            FileWithContent fileWithContent = new FileWithContent();
            fileWithContent.setId(fileDTO.getId());
            fileWithContent.setFileName(fileDTO.getFileName());
            fileWithContent.setFileContent(fileDTO.getData().getContentAsByteArray());
            return fileWithContent;
        } catch (Exception e) {
            return null;
        }
    }
}
