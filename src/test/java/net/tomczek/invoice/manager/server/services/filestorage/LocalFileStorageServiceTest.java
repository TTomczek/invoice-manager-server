package net.tomczek.invoice.manager.server.services.filestorage;

import net.tomczek.invoice.manager.server.entities.LocalFileStorageFile;
import net.tomczek.invoice.manager.server.models.FileWithContent;
import net.tomczek.invoice.manager.server.repositories.LocalFileStorageFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LocalFileStorageServiceTest {

    LocalFileStorageService cut;
    LocalFileStorageFileRepository repoMock = Mockito.mock(LocalFileStorageFileRepository.class);

    @BeforeEach
    void setUp() throws IOException {
        LocalFileStorageProperties properties = new LocalFileStorageProperties();
        properties.setStoragePath("target/test/files");
        cut = new LocalFileStorageService(properties, repoMock);

        File dir = new File("target/test/files");
        if (dir.exists() ) {
            if (dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    @Test
    void storeFile() throws Exception {
        when(repoMock.save(any(LocalFileStorageFile.class))).thenReturn(new LocalFileStorageFile(1, "storedFile.txt"));
        int id = cut.storeFile("testFile.txt", Files.readAllBytes(new File("src/test/resources/file.txt").toPath()));

        assertEquals(id, 1);
        File expectedFile = new File("target/test/files/1");
        assertTrue(expectedFile.exists());
        assertEquals(expectedFile.getName(), "1");
    }

    @Test
    void getFile() throws Exception {
        when(repoMock.findById(1)).thenReturn(java.util.Optional.of(new LocalFileStorageFile(1, "storedFile.txt")));
        File file = new File("target/test/files/1");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Files.write(file.toPath(), "testcontent".getBytes());

        FileWithContent result = cut.getFile(1);
        assertEquals(result.getId(), 1);
        assertEquals(result.getFileName(), "storedFile.txt");
        assertEquals(new String(result.getFileContent()), "testcontent");
    }

    @Test
    void deleteFile() {
    }

    @Test
    void fileExists() {

    }
}
