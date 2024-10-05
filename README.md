# Gestión de Notas de Alumnos de Primaria

## 1. Introducción

Este proyecto es una aplicación con una interfaz gráfica de usuario (GUI) que permite gestionar los datos de los alumnos de primaria. Los datos que maneja incluyen:
- Código del alumno
- Nombre
- Notas de los cuatro bimestres
- Cálculo del promedio del alumno.

La aplicación también permite almacenar la información en una base de datos utilizando **HeidiSQL** y **MySQL**. Además, incluye funcionalidades para buscar un alumno por su código y visualizar sus datos.

## 2. Requisitos del Sistema

### Software Requerido
- **Java Development Kit (JDK)** versión 8 o superior.
- **MySQL** para la base de datos.
- **HeidiSQL** como herramienta de administración de la base de datos.
- **Maven** (opcional) para la gestión de dependencias del proyecto.
- **IDE** recomendado: IntelliJ IDEA o NetBeans.

### Dependencias
- MySQL Connector para la conexión con la base de datos:
  ```xml
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.27</version>
  </dependency>
  ```

## 3. Instalación


### Configurar la base de datos
1. Instala y configura MySQL, y crea una base de datos llamada `proyecto2`.
2. Crea una tabla llamada `alumnos` con la siguiente estructura:

```sql
CREATE TABLE alumnos (
    codigo INT PRIMARY KEY,
    nombre VARCHAR(100),
    nota1 INT,
    nota2 INT,
    nota3 INT,
    nota4 INT,
    promedio DOUBLE
);
```

### Configuración en el código
En la clase `Database.java`, asegúrate de que los detalles de la conexión coincidan con tu configuración de MySQL:

```java
private String host = "localhost";
private String port = "3306";
private String dbName = "proyecto2";
private String userName = "root";
private String password = "tupassword";
```

### Compilar y ejecutar
Puedes compilar y ejecutar el proyecto utilizando un IDE como IntelliJ IDEA o NetBeans. Si estás utilizando Maven, también puedes compilar desde la terminal:

```bash
mvn clean install
java -jar target/notas-1.0.jar
```

## 4. Guía de Uso

1. **Interfaz gráfica de usuario (GUI)**:
   - **Ingreso de datos**: Llena los campos de código, nombre, y las cuatro notas del alumno. Presiona el botón "Calcular Promedio" para calcular el promedio.
   - **Guardar datos**: Presiona el botón "Guardar" para almacenar los datos del alumno en la base de datos.
   - **Buscar alumno**: Ingresa el código del alumno en el campo correspondiente y presiona "Buscar Alumno" para cargar sus datos desde la base de datos.
   
2. **Conexión a la base de datos**:
   La aplicación se conecta automáticamente a la base de datos al iniciar, y los datos se almacenan o recuperan según la acción del usuario.

## 5. Estructura del Código

### Principales clases

- **GestorNotas**: Administra la lista de alumnos y carga o guarda los datos desde un archivo.
- **Alumno**: Representa la entidad de un alumno, con métodos para gestionar sus notas y calcular el promedio.
- **Notas**: Encapsula la lógica relacionada con las notas de los bimestres y el cálculo del promedio.
- **Database**: Gestiona la conexión y la interacción con la base de datos MySQL.
- **NotasPrimariaGUI**: Interfaz gráfica que permite la interacción con el usuario. Tiene botones para guardar, buscar y calcular el promedio del alumno.

### Diagrama de Clases

```plaintext
NotasPrimariaGUI
     |
Database - Alumno - Notas
```

### Estructura de carpetas
```plaintext
src/
 ├── notasprimaria/
 │    ├── Alumno.java
 │    ├── Database.java
 │    ├── GestorNotas.java
 │    ├── Notas.java
 │    └── NotasPrimariaGUI.java
 └── Main.java
```

## 6. Mantenimiento y Desarrollo

Para contribuir o añadir nuevas funcionalidades:
1. Crea una rama para tu funcionalidad:
   ```bash
   git checkout -b nueva-funcionalidad
   ```

2. Haz tus cambios y verifica que el código compila y funciona correctamente.
3. Realiza un **pull request** para integrar tus cambios en el proyecto principal.

### Mejores Prácticas
- Sigue la convención de nombres de Java.
- Asegúrate de que cualquier interacción con la base de datos esté adecuadamente documentada y probada.
- Usa **Javadoc** para documentar métodos y clases.


## 8. Créditos

Proyecto desarrollado por:
- Rode Toledo - Desarrollador Principal

Contribuciones adicionales por:
- San Google
- ChatGPT
