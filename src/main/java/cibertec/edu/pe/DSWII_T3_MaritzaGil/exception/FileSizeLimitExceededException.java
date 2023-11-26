package cibertec.edu.pe.DSWII_T3_MaritzaGil.exception;

public class FileSizeLimitExceededException extends RuntimeException {

    public FileSizeLimitExceededException() {
        super("El tamaño del archivo excede el límite permitido.");
    }
}
