/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ignac
 */
import Model.Alumno;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroAlumnos {

    public static ArrayList<Alumno> alumnos = new ArrayList();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String ARCHIVO_REGISTRO = "registro.txt";

        // esto es para crear el archivo si no existe
        File archivo = new File(ARCHIVO_REGISTRO);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo de registro creado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de registro: " + e.getMessage());
                return;
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

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    agregarAlumno(scanner, ARCHIVO_REGISTRO);
                    break;

                case 2:

                    mostrarAlumno(scanner, ARCHIVO_REGISTRO);
                    break;

                case 3:
                   eliminarAlumno(scanner, ARCHIVO_REGISTRO);
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

    public static void agregarAlumno(Scanner scanner, String ARCHIVO_REGISTRO) throws IOException {
        System.out.println("AGREGAR UN ALUMNO");
        System.out.print("DNI: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("DNI no puede estar vacío.");
            return;
        }

        boolean existe = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(";");
                    if (datos.length >= 1 && datos[0].trim().equals(dni)) {
                        existe = true;
                        break;
                    }
                }
            }
        }

        if (existe) {
            System.out.println("Error: Ya existe un alumno registrado con el DNI " + dni);
            return;
        }

        System.out.print("NOMBRE: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("APELLIDOS: ");
        String apellido = scanner.nextLine().trim();

        int edad;
        System.out.print("Edad: ");
        String edad1 = scanner.nextLine().trim();

        try {
            edad = Integer.parseInt(edad1);
            if (edad < 0) {
                System.out.println("Edad inválida.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Edad no es un número válido.");
            return;
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine().trim();
        
        //añadimos el alumno al arraylist
        Alumno alumno = new Alumno(nombre, apellido, edad, curso, dni);
        alumnos.add(alumno);
        
        
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, true))) {
            writer.write("--------------------------NUEVO ALUMNO---------------------------------");

            writer.write("\nDNI: " + dni + "\nNOMBRE: " + nombre + "\nAPELLIDOS: " + apellido + "\nEDAD: " + edad + "\nCURSO: " + curso);

            writer.write("\n-----------------------------------------------------------------------------------");
            writer.newLine();
        }

        System.out.println("Alumno agregado correctamente.");
    }

    public static void mostrarAlumno(Scanner scanner, String ARCHIVO_REGISTRO) {
        System.out.println("MOSTRAR ALUMNOS");

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            boolean vacio = true;

            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    System.out.println(linea);
                    vacio = false;
                }
            }

            if (vacio) {
                System.out.println("No hay alumnos registrados.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void eliminarAlumno(Scanner scanner, String ARCHIVO_REGISTRO) {
        System.out.println("ELIMINAR ALUMNO");
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            boolean vacio = true;

            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    System.out.println(linea);
                    vacio = false;
                }
            }

            if (vacio) {
                System.out.println("No hay alumnos registrados.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
  
    
    
    
    
    }

}
