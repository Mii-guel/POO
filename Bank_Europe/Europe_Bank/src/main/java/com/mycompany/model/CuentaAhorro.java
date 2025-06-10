/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;
/**
 *
 * @author Lenovo
 */

public class CuentaAhorro extends CuentaBancaria {

    public CuentaAhorro() {
        super(); 
    }

    public CuentaAhorro(int saldoInicial) {
        super(saldoInicial); 
    }

    @Override
    public boolean Deposito(int MontoDepositado) {
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
    public boolean Giro(int MontoGiro) {
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
    
}
