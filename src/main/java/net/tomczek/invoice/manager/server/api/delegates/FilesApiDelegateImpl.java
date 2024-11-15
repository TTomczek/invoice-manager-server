package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.FilesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.FileDTO;
import net.tomczek.invoice.manager.server.repositories.LocalFileStorageFileRepository;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilesApiDelegateImpl implements FilesApiDelegate {

    @Autowired
    public FilesApiDelegateImpl(IFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private IFileStorageService fileStorageService;

    @Override
    public ResponseEntity<Resource> downloadFileById(Integer id) {
        try {
            // Return FileWithContent from localFileStorageFileRepository


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<FileDTO> uploadFile(Integer id, String fileType, String fileName, MultipartFile data) {
        return FilesApiDelegate.super.uploadFile(id, fileType, fileName, data);
    }
}
