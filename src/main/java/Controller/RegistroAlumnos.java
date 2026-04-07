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
                    buscarAlumno(scanner, ARCHIVO_REGISTRO);
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
            System.out.println("DNI no puede estar vacio.");
            return;
        }

        boolean existe = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().equals("DNI: " + dni)) {
                    existe = true;
                    break;
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
                System.out.println("Edad invalida.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Edad no es un numero valido.");
            return;
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine().trim();

        //añadimos el alumno al arraylist
        Alumno alumno = new Alumno(nombre, apellido, edad, curso, dni);
        alumnos.add(alumno);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, true))) {
            writer.write("DNI: " + dni);
            writer.newLine();
            writer.write("NOMBRE: " + nombre);
            writer.newLine();
            writer.write("APELLIDOS: " + apellido);
            writer.newLine();
            writer.write("EDAD: " + edad);
            writer.newLine();
            writer.write("CURSO: " + curso);
            writer.newLine();
            writer.write("-----------------------------------------------------------------------------------");
            writer.newLine();
        }

        System.out.println("Alumno agregado correctamente.");
    }
    
    public static void agregarAlumno(Alumno alumno, String ARCHIVO_REGISTRO) throws IOException {
    String dni = alumno.getDni();


    try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            if (linea.trim().equals("DNI: " + dni)) {
                throw new IOException("Ya existe un alumno con el DNI " + dni);
            }
        }
    }

    // Añadir al ArrayList 
    alumnos.add(alumno);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, true))) {
        writer.write("DNI: " + alumno.getDni()); writer.newLine();
        writer.write("NOMBRE: " + alumno.getNombre()); writer.newLine();
        writer.write("APELLIDOS: " + alumno.getApellido()); writer.newLine();
        writer.write("EDAD: " + alumno.getEdad()); writer.newLine();
        writer.write("CURSO: " + alumno.getCurso()); writer.newLine();
        writer.write("-----------------------------------------------------------------------------------");
        writer.newLine();
    }
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
  //METODO PARA LA INTERFAZ GRAFICA
 public static String mostrarAlumno(String ARCHIVO_REGISTRO) {
    StringBuilder resultado = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
        String linea;
        boolean vacio = true;

        while ((linea = reader.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                resultado.append(linea).append("\n");
                vacio = false;
            }
        }

        if (vacio) {
            resultado.append("No hay alumnos registrados.\n");
        }

    } catch (IOException e) {
        resultado.append("Error al leer el archivo: ").append(e.getMessage());
    }

    return resultado.toString();
}
    
    
    public static void eliminarAlumno(Scanner scanner, String ARCHIVO_REGISTRO) throws IOException {
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
                return;
            }
        }

        System.out.print("Introduce el DNI del alumno que quieres eliminar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("DNI vacio. Operacion cancelada.");
            return;
        }

        ArrayList<String> lineas = new ArrayList<>();
        boolean eliminar = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            ArrayList<String> bloqueActual = new ArrayList<>();

            while ((linea = reader.readLine()) != null) {
                bloqueActual.add(linea);

                
                // con esto el programa sabe cuando acaba y cuando empieza un alumno
                if (linea.startsWith("---")) {
                    if (!bloqueActual.isEmpty() && bloqueActual.get(0).trim().equals("DNI: " + dni)) {
                        eliminar = true;
                    } else {
                        lineas.addAll(bloqueActual);
                    }
                    bloqueActual.clear();
                }
            }
        }

        if (!eliminar) {
            System.out.println("Ese alumno no existe en el registro.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, false))) {
            for (String l : lineas) {
                writer.write(l);
                writer.newLine();
            }
        }

        System.out.println("Alumno eliminado correctamente.");
    }
   // METODO PARA LA INTERFAZ GRAFICA
public static String eliminarAlumno(String dni, String ARCHIVO_REGISTRO) {
    if (dni == null || dni.trim().isEmpty()) {
        return "Error: El DNI no puede estar vacío.";
    }

    dni = dni.trim();
    ArrayList<String> lineas = new ArrayList<>();
    boolean encontrado = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
        String linea;
        ArrayList<String> bloqueActual = new ArrayList<>();

        while ((linea = reader.readLine()) != null) {
            bloqueActual.add(linea);

            if (linea.startsWith("---")) {
                if (!bloqueActual.isEmpty() && bloqueActual.get(0).trim().equals("DNI: " + dni)) {
                    encontrado = true;
                } else {
                    lineas.addAll(bloqueActual);
                }
                bloqueActual.clear();
            }
        }

    } catch (IOException e) {
        return "Error al leer el archivo: " + e.getMessage();
    }

    if (!encontrado) {
        return "No existe ningún alumno con el DNI: " + dni;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, false))) {
        for (String l : lineas) {
            writer.write(l);
            writer.newLine();
        }
    } catch (IOException e) {
        return "Error al escribir el archivo: " + e.getMessage();
    }

    return "Alumno con DNI " + dni + " eliminado correctamente.";
} 
    public static void buscarAlumno(Scanner scanner, String ARCHIVO_REGISTRO) throws IOException {
        System.out.println("BUSCAR ALUMNO");
        
        //pido los datos
        System.out.print("Introduce el DNI del alumno que quieres buscar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("DNI vacio. Operacion cancelada.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_REGISTRO))) {
            String linea;
            ArrayList<String> bloque = new ArrayList<>();
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                bloque.add(linea);

                if (linea.startsWith("---")) {
                    if (!bloque.isEmpty() && bloque.get(0).trim().equals("DNI: " + dni)) {
                        for (String l : bloque) {
                            System.out.println(l);
                        }
                        encontrado = true;
                        break;
                    }
                    bloque.clear();
                }
            }

            if (!encontrado) {
                System.out.println("No existe un alumno con ese DNI.");
            }
        }
    }

}