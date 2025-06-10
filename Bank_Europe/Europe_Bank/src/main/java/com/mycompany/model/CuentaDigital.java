package com.mycompany.model;

/**
 * CuentaDigital extiende CuentaBancaria
 */
public class CuentaDigital extends CuentaBancaria {

    private static final int MONTO_MAXIMO_GIRO = 1000000; 
    private String email;

    // Constructor vacío
    public CuentaDigital() {
        super(); // usa constructor sin saldo inicial
    }

    // Constructor con saldo inicial
    public CuentaDigital(int saldoInicial) {
        super(saldoInicial);
    }

    // Constructor completo con número de cuenta, saldo inicial y email
    public CuentaDigital(int numeroCuenta, int saldoInicial, String email) {
        super(numeroCuenta, saldoInicial); // llama al constructor padre con número y saldo
        this.email = email;
    }

    @Override
    public boolean Deposito(int monto) {
        if (monto > 0) {
            this.saldo += monto;
            enviarNotificacion("Se ha depositado " + monto + " en su cuenta digital.");
            return true;
        }
        return false;
    }

    @Override
    public boolean Giro(int monto) {
        if (monto > 0 && monto <= this.saldo && monto <= MONTO_MAXIMO_GIRO) {
            this.saldo -= monto;
            enviarNotificacion("Se ha girado " + monto + " de su cuenta digital.");
            return true;
        }
        return false;
    }

    public void enviarNotificacion(String mensaje) {
        // Aquí puedes implementar el envío real, pero por ahora solo imprimimos
        System.out.println("Notificación enviada a " + email + ": " + mensaje);
    }

    // Getter y setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


