package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.DownloadFileDTO;
import net.tomczek.invoice.manager.server.models.FileWithContent;

public class FileConverter {

    public static DownloadFileDTO toDownloadDTO(FileWithContent fileWithContent) {
        if (fileWithContent == null) {
            return null;
        }
        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();
        downloadFileDTO.setId(fileWithContent.getId());
        downloadFileDTO.setFileName(fileWithContent.getFileName());
        downloadFileDTO.setData(fileWithContent.getFileContent());
        return downloadFileDTO;
    }
}
