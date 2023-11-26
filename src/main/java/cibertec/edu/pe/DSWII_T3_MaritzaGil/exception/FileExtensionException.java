package cibertec.edu.pe.DSWII_T3_MaritzaGil.exception;

public class FileExtensionException extends RuntimeException {

    public FileExtensionException() {
        super("La extensión del archivo no es la permitida.");
    }
}
