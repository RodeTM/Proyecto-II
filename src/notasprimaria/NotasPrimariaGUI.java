
package notasprimaria;

/**
 *
 * @author EL-RODER
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Esta clase proporciona una interfaz gráfica de usuario (GUI) para manejar las notas de los alumnos.
 * Nos permite ingresar datos de los alumnos, buscar alumnos por código y calcular el promedio de los alumnos.
 */

public class NotasPrimariaGUI extends JFrame {
    private JTextField nombreField;
    private JTextField codigoField;
    private JTextField[] notaFields = new JTextField[4];
    private GestorNotas gestor;

    public NotasPrimariaGUI() {
        gestor = new GestorNotas();
        setTitle("Gestión de Notas - Sexto Primaria");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel nombreLabel = new JLabel("Nombre del Alumno:");
        nombreField = new JTextField();

        JLabel codigoLabel = new JLabel("Código del Alumno (3 dígitos):");
        codigoField = new JTextField();

        JLabel[] bimestreLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            bimestreLabels[i] = new JLabel("Nota Bimestre " + (i + 1) + ":");
            notaFields[i] = new JTextField();
        }

        JButton guardarButton = new JButton("Guardar Datos");
        JButton calcularPromedioButton = new JButton("Calcular Promedio");
        JButton buscarButton = new JButton("Buscar Alumno");

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nombreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(codigoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(codigoField, gbc);

        for (int i = 0; i < 4; i++) {
            gbc.gridx = 0;
            gbc.gridy = 2 + i;
            add(bimestreLabels[i], gbc);

            gbc.gridx = 1;
            gbc.gridy = 2 + i;
            add(notaFields[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(guardarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(calcularPromedioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(buscarButton, gbc);

        
        JTextArea resultadoArea = new JTextArea(5, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos(resultadoArea);
            }
        });

        
        calcularPromedioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPromedio(resultadoArea);
            }
        });

        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno(resultadoArea);
            }
        });
    }

    private void guardarDatos(JTextArea resultadoArea) {
        String nombre = nombreField.getText().trim();
        String codigo = codigoField.getText().trim();

        
        if (nombre.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!codigo.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "El código debe tener exactamente 3 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumno = new Alumno(nombre, codigo);

        try {
            for (int i = 0; i < 4; i++) {
                String notaStr = notaFields[i].getText().trim();
                if (notaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese todas las notas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double nota = Double.parseDouble(notaStr);
                if (nota < 0 || nota > 100) {
                    JOptionPane.showMessageDialog(this, "Las notas deben estar entre 0 y 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                alumno.setNota(i + 1, nota);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para las notas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (gestor.getAlumnoPorCodigo(codigo) != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "El alumno con este código ya existe. ¿Desea actualizar sus notas?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                
                Alumno existente = gestor.getAlumnoPorCodigo(codigo);
                existente.setNota(1, alumno.getNotas()[0]);
                existente.setNota(2, alumno.getNotas()[1]);
                existente.setNota(3, alumno.getNotas()[2]);
                existente.setNota(4, alumno.getNotas()[3]);
                gestor.guardarDatos("notas_alumnos.txt");
                resultadoArea.setText("Datos del alumno actualizados correctamente.");
            }
        } else {
            gestor.agregarAlumno(alumno);
            gestor.guardarDatos("notas_alumnos.txt");
            resultadoArea.setText("Datos del alumno guardados correctamente.");
        }

        
        nombreField.setText("");
        codigoField.setText("");
        for (JTextField notaField : notaFields) {
            notaField.setText("");
        }
    }

    private void calcularPromedio(JTextArea resultadoArea) {
        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el código del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumno = gestor.getAlumnoPorCodigo(codigo);

        if (alumno != null) {
            double promedio = alumno.calcularPromedio();
            String estado = promedio >= 60 ? "Aprobado" : "Desaprobado";
            resultadoArea.setText("Promedio del alumno " + alumno.getNombre() + ": " + promedio + "\nEstado: " + estado);
    } else {
            JOptionPane.showMessageDialog(this, "Alumno no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void buscarAlumno(JTextArea resultadoArea) {
        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el código del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumno = gestor.getAlumnoPorCodigo(codigo);

        if (alumno != null) {
            StringBuilder infoAlumno = new StringBuilder();
            infoAlumno.append("Nombre: ").append(alumno.getNombre()).append("\n");
            infoAlumno.append("Código: ").append(alumno.getCodigo()).append("\n");
            infoAlumno.append("Notas: ").append(Arrays.toString(alumno.getNotas())).append("\n");
            infoAlumno.append("Promedio: ").append(alumno.calcularPromedio()).append("\n");

            resultadoArea.setText(infoAlumno.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Alumno no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NotasPrimariaGUI().setVisible(true);
            }
        });
    }
}




  