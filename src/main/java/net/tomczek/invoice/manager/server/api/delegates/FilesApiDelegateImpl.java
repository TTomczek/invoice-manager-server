package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.FilesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.DownloadFileDTO;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import net.tomczek.invoice.manager.server.converter.FileConverter;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilesApiDelegateImpl implements FilesApiDelegate {

    private static final Logger logger = LoggerFactory.getLogger(FilesApiDelegateImpl.class);

    @Autowired
    public FilesApiDelegateImpl(IFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private final IFileStorageService fileStorageService;

    @Override
    public ResponseEntity<DownloadFileDTO> downloadFileById(Integer id) {
        try {
            FileWithContent fileWithContent = this.fileStorageService.getFile(id);
            DownloadFileDTO fileDTO = FileConverter.toDownloadDTO(fileWithContent);
            return ResponseEntity.ok(fileDTO);
        } catch (Exception e) {
            logger.warn("Could not download file with id [{}]", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Integer> uploadFile(Integer id, String fileName, MultipartFile data) {
        try {
            int fileId = this.fileStorageService.storeFile(fileName, data.getBytes());
            return ResponseEntity.ok(fileId);
        } catch (Exception e) {
            logger.warn("Could not upload file with id [{}]", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
