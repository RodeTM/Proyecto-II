
package notasprimaria;

/**
 *
 * @author EL-RODER
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase permite la gestión de los datos de los alumnos, agregar alumnos a la lista, buscar alumnos por código,
 * y guardar y cargar datos desde un archivo de texto.
 */

public class GestorNotas {
    private List<Alumno> listaAlumnos;

    public GestorNotas() {
        listaAlumnos = new ArrayList<>();
        cargarDatos("notas_alumnos.txt");
    }
   
    public void agregarAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }
    
    public Alumno getAlumnoPorCodigo(String codigo) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getCodigo().equals(codigo)) {
                return alumno;
            }
        }
        return null;
    }
    
    public void guardarDatos(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Alumno alumno : listaAlumnos) {
                writer.write(alumno.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public void cargarDatos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
         
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    Alumno alumno = Alumno.fromString(linea);
                    listaAlumnos.add(alumno);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error al leer línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    
