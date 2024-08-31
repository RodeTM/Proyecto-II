
package notasprimaria;

/**
 *
 * @author EL-RODER
 */

/**
 * Esta clase representa las notas de un alumno en los 4 bimestres y proporciona métodos para manejar la información de las notas.
 */

public class Notas {
    private double[] notas;

    public Notas() {
        this.notas = new double[4]; 
    }

    public void setNota(int bimestre, double nota) {
        if (bimestre < 1 || bimestre > 4) {
            throw new IllegalArgumentException("Número de bimestre inválido.");
        }
        notas[bimestre - 1] = nota;
    }

    public double getNota(int bimestre) {
        if (bimestre < 1 || bimestre > 4) {
            throw new IllegalArgumentException("Número de bimestre inválido.");
        }
        return notas[bimestre - 1];
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Notas: ");
        for (double nota : notas) {
            sb.append(nota).append(" ");
        }
        sb.append("\nPromedio: ").append(calcularPromedio());
        return sb.toString();
    }
}



    

