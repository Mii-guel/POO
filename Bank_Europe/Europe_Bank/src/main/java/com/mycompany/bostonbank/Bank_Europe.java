package com.mycompany.bostonbank;

import java.util.Scanner;
import java.util.ArrayList;
import com.mycompany.model.Cliente;
import com.mycompany.model.CuentaAhorro;
import com.mycompany.model.CuentaBancaria;
import com.mycompany.model.CuentaCorriente;
import com.mycompany.model.CuentaCredito;
import com.mycompany.model.CuentaDigital;
import com.mycompany.utils.ValidaEntrada;
import com.mycompany.utils.BuscarCliente;

public class Bank_Europe {

    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Cliente> ListaNuevoCliente = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("---------------Bienvenido al Banco Boston---------------\n");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir\n");

            int opcion = ValidaEntrada.EnteroEnRango(sc, "Para comenzar por favor elija una de las opciones del 1 al 6", 1, 6);

            switch (opcion) {
                case 1: // Registrar cliente
                    System.out.println("--- Registro de Cliente ---");

                    String Rut = ValidaEntrada.ValidaRut(sc, "Ingrese RUT (formato 12.345.678-9):");
                    String Nombre = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su primer nombre:");
                    String ApellidoPaterno = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su primer apellido:");
                    String ApellidoMaterno = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su segundo apellido:");
                    String Comuna = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su comuna:");
                    String Domicilio = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su domicilio:");
                    String Telefono = ValidaEntrada.ErrorEspacioVacio(sc, "Ingrese su número de teléfono:");

                    System.out.println("\n--- Selección de Tipo de Cuenta ---");
                    System.out.println("1. Cuenta Corriente");
                    System.out.println("2. Cuenta de Ahorro");
                    System.out.println("3. Cuenta de Crédito");
                    System.out.println("4. Cuenta Digital");

                    int tipoCuentaOpcion = ValidaEntrada.EnteroEnRango(sc, "Ingrese el tipo de cuenta (1-4):", 1, 4);
                    
                    // Limpiar buffer pendiente si es necesario
                    sc.nextLine();

                    CuentaBancaria nuevaCuenta = null;

                    switch (tipoCuentaOpcion) {
                        case 1:
                            nuevaCuenta = new CuentaCorriente();
                            break;
                        case 2:
                            nuevaCuenta = new CuentaAhorro();
                            break;
                        case 3:
                            nuevaCuenta = new CuentaCredito();
                            break;
                        case 4:
                            System.out.print("Ingrese su email para la cuenta digital: ");
                            String email = sc.nextLine().trim();                           
                            int numeroCuentaDigital = 100_000_000 + (int)(Math.random() * 9_000_000);
                            CuentaDigital nuevaCuentaDigital = new CuentaDigital(numeroCuentaDigital, 0, email);
                            nuevaCuentaDigital.enviarNotificacion("Cuenta Digital creada exitosamente.");
                            break;
                    }

                    if (nuevaCuenta != null) {
                        Cliente nuevoCliente = new Cliente(Rut, Nombre, ApellidoPaterno, ApellidoMaterno, Comuna, Domicilio, Telefono, nuevaCuenta);
                        ListaNuevoCliente.add(nuevoCliente);
                        System.out.println("Cliente registrado. Cuenta número: " + nuevaCuenta.getNumeroCuenta());
                    } else {
                        System.out.println("Error: No se pudo crear la cuenta.");
                    }
                    break;

                case 2: // Ver datos cliente
                    Cliente cliente = BuscarCliente.BuscarCliente(sc, ListaNuevoCliente);
                    if (cliente != null) {
                        System.out.println(cliente.MostrarDetalles());
                    }
                    break;

                case 3: // Depositar
                    Cliente clienteDeposito = BuscarCliente.BuscarCliente(sc, ListaNuevoCliente);
                    if (clienteDeposito != null) {
                        int monto = ValidaEntrada.ValidaEntero(sc, "Ingrese el monto a depositar:");
                        if (clienteDeposito.getCuentaBancaria() != null) {
                            clienteDeposito.getCuentaBancaria().Deposito(monto);
                        }
                    }
                    break;

                case 4: // Girar
                    Cliente clienteGiro = BuscarCliente.BuscarCliente(sc, ListaNuevoCliente);
                    if (clienteGiro != null) {
                        int monto = ValidaEntrada.ValidaEntero(sc, "Ingrese el monto a girar:");
                        if (clienteGiro.getCuentaBancaria() != null) {
                            clienteGiro.getCuentaBancaria().Giro(monto);
                        }
                    }
                    break;

                case 5: // Consultar saldo
                    Cliente clienteSaldo = BuscarCliente.BuscarCliente(sc, ListaNuevoCliente);
                    if (clienteSaldo != null && clienteSaldo.getCuentaBancaria() != null) {
                        System.out.println("Saldo actual: " + clienteSaldo.getCuentaBancaria().ConsultarSaldo());
                    }
                    break;

                case 6: // Salir
                    System.out.println("Gracias por usar Boston Bank.");
                    System.exit(0);
                    break;
            }
        }
    }
}


