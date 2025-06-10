/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Lenovo
 */

public class CuentaCredito extends CuentaBancaria {
    //En cuenta credito el saldo es el monto de la deuda

    private int limiteCredito; //atributo especifico de CuentaCredito que representa el monto maximo de la linea de credito

    public CuentaCredito() {
        super(); 
        this.limiteCredito = 100000;
    }


    @Override
    public boolean Deposito(int MontoDepositado) {//Abono a la deuda
        if (MontoDepositado <= 0) {
            System.out.println("Error: No se permite el ingreso de montos menores o iguales a cero.");
            return false;
        }
        
        this.saldo += MontoDepositado; 
        System.out.println("Deposito realizado exitosamente"); 
        System.out.println("Su saldo actual es de: " + this.saldo + "$"); 
        return true;
    }

    @Override
    public boolean Giro(int MontoGiro) {//Acredita a la deuda
        if (MontoGiro <= 0) {
            System.out.println("Error: No se permite el ingreso de montos menores o iguales a cero para girar."); 
            return false;
        }
        if (MontoGiro > this.saldo) { 
            System.out.println("Error: No se permite realizar giros mayores al saldo de su cuenta.");
            System.out.println("Saldo disponible: " + this.saldo + "$"); 
            return false;
        }
        this.saldo -= MontoGiro;
        System.out.println("Giro realizado de manera exitosa");
        System.out.println("Usted tiene un saldo actual de: " + this.saldo + "$");
        return true;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public int ConsultarCreditoDisponible() {//Diferencia entre el saldo (deuda) y la linea de credito
        return this.limiteCredito - this.saldo;
    }
    
}
