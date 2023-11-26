package cibertec.edu.pe.DSWII_T3_MaritzaGil.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {
    private static final String UPLOAD_DIR = "Documentos";

    public void savePDF(MultipartFile file) throws IOException {
        if (!file.getContentType().equals("application/pdf")) {
            throw new IOException("El archivo no es un PDF.");
        }

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);
    }
}
