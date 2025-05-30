package exp1_s1_miguel_castillo;

import java.util.Scanner;

public class Exp1_S1_Miguel_Castillo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        int opcion;

        do {
            System.out.println("\n--- Menú Bank Boston ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese Rut (8 a 10 caracteres): ");
                    String rut = sc.nextLine();
                    if (rut.length() < 8 || rut.length() > 10) {
                        System.out.println("Rut inválido. Debe tener entre 11 y 12 caracteres.");
                        break;
                    }
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese apellido paterno: ");
                    String apellidoPaterno = sc.nextLine();
                    System.out.print("Ingrese apellido materno: ");
                    String apellidoMaterno = sc.nextLine();
                    System.out.print("Ingrese domicilio: ");
                    String domicilio = sc.nextLine();
                    System.out.print("Ingrese comuna: ");
                    String comuna = sc.nextLine();
                    System.out.print("Ingrese teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Ingrese número de cuenta corriente (9 dígitos): ");
                    int numeroCuenta = sc.nextInt();
                    sc.nextLine();

                    // Crear cuenta con saldo inicial 0
                    CuentaCorriente cuenta = new CuentaCorriente(numeroCuenta);

                    // Crear cliente
                    cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna, telefono, cuenta);
                    System.out.println("¡Cliente registrado exitosamente!");
                    break;

                case 2:
                    if (cliente == null) {
                        System.out.println("No hay cliente registrado.");
                    } else {
                        cliente.mostrarDatos();
                    }
                    break;

                case 3:
                    if (cliente == null) {
                        System.out.println("Primero debe registrar un cliente.");
                    } else {
                        System.out.print("Ingrese un monto para depositar: ");
                        int montoDeposito = sc.nextInt();
                        sc.nextLine();
                        cliente.getCuenta().depositar(montoDeposito);
                    }
                    break;

                case 4:
                    if (cliente == null) {
                        System.out.println("Primero debe registrar un cliente.");
                    } else {
                        System.out.print("Ingrese un monto para girar: ");
                        int montoGiro = sc.nextInt();
                        sc.nextLine();
                        cliente.getCuenta().girar(montoGiro);
                    }
                    break;

                case 5:
                    if (cliente == null) {
                        System.out.println("Primero debe registrar un cliente.");
                    } else {
                        cliente.getCuenta().consultarSaldo();
                    }
                    break;

                case 6:
                    System.out.println("Gracias por usar Bank Boston.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6);

        sc.close();
    }
}


