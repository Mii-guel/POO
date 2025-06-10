/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;
import com.mycompany.model.Cliente;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BuscarCliente {
    
    public static Cliente BuscarCliente(Scanner sc, ArrayList<Cliente> ListaClientes){
        if (ListaClientes.isEmpty()){
        System.out.println("No hay clientes registrdos en el sistema");
        return null;
        }
        
        System.out.println("--- Desea realizar la operacion con su RUT o con su numero de cuenta ---");
        System.out.println("1. RUT");
        System.out.println("2. Numero de cuenta corriente");
        
        
        
        int OpcionBusqueda = ValidaEntrada.EnteroEnRango(sc, "Seleccione las opciones 1 o 2 para continuar", 1, 2);
        
        
        
        if (OpcionBusqueda == 1 ){
            String RutCliente = ValidaEntrada.ValidaRut(sc, "Ingrese su RUT en el formato: 12.345.678-9");
            for (Cliente elemento : ListaClientes){
                if (elemento.getRut().equalsIgnoreCase(RutCliente)){
                    return elemento;
                }
            }
        }else{
            int NumeroCuentaCliente = ValidaEntrada.ValidaCuenta(sc, "Ingrese su nmero de cuenta corriente");
            for(Cliente elemento : ListaClientes){
                if(elemento.getCuentaBancaria().getNumeroCuenta() == NumeroCuentaCliente){
                    return elemento;
                }
                
            }
            
        }
        
        System.out.println("No se encontro ningun cliente con la cuenta indicada");
            return null;
    }
    
}
