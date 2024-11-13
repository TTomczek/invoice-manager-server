package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.FilesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.FileDTO;
import net.tomczek.invoice.manager.server.repositories.LocalFileStorageFileRepository;
import net.tomczek.invoice.manager.server.services.filestorage.IFileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FilesApiDelegateImpl implements FilesApiDelegate {

    private LocalFileStorageFileRepository localFileStorageFileRepository;
    private IFileStorageService fileStorageService;

    @Override
    public Mono<ResponseEntity<Resource>> downloadFileById(Integer id, ServerWebExchange exchange) {
        return FilesApiDelegate.super.downloadFileById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<FileDTO>> uploadFile(Integer id, String fileType, String fileName, Flux<Part> data, ServerWebExchange exchange) {

        return FilesApiDelegate.super.uploadFile(id, fileType, fileName, data, exchange);
    }


}
