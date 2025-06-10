/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.interfaces.interfaces.Informacion;

public class Cliente implements Informacion {
    
    private String Rut;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Comuna;
    private String Domicilio;
    private String Telefono;

    private CuentaBancaria CuentaBancaria;
    

    // Constructor para cuentas tradicionales
    public Cliente(String Rut, String Nombre, String ApellidoPaterno, String ApellidoMaterno,
                   String Comuna, String Domicilio, String Telefono, CuentaBancaria CuentaBancaria) {
        this.Rut = Rut;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Comuna = Comuna;
        this.Domicilio = Domicilio;
        this.Telefono = Telefono;
        this.CuentaBancaria = CuentaBancaria;
        
    }

    // Constructor para cuenta digital
    public Cliente(String Rut, String Nombre, String ApellidoPaterno, String ApellidoMaterno,
                   String Comuna, String Domicilio, String Telefono, CuentaDigital CuentaDigital) {
        this.Rut = Rut;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Comuna = Comuna;
        this.Domicilio = Domicilio;
        this.Telefono = Telefono;
        
        this.CuentaBancaria = null;
    }

    // Getters y Setters
    public String getRut() { return Rut; }
    public void setRut(String Rut) { this.Rut = Rut; }

    public String getNombre() { return Nombre; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }

    public String getApellidoPaterno() { return ApellidoPaterno; }
    public void setApellidoPaterno(String ApellidoPaterno) { this.ApellidoPaterno = ApellidoPaterno; }

    public String getApellidoMaterno() { return ApellidoMaterno; }
    public void setApellidoMaterno(String ApellidoMaterno) { this.ApellidoMaterno = ApellidoMaterno; }

    public String getComuna() { return Comuna; }
    public void setComuna(String Comuna) { this.Comuna = Comuna; }

    public String getDomicilio() { return Domicilio; }
    public void setDomicilio(String Domicilio) { this.Domicilio = Domicilio; }

    public String getTelefono() { return Telefono; }
    public void setTelefono(String Telefono) { this.Telefono = Telefono; }

    public CuentaBancaria getCuentaBancaria() { return CuentaBancaria; }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) { this.CuentaBancaria = cuentaBancaria; }

    

    // Mostrar información resumida
    public String MostrarInformacionCliente() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rut: ").append(this.Rut).append("\n");
        sb.append("Nombre: ").append(this.Nombre).append("\n");
        sb.append("Apellido paterno: ").append(this.ApellidoPaterno).append("\n");
        sb.append("Apellido materno: ").append(this.ApellidoMaterno).append("\n");
        sb.append("Domicilio: ").append(this.Domicilio).append("\n");
        sb.append("Comuna: ").append(this.Comuna).append("\n");
        sb.append("Telefono: ").append(this.Telefono).append("\n");

        if (this.CuentaBancaria != null) {
            sb.append("Numero de cuenta: ").append(this.CuentaBancaria.getNumeroCuenta()).append("\n");
            sb.append("Saldo: ").append(this.CuentaBancaria.ConsultarSaldo()).append("$\n");
        } else {
            sb.append("Cuenta: No asignada\n");
            sb.append("Saldo: No disponible\n");
        }

        return sb.toString();
    }

    @Override
    public String MostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rut: ").append(this.Rut).append("\n");
        sb.append("Nombre: ").append(this.Nombre).append("\n");
        sb.append("Apellido paterno: ").append(this.ApellidoPaterno).append("\n");
        sb.append("Apellido materno: ").append(this.ApellidoMaterno).append("\n");
        sb.append("Domicilio: ").append(this.Domicilio).append("\n");
        sb.append("Comuna: ").append(this.Comuna).append("\n");
        sb.append("Teléfono: ").append(this.Telefono).append("\n");

        if (this.CuentaBancaria != null) {
            sb.append("Número de cuenta: ").append(this.CuentaBancaria.getNumeroCuenta()).append("\n");
            if (this.CuentaBancaria instanceof CuentaAhorro) {
                sb.append("Tipo de Cuenta: Ahorro\n");
            } else if (this.CuentaBancaria instanceof CuentaCredito) {
                sb.append("Tipo de Cuenta: Crédito\n");
            } else {
                sb.append("Tipo de Cuenta: Corriente\n");
            }
        } else {
            sb.append("Número de cuenta: No asignada\n");
            sb.append("Saldo Registrado en Cuenta: No disponible\n");
        }

        return sb.toString();
    }
}

