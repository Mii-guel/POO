/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.Random;

/**
 *
 * @author Lenovo
 */

public abstract class CuentaBancaria {

    protected int numeroCuenta;
    protected int saldo;

    public CuentaBancaria() {
        this.numeroCuenta = generarNumeroCuentaAleatorio();
        this.saldo = 0;
    }

    public CuentaBancaria(int saldoInicial) {
        this.numeroCuenta = generarNumeroCuentaAleatorio();
        this.saldo = saldoInicial;
    }

   
    public CuentaBancaria(int numeroCuenta, int saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    
    private int generarNumeroCuentaAleatorio() {
        Random random = new Random();
        return 100_000_000 + random.nextInt(900_00_000);
    }

   
    public abstract boolean Deposito(int monto);

    
    public abstract boolean Giro(int monto);


    public int ConsultarSaldo() {
        return this.saldo;
    }

   
    public int getNumeroCuenta() {
        return this.numeroCuenta;
    }

    

}
