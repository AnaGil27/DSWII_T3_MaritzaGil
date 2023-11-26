package cibertec.edu.pe.DSWII_T3_MaritzaGil.controller;

import cibertec.edu.pe.DSWII_T3_MaritzaGil.exception.FileExtensionException;
import cibertec.edu.pe.DSWII_T3_MaritzaGil.exception.FileSizeLimitExceededException;
import cibertec.edu.pe.DSWII_T3_MaritzaGil.model.response.ResponseFile;
import cibertec.edu.pe.DSWII_T3_MaritzaGil.service.DocService;
import cibertec.edu.pe.DSWII_T3_MaritzaGil.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/file")
public class FileController {
    private FileService fileService;
    private DocService docService;
    @PostMapping("/filespdf")
    @PreAuthorize("hasAuthority('Supervisor')")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file) {
        try {
            fileService.savePDF(file);
            return new ResponseEntity<>("Archivo PDF subido correctamente.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir el archivo PDF.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filesdoc")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<String> handleDocumentUpload(@RequestPart("file") MultipartFile file) {
        try {
            docService.saveDocument(file);
            return new ResponseEntity<>("Archivo DOC subido correctamente.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir el archivo DOC.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FileSizeLimitExceededException e) {
            return new ResponseEntity<>("El tamaño del archivo excede el límite permitido (2MB).", HttpStatus.BAD_REQUEST);
        } catch (FileExtensionException e) {
            return new ResponseEntity<>("La extensión del archivo no es DOC.", HttpStatus.BAD_REQUEST);
        }
    }



}
