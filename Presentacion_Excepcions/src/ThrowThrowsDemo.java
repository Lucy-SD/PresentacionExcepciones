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
        System.out.println("\nIntentando abrir el archivo: " + name);
        // La siguiente línea puede lanzar IOException, por eso se declara con 'throws'
        FileReader reader = new FileReader(name);
        // Aquí iría la lógica para leer el archivo
        reader.close(); // También podría lanzar IOException
        System.out.println("Archivo abierto y cerrado con éxito.");
    }

    public static void main(String[] args) {
        ThrowThrowsDemo demo = new ThrowThrowsDemo();

        try {
            System.out.println("*** Demostración de 'throws' (IOException) ***");
            demo.openFile("src/ej.txt");
            demo.openFile("no_existe.txt");
            //Intentar abrir un archivo que no existe causará una IOException que será capturada.
        } catch (IOException e) {
            System.out.println("¡! ¡! IOException Capturada: " + e.getMessage());//*
        } catch (IllegalArgumentException e) {
            System.out.println("¡! ¡! IllegalArgumentException Capturada: " + e.getMessage());//*
        }


        System.out.println("\n*** Demostración de 'throw' (IllegalArgumentException) ***");
        try {
            demo.openFile("");
            // Esta llamada pasa una cadena vacía que activará el 'throw' de IllegalArgumentException dentro del méto-do openFile.
        } catch (IOException ex) { // Aunque es una IOException, no se espera aquí
            System.out.println("¡! ¡! IOException Capturada: " + ex.getMessage());//*
        } catch (IllegalArgumentException ex) {
            System.out.println("¡! ¡! IllegalArgumentException Capturada: " + ex.getMessage());//*
        }

    }
}
