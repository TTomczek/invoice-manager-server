package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.FilesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.ErrorDTO;
import net.tomczek.invoice.manager.api.server.model.FileDTO;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import net.tomczek.invoice.manager.server.models.converter.FileConverter;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FilesApiDelegateImpl implements FilesApiDelegate {

    @Autowired
    public FilesApiDelegateImpl(IFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private IFileStorageService fileStorageService;

    @Override
    public ResponseEntity<FileDTO> downloadFileById(Integer id) {
        try {
            FileWithContent fileWithContent = fileStorageService.getFile(id);
            if (fileWithContent == null) {
                return ResponseEntity.notFound().build();
            }
            FileDTO fileDTO = FileConverter.toDTO(fileWithContent);
            return ResponseEntity.ok(fileDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Integer> uploadFile(FileDTO fileDTO) {
        try {
            Integer id = fileStorageService.storeFile(fileDTO.getFileName(), fileDTO.getData().getContentAsByteArray());
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
