package CustomExceptions;

// 1. Excepción verificada personalizada (Checked Exception)
// Se extiende de Exception, por lo que el compilador exigirá su manejo.
public class SaldoInsuficienteException extends Exception {
    private double saldoActual;
    private double montoRetiro;

    public SaldoInsuficienteException(String message, double saldoActual, double montoRetiro) {
        super(message);
        this.saldoActual = saldoActual;
        this.montoRetiro = montoRetiro;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getMontoRetiro() {
        return montoRetiro;
    }
}