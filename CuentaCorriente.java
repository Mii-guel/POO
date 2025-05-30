package exp1_s1_miguel_castillo;

public class CuentaCorriente {
    private int numero;  // 9 dígitos
    private int saldo;   // saldo en pesos

    public CuentaCorriente(int numero) {
        this.numero = numero;
        this.saldo = 0; // saldo inicial 0
    }

    public int getNumero() {
        return numero;
    }

    public int getSaldo() {
        return saldo;
    }
    
    public void depositar(int monto) {
        if (monto <= 0) {
            System.out.println("¡Monto inválido! El depósito debe ser mayor a cero.");
            return;
        }
        saldo += monto;
        System.out.println("¡Depósito realizado de manera exitosa!");
        System.out.println("Usted tiene un saldo actual de " + saldo + " pesos.");
    }

    public void girar(int monto) {
        if (monto <= 0) {
            System.out.println("¡Monto inválido! El giro debe ser mayor a cero.");
            return;
        }
        if (saldo <= 0) {
            System.out.println("No puede realizar giros porque su saldo es cero.");
            return;
        }
        if (monto > saldo) {
            System.out.println("No puede girar un monto mayor al saldo actual.");
            return;
        }
        saldo -= monto;
        System.out.println("¡Giro realizado de manera exitosa!");
        System.out.println("Usted tiene un saldo actual de " + saldo + " pesos.");
    }

    public void consultarSaldo() {
        System.out.println("Saldo actual: " + saldo + " pesos.");
    }
}


