import java.io.FileReader;
import java.io.IOException;

public class ExceptionTypesDemo {
    public static void main(String[] args) {
        // --- Ejemplo de Excepción Verificada (Checked Exception): IOException ---
        // El compilador exige que se maneje FileReader o se declare con 'throws'.
        try {
            System.out.println("Intentando abrir un archivo que no existe...");
            FileReader reader = new FileReader("no_existe.txt"); // Esto lanzará FileNotFoundException (subclase de IOException)
            // Si el archivo existiera, se podría leer aquí
            reader.close(); // También podría lanzar IOException
        } catch (IOException e) {
            System.out.println("¡! ¡! Capturada Checked Exception: Archivo no encontrado. Mensaje: " + e.getMessage());
            //aquí es correcto utilizar .err, pero como tienen buffers diferentes, la información aparece "mezclada" en consola.
        }

        System.out.println("\n--- Ejemplo de Excepción No Verificada (Unchecked Exception): NullPointerException ---");
        String s = null;
        System.out.println("Intentando acceder a la longitud de una cadena nula...");
        // Esto arrojará una NullPointerException en tiempo de ejecución.
        // El compilador no exige un bloque try-catch para este tipo de excepción.
        // Generalmente, estas excepciones indican un error de lógica en el código que debe ser corregido.
        System.out.println(s.length()); // ¡Cuidado! Esto causará un NullPointerException si 's' es null.

        System.out.println("Fin de la demostración de tipos de excepción.");
    }
}