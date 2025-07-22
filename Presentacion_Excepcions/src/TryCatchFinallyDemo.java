import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryCatchFinallyDemo {

    public static void readFileAndProcess(String filePath) {
        BufferedReader reader = null; // Se declara fuera del try para que 'finally' pueda acceder a él
        System.out.println("Intentando leer el archivo: <" + filePath + "> . . .");
        try {
            reader = new BufferedReader(new FileReader(filePath)); // Puede lanzar FileNotFoundException (subclase de IOException)
            String line;
            System.out.println("Contenido del archivo:\n{");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("}\nArchivo leído exitosamente.");
        } catch (IOException e) { // Captura la IOException si el archivo no se puede abrir o leer
            System.out.println("¡! ¡! Error capturado: No se pudo acceder al archivo. Mensaje: " + e.getMessage());//*
        } finally {
            // Este bloque se ejecuta SIEMPRE, garantizando que el recurso se cierre.
            System.out.println("Ejecutando bloque 'finally' para cerrar el recurso.");
            if (reader != null) { // Comprueba si el lector fue inicializado antes de intentar cerrarlo
                try {
                    reader.close(); // El cierre también puede lanzar una IOException
                    System.out.println("Recurso de archivo cerrado con éxito en 'finally'.");
                } catch (IOException e) {
                    System.out.println("¡! ¡! Error al cerrar el recurso en 'finally': " + e.getMessage());//*
                }
            }
            System.out.println("Fin del proceso de archivo.");
        }
    }

    public static void main(String[] args) {

        System.out.println("*** Ejemplo 1: Archivo existente ***");
        readFileAndProcess("src/ej.txt");

        System.out.println("\n*** Ejemplo 2: Archivo inexistente ***");
        readFileAndProcess("no_existe.txt");
    }
}
