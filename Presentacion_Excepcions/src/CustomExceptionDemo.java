import CustomExceptions.DatosInvalidosException;
import CustomExceptions.SaldoInsuficienteException;

public class CustomExceptionDemo {
    private double accountBalance = 500.0; // Saldo inicial de la cuenta

    // Méto-do que puede lanzar una SaldoInsuficienteException (Checked Exception)
    public void withdraw(double amount) throws SaldoInsuficienteException {
        if (amount <= 0) {
            // Se lanza una excepción no verificada si el monto es inválido
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (accountBalance < amount) {
            // Se lanza la excepción personalizada verificada
            throw new SaldoInsuficienteException("No hay saldo suficiente para el retiro.", accountBalance, amount);
        }
        accountBalance -= amount;
        System.out.printf("Retiro exitoso de %.2f. Nuevo saldo: %.2f%n", amount, accountBalance);
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

        System.out.println("--- Demostración de SaldoInsuficienteException (Checked) ---");
        try {
            bankAccount.withdraw(200); // Retiro exitoso
            bankAccount.withdraw(400); // Esto lanzará SaldoInsuficienteException
        } catch (SaldoInsuficienteException e) {
            System.out.println("¡! ¡! Error de retiro capturado: " + e.getMessage());//*
            System.out.printf("¡! ¡! Detalles: Saldo actual %.2f. Intento de retiro %.2f.%n", e.getSaldoActual(), e.getMontoRetiro());//*
        } catch (IllegalArgumentException e) {
            System.out.println("¡! ¡! Error de entrada de retiro: " + e.getMessage());
        }

        System.out.println("\n--- Demostración de DatosInvalidosException (Unchecked) ---");
        try {
            bankAccount.processUserData("usuario123"); // Datos válidos
            bankAccount.processUserData(null); // Esto lanzará DatosInvalidosException
        } catch (DatosInvalidosException e) {
            System.out.println("¡! ¡! Error de datos de usuario capturado: " + e.getMessage());//*
        } finally {
            System.out.println("Fin de la demostración de excepciones personalizadas.");
        }
    }
}