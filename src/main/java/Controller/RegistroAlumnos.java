/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ignac
 */
import java.io.*;
import java.util.Scanner;

public class RegistroAlumnos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ARCHIVO_REGISTRO = "registro.txt";

        // esto es para crear el archivo si no existe
        File archivo = new File(ARCHIVO_REGISTRO);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo de registro creado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de registro: " + e.getMessage());
            }
        }

        int opcion;
        do {
            System.out.println(" REGISTRO DE ALUMNOS DEL INSTITUTO");
            System.out.println("1. Agregar un nuevo alumno");
            System.out.println("2. Mostrar lista de alumnos registrados");
            System.out.println("3. Eliminar un alumno del registro");
            System.out.println("4. Buscar un alumno por DNI");
            System.out.println("5. Salir del programa");
            System.out.print("Selecciona una opcion: ");
            
  
                opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Agregar alumno
                    break;

                case 2:
                    // Mostrar alumnos
                    break;

                case 3:
                    // Eliminar alumno
                    break;

                case 4:
                    // Buscar alumno
                    break;

                case 5:
                    System.out.println("Hasta pronto!");
                    break;

                default:
                    System.out.println("Opcion no valida. Por favor, selecciona una opcion del 1 al 5.");
            }
        } while (opcion != 5);

    }
}