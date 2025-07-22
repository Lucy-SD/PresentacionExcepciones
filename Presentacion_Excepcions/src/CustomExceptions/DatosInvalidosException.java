package CustomExceptions;

// 2. Excepción no verificada personalizada (Unchecked Exception)
// Se extiende de RuntimeException, por lo que el compilador no exigirá su manejo.
public class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException(String message) {
        super(message);
    }
}