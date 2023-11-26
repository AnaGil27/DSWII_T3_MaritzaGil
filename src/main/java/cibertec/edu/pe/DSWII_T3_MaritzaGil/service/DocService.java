package cibertec.edu.pe.DSWII_T3_MaritzaGil.service;

import cibertec.edu.pe.DSWII_T3_MaritzaGil.exception.FileExtensionException;
import cibertec.edu.pe.DSWII_T3_MaritzaGil.exception.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class DocService {
    private static final String UPLOAD_DIR = "Documentos";
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2 MB

    public void saveDocument(MultipartFile file) throws IOException {
        // Verificar el tamaño del archivo
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileSizeLimitExceededException();
        }

        // Verificar la extensión del archivo
        if (!file.getOriginalFilename().toLowerCase().endsWith(".doc")) {
            throw new FileExtensionException();
        }

        // Directorio para almacenar archivos DOC
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Guardar el archivo en el directorio
        String filePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);
    }
}
