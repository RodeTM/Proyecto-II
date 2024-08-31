
package notasprimaria;

/**
 *
 * @author EL-RODER
 */
import java.util.Arrays;

/**
 * Esta clase representa al alumno con sus notas y proporciona métodos para manejar la información del alumno.
 */

public class Alumno {
    private String nombre;
    private String codigo;
    private double[] notas;

    public Alumno(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.notas = new double[4];
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNota(int bimestre, double nota) {
        if (bimestre >= 1 && bimestre <= 4) {
            this.notas[bimestre - 1] = nota;
        } else {
            System.out.println("Número de bimestre inválido.");
        }
    }

    public double[] getNotas() {
        return notas;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Código: " + codigo);
        System.out.println("Notas: " + Arrays.toString(notas));
        double promedio = calcularPromedio();
        System.out.println("Promedio: " + promedio);
        String estado = promedio >= 60 ? "Aprobado" : "Reprobado";
        System.out.println("Estado: " + estado);
    }

    
    @Override
    public String toString() {
        double promedio = calcularPromedio();
        String estado = promedio >= 60 ? "Aprobado" : "Reprobado";
        return nombre + "," + codigo + "," +
               notas[0] + "," + notas[1] + "," +
               notas[2] + "," + notas[3] + "," +
               promedio + "," + estado;
    }

    
    public static Alumno fromString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 8) { 
            throw new IllegalArgumentException("Formato de línea inválido: " + linea);
        }
        String nombre = partes[0];
        String codigo = partes[1];
        Alumno alumno = new Alumno(nombre, codigo);
        for (int i = 0; i < 4; i++) {
            alumno.setNota(i + 1, Double.parseDouble(partes[2 + i]));
        }
        return alumno;
    }
}





