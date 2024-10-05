package notasprimaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creacion de la clase Database la cual permite la conexión con la BD
 * @author DELL
 */
public class Database {
    private Connection conn;
    private String host = "localhost";
    private String port = "3306";
    private String dbName = "proyecto2";
    private String userName = "root";
    private String password = "vivaelcp";

    public Database() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName + "?useSSL=false&serverTimezone=UTC";
            
            conn = DriverManager.getConnection(url, this.userName, this.password);
            System.out.println("Conexión Exitosa");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: El controlador MySQL no se encuentra. Asegúrate de que el conector esté en tu proyecto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
    }


    public boolean guardarDatos(int codigo, String nombre, int nota1, int nota2, int nota3, int nota4, double promedio) {
        String query = "INSERT INTO alumnos (codigo, nombre, nota1, nota2, nota3, nota4, promedio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, nombre);
            stmt.setInt(3, nota1);
            stmt.setInt(4, nota2);
            stmt.setInt(5, nota3);
            stmt.setInt(6, nota4);
            stmt.setDouble(7, promedio);
            stmt.executeUpdate();
            System.out.println("Datos guardados exitosamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar los datos");
            e.printStackTrace();
            return false;
        }
    }

    public Alumno buscarAlumno(int codigo) {
        String query = "SELECT * FROM alumnos WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int nota1 = rs.getInt("nota1");
                int nota2 = rs.getInt("nota2");
                int nota3 = rs.getInt("nota3");
                int nota4 = rs.getInt("nota4");

                Alumno alumno = new Alumno(nombre, String.valueOf(codigo));
                alumno.setNota(1, nota1);
                alumno.setNota(2, nota2);
                alumno.setNota(3, nota3);
                alumno.setNota(4, nota4);

                return alumno;
            } else {
                System.out.println("Alumno no encontrado.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al alumno");
            e.printStackTrace();
            return null;
        }
    }
}
