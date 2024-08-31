
package notasprimaria;

/**
 *
 * @author EL-RODER
 */

import java.util.Scanner;

/**
 * Esta clase proporciona la interfaz de consola para manejar los datos de los alumnos, permite ingresar alumnos y sus notas, buscar alumnos por código y calcular el promedio de un alumno.
 */

public class NotasPrimaria {
    private GestorNotas gestor;

    public NotasPrimaria() {
        gestor = new GestorNotas();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Ingresar Alumno y Notas");
            System.out.println("2. Buscar Alumno por Código");
            System.out.println("3. Calcular Promedio de Alumno");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ingresarAlumnoYNotas(scanner);
                    break;
                case 2:
                    buscarAlumnoPorCodigo(scanner);
                    break;
                case 3:
                    calcularPromedioAlumno(scanner);
                    break;
                case 4:
                    salir = true;
                    gestor.guardarDatos("notas_alumnos.txt");
                    System.out.println("Datos guardados en notas_alumnos.txt");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private void ingresarAlumnoYNotas(Scanner scanner) {
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.next();

        System.out.print("Ingrese el código del alumno (3 dígitos): ");
        String codigo = scanner.next();

        Alumno alumno = new Alumno(nombre, codigo);

        for (int i = 0; i < 4; i++) {
            System.out.print("Ingrese la nota del bimestre " + (i + 1) + " (0-100): ");
            double nota = scanner.nextDouble();
            alumno.setNota(i + 1, nota);
        }

        gestor.agregarAlumno(alumno);
    }

    private void buscarAlumnoPorCodigo(Scanner scanner) {
        System.out.print("Ingrese el código del alumno (3 dígitos): ");
        String codigo = scanner.next();

        Alumno alumno = gestor.getAlumnoPorCodigo(codigo);
        if (alumno != null) {
            System.out.println("Alumno encontrado:");
            alumno.mostrarDatos();
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private void calcularPromedioAlumno(Scanner scanner) {
        System.out.print("Ingrese el código del alumno (3 dígitos): ");
        String codigo = scanner.next();

        Alumno alumno = gestor.getAlumnoPorCodigo(codigo);
        if (alumno != null) {
            double promedio = alumno.calcularPromedio();
            System.out.println("El promedio del alumno " + alumno.getNombre() + " es: " + promedio);
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    public static void main(String[] args) {
        NotasPrimaria app = new NotasPrimaria();
        app.mostrarMenu();
    }
}


    