import CustomExceptions.DatosInvalidosException;
import CustomExceptions.SaldoInsuficienteException;

public class CustomExceptionDemo {
    private double accountBalance = 500.0; // Saldo inicial de la cuenta

    // Méto-do que puede lanzar una SaldoInsuficienteException (Checked Exception)
    public void withdraw(double withdrawAmount) throws SaldoInsuficienteException {
        if (withdrawAmount <= 0) {
            // Se lanza una excepción no verificada si el monto es inválido
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (accountBalance < withdrawAmount) {
            // Se lanza la excepción personalizada verificada
            throw new SaldoInsuficienteException("No hay saldo suficiente para el retiro.", accountBalance, withdrawAmount);
        }
        accountBalance -= withdrawAmount;
        System.out.printf("Retiro exitoso de %.2f. Nuevo saldo: %.2f.%n", withdrawAmount, accountBalance);
    }

    // Méto-do que puede lanzar una DatosInvalidosException (Unchecked Exception)
    public void processUserData(String userData) {
        if (userData == null || userData.trim().isEmpty()) {
            // Se lanza la excepción personalizada no verificada
            throw new DatosInvalidosException("Los datos de usuario no pueden ser nulos o vacíos.");
        }
        System.out.println("Datos de usuario procesados: " + userData);
    }

    public static void main(String[] args) {
        CustomExceptionDemo bankAccount = new CustomExceptionDemo();

        System.out.println("\n*** Demostración de SaldoInsuficienteException (Verificada) ***");
        try {
            bankAccount.withdraw(200); // Retiro exitoso
            bankAccount.withdraw(400); // Esto lanzará SaldoInsuficienteException
        } catch (SaldoInsuficienteException e) {
            System.out.println("¡! ¡! Error de retiro capturado: " + e.getMessage());//*
            System.out.printf("Detalles: Saldo actual %.2f. Intento de retiro %.2f.%n", e.getSaldoActual(), e.getMontoRetiro());//*
        } catch (IllegalArgumentException e) {
            System.out.println("¡! ¡! Error de entrada de retiro: " + e.getMessage());
        }

        System.out.println("\n*** Demostración de IllegalArgumentException (No Verificada) ***");
        try {
            bankAccount.withdraw(200); // Retiro exitoso
            bankAccount.withdraw(-200); // Esto lanzará IllegalArgumentException
        } catch (SaldoInsuficienteException e) {
            System.out.println("¡! ¡! Error de retiro capturado: " + e.getMessage());//*
            System.out.printf("¡! ¡! Detalles: Saldo actual %.2f. Intento de retiro %.2f.%n", e.getSaldoActual(), e.getMontoRetiro());//*
        } catch (IllegalArgumentException e) {
            System.out.println("¡! ¡! Error de entrada de retiro: " + e.getMessage());
        }

        System.out.println("\n*** Demostración de DatosInvalidosException (No Verificada) ***");

        bankAccount.processUserData("usuario123"); // Datos válidos
        bankAccount.processUserData(null); // Esto lanzará DatosInvalidosException


        //Si quisiéramos capturar la Excepción:
    /*    try {
            bankAccount.processUserData("usuario123"); // Datos válidos
            bankAccount.processUserData(null); // Esto lanzará DatosInvalidosException
        } catch (DatosInvalidosException e) {
            System.out.println("¡! ¡! Error de datos de usuario capturado: " + e.getMessage());//*
        } finally {
            System.out.println("Este mensaje se mostrará más allá de si se capturó o no una excepción.");
        }*/

        System.out.println("Este mensaje NO se mostrará si la Exception No Verificada no es capturada.");
    }
}