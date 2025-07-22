import java.io.FileReader;
import java.io.IOException;

public class ExceptionTypesDemo {
    public static void main(String[] args) {

        System.out.println("*** Ejemplo de Excepción Verificada (Checked): IOException ***");
        // El compilador exige que se capture la excepción de FileReader o que se declare con 'throws'.

        System.out.println("Intentando abrir un archivo que no existe . . .");
        try (FileReader reader = new FileReader("no_existe.txt")) {
            System.out.println("Si el archivo existiera, se podría leer aquí");
        } catch (IOException e) {
            System.out.println("¡! ¡! Exception Verificada Capturada: Archivo no encontrado. Mensaje: " + e.getMessage());//*
            //aquí es correcto utilizar .err, pero como tienen buffers diferentes, la información aparece "mezclada" en consola.
        }


        FileReader reader = null;

        System.out.println("Intentando abrir un archivo que no existe . . .");
        try {
            reader = new FileReader("no_existe.txt"); // Esto lanzará FileNotFoundException (subclase de IOException)
            //Si el archivo existiera, se podría leer aquí
        } catch (IOException e) {
            System.out.println("¡! ¡! Exception Verificada Capturada: Archivo no encontrado. Mensaje: " + e.getMessage());//*
            //aquí es correcto utilizar .err, pero como tienen buffers diferentes, la información aparece "mezclada" en consola.
        } finally {
            // El bloque 'finally' se ejecuta siempre, garantizando el intento de cierre del recurso.
            if(reader != null) {
                try {
                    reader.close(); //Esto puede lanzar una IOException
                    System.out.println("Recurso de archivo cerrado con éxito en 'finally'.");
                } catch (IOException e) {
                    // Manejamos la excepción que puede ocurrir al intentar cerrar el recurso.
                    // Como no hay niveles superiores, la imprimimos.
                    System.out.println("¡! ¡! Error al cerrar el recurso en 'finally': " + e.getMessage());//*
                }
            }
        }


        System.out.println("\n*** Ejemplo de Excepción No Verificada (Unchecked): NullPointerException ***");
        String s = null;
        System.out.println("Intentando obtener la longitud de una cadena nula...");
        // Esto arrojará una NullPointerException en tiempo de ejecución.
        // El compilador NO exige un bloque try-catch para este tipo de excepción.
        // Generalmente, estas excepciones indican un error de lógica en el código que debe ser corregido.
        // AL NO ESTAR MANEJADA, ESTA EXCEPCIÓN DETENDRÁ LA EJECUCIÓN DEL PROGRAMA
        // y la JVM imprimirá el 'stack trace' completo en la consola de errores (System.err).
        System.out.println(s.length()); // ¡! Esto causará un NullPointerException si 's' es null.

        //Este mensaje NO se mostrará si la NullPointerException no es capturada.
        System.out.println("Fin de la demostración de tipos de excepción.");
    }
}