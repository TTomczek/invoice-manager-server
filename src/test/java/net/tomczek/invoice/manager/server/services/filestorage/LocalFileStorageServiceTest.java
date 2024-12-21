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

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LocalFileStorageServiceTest {

    LocalFileStorageService cut;
    LocalFileStorageFileRepository repoMock = Mockito.mock(LocalFileStorageFileRepository.class);

    @BeforeEach
    void setUp() {
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
        File expectedFile = new File("target/test/files/1.txt");
        assertTrue(expectedFile.exists());
        assertEquals(expectedFile.getName(), "1.txt");
    }

    @Test
    void getFile() throws Exception {
        when(repoMock.findById(1)).thenReturn(java.util.Optional.of(new LocalFileStorageFile(1, "storedFile.txt")));
        File file = new File("target/test/files/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Files.write(file.toPath(), "testcontent".getBytes());

        FileWithContent result = cut.getFile(1);
        assertEquals(result.getId(), 1);
        assertEquals(result.getFileName(), "storedFile.txt");
        assertEquals(new String(result.getFileContent()), "testcontent");
    }

    @Test
    void deleteFile() throws Exception {
        when(repoMock.save(any(LocalFileStorageFile.class))).thenReturn(new LocalFileStorageFile(1, "storedFile.txt"));
        when(repoMock.findById(1)).thenReturn(java.util.Optional.of(new LocalFileStorageFile(1, "storedFile.txt")));
        cut.storeFile("testFile.txt", Files.readAllBytes(new File("src/test/resources/file.txt").toPath()));

        File expectedFile = new File("target/test/files/1.txt");
        cut.deleteFile(1);
        assertFalse(expectedFile.exists());
        verify(repoMock).delete(any(LocalFileStorageFile.class));
    }

    @Test
    void fileExists() throws Exception {
        when(repoMock.save(any(LocalFileStorageFile.class))).thenReturn(new LocalFileStorageFile(1, "storedFile.txt"));
        when(repoMock.findById(1)).thenReturn(java.util.Optional.of(new LocalFileStorageFile(1, "storedFile.txt")));
        cut.storeFile("testFile.txt", Files.readAllBytes(new File("src/test/resources/file.txt").toPath()));
        boolean fileExists = cut.fileExists(1);
        assertTrue(fileExists);
    }
}
