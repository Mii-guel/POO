/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
   package com.mycompany.utils;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class ValidaEntrada {
    
    public static String ErrorEspacioVacio (Scanner sc, String mensaje){
        String entrada;
        while (true){
            System.out.println(mensaje);
            entrada = sc.nextLine().trim();
            if (!entrada.isEmpty()){
                return entrada;
            }else{
                System.out.println("Error: No debe responder con un espacio vacio");
            }
        }
    }
    
    public static int ValidaEntero (Scanner sc, String mensaje){
        int numero;
        while (true){
            System.out.println(mensaje);
            try{
                numero = sc.nextInt();
                sc.nextLine();
                return numero;
                
                
            }catch(InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero entero valido. Intentelo de nuevo.");
                sc.nextLine();
            }
        }
    }
    
    public static int EnteroEnRango(Scanner sc, String mensaje, int min, int max){
        int numero;
        while(true){
            numero = ValidaEntero(sc, mensaje + " (entre " + min + " y " + max + "):"  );
            if (numero >= min && numero <= max){
                return numero;
            }else{
                System.out.println("Error: EL numero debe estar entre " + min + " y " + max + "Intentalo de nuevo.");
            }
        }
    }
    
    public static String ValidaRut(Scanner sc, String mensaje){
        String rut;
        String RegexRut = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$";
        while (true){
            System.out.println(mensaje);
            rut = sc.nextLine().trim();
            if(rut.matches(RegexRut)){
                return rut;
            }else{
                System.out.println("Error: Rut invalido, intentelo de nuevo por favor en el formato indicado \"12.345.678-9\" o \"12.345.678-k\"");
            }
        }
    }
    
    public static int ValidaCuenta (Scanner sc, String mensaje){
        String numeroStr;
        int CuentaTemp;
        while(true){
            System.out.println(mensaje);
            numeroStr = sc.nextLine().trim();
            if(numeroStr.matches("\\d{9}")){
                try{
                    CuentaTemp = Integer.parseInt(numeroStr);
                    return CuentaTemp;
                }catch(NumberFormatException e){
                    System.out.println("Error: Tipo de dato incorrecto");
                }
            }
        
        }
    }
    
}//final de la clase
