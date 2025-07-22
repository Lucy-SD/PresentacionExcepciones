import java.io.FileReader;
import java.io.IOException;

public class ThrowThrowsDemo {
    // Este méto-do declara que puede lanzar IOException (checked)
    // y IllegalArgumentException (unchecked)
    public void openFile(String name) throws IOException {
        if (name == null || name.trim().isEmpty()) {
            // 'throw' para lanzar manualmente una excepción no verificada
            throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío.");
        }
        System.out.println("Intentando abrir el archivo: " + name);
        // La siguiente línea puede lanzar IOException, por eso se declara con 'throws'
        FileReader reader = new FileReader(name);
        // Aquí iría la lógica para leer el archivo
        reader.close(); // También podría lanzar IOException
        System.out.println("Archivo abierto y cerrado con éxito.");
    }

    public static void main(String[] args) {
        ThrowThrowsDemo demo = new ThrowThrowsDemo();

        try {
            System.out.println("--- Demostración de 'throws' (IOException) ---");
            // Intenta abrir un archivo que no existe, lo que causará una IOException que será capturada.
            demo.openFile("archivo_que_no_existe.txt");
        } catch (IOException e) {
            System.out.println("¡! ¡! Capturada IOException: " + e.getMessage());//*
        } catch (IllegalArgumentException e) {
            System.out.println("¡! ¡! Capturada IllegalArgumentException: " + e.getMessage());//*
        }

        System.out.println("\n--- Demostración de 'throw' (IllegalArgumentException) ---");
        try {
            // Esta llamada pasa una cadena vacía, activará el 'throw' de IllegalArgumentException dentro del méto-do openFile.
            demo.openFile("");
        } catch (IOException ex) { // Aunque es una IOException, no se espera aquí
            System.out.println("Capturada IOException: " + ex.getMessage());//*
        } catch (IllegalArgumentException ex) {
            System.out.println("¡! ¡! Capturada IllegalArgumentException: " + ex.getMessage());//*
        }

    }
}
