# 📚 Sistema de Gestión de Alumnos — MP0485 RA5

> **Módulo:** MP0485 – Programación · **Unidad:** RA5 – Lectura/Escritura de Ficheros  
> **Centro:** Stucom · **Grupo:** DAM1  

---

## 📋 Descripción

Sistema de registro de alumnos desarrollado en **Java** como proyecto de clase del módulo **MP0485 – Programación**, correspondiente al resultado de aprendizaje **RA5: Lectura y Escritura de Ficheros**.

El programa permite gestionar un registro de alumnos de instituto de forma persistente, utilizando ficheros de texto como base de datos. Cuenta con **dos modos de uso**: interfaz de línea de comandos (terminal) e interfaz gráfica de usuario construida con **Java Swing**.

---

## ✨ Funcionalidades

| Función | Terminal | GUI |
|---|:---:|:---:|
| Agregar nuevo alumno | ✅ | ✅ |
| Mostrar lista de alumnos | ✅ | ✅ |
| Eliminar alumno por DNI | ✅ | ✅ |
| Buscar alumno por DNI | ✅ | ✅ |
| Validación de duplicados | ✅ | ✅ |
| Persistencia en fichero | ✅ | ✅ |

---

## 🏗️ Estructura del proyecto

```
StudentRegistry/
├── src/
│   ├── model/
│   │   └── Alumno.java          # Clase modelo del alumno
│   ├── controller/
│   │   └── RegistroAlumnos.java # Lógica de negocio y gestión del fichero
│   ├── view/
│   │   └── MenuView.java         # Interfaz gráfica (Java Swing)
│       └── BuscarView.java
│       └── EliminarView.java
│       └── RegistrarView.java
│       └── MenuView.java   
│  
├──
│   └── registro.txt             # Fichero de persistencia (autogenerado)
└── README.md
```

---

## 🧩 Tecnologías y conceptos aplicados

- **Java SE** — Lenguaje principal
- **Java Swing** — Interfaz gráfica de usuario
- **FileReader / FileWriter** — Lectura y escritura de ficheros de texto
- **BufferedReader / BufferedWriter** — Buffers para I/O eficiente
- **ArrayList\<Alumno\>** — Almacenamiento en memoria de los registros en tiempo de ejecución
- **Gestión de excepciones** — Control de errores de I/O y validaciones de entrada
- **Streams** — Procesamiento y filtrado de datos

---

## 👤 Modelo de datos — Clase `Alumno`

```java
public class Alumno {
    private String nombre;
    private String apellido;
    private int    edad;
    private String curso;
    private String dni;      // Identificador único
}
```

---

## 🚀 Cómo ejecutar

### Requisitos previos

- Java JDK **17** o superior
- IDE recomendado: IntelliJ IDEA / Eclipse / NetBeans

### Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/student-registry-mp0485.git
cd student-registry-mp0485
```

### Compilar

```bash
javac -d out src/**/*.java
```

### Ejecutar en modo terminal

```bash
java -cp out Main --terminal
```

### Ejecutar en modo gráfico (GUI)

```bash
java -cp out Main --gui
```

> Si se ejecuta sin argumentos, el programa pedirá al usuario que elija el modo de inicio.

---

## 📂 Formato del fichero `registro.txt`

Cada alumno se almacena en una línea con sus campos separados por punto y coma:

```
Nombre;Apellido;Edad;Curso;DNI
Ana;García;20;DAM1;12345678A
Carlos;López;22;DAW2;87654321B
```

---

## 🛡️ Validaciones implementadas

- DNI único — no se permiten registros duplicados
- Campos obligatorios — ningún campo puede quedar vacío
- Edad numérica — se valida que la edad sea un número entero positivo
- Cierre seguro del fichero tras cada operación

---

## 📄 Licencia

Proyecto académico de uso educativo. Todos los derechos reservados © 2025 — Stucom DAM1.
