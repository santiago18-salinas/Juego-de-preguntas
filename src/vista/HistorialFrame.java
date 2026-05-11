package vista;

import historico.Historial;
import historico.Registro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistorialFrame extends JFrame {
    private JTable tablaRecords;
    private DefaultTableModel modelo;

    public HistorialFrame() {
        setTitle("Ranking - Top 10 Jugadores"); // Título de la ventana
        setSize(400, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //  Permite cerrar esta ventana sin afectar al resto de la aplicación
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Usamos BorderLayout para organizar el título, la tabla y el botón de cerrar

        JLabel lblTitulo = new JLabel("MEJORES PUNTAJES", SwingConstants.CENTER); // Título centrado para el ranking
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); // Estilo para el título del ranking
        add(lblTitulo, BorderLayout.NORTH); // Agregamos el título a la parte superior de la ventana

        modelo = new DefaultTableModel(new Object[]{"Puesto", "Nombre", "Puntaje"}, 0); // Modelo para la tabla con columnas para puesto, nombre y puntaje
        tablaRecords = new JTable(modelo); // Creamos la tabla con el modelo definido
        
        cargarDatos(); // Cargamos los datos del historial para mostrar en la tabla

        add(new JScrollPane(tablaRecords), BorderLayout.CENTER); // Agregamos la tabla dentro de un JScrollPane para permitir desplazamiento si hay muchos registros

        JButton btnCerrar = new JButton("Volver al Menú"); // Botón para cerrar esta ventana y volver al menú principal
        btnCerrar.addActionListener(e -> dispose()); // Al hacer clic en el botón, se cierra esta ventana
        add(btnCerrar, BorderLayout.SOUTH); // Agregamos el botón a la parte inferior de la ventana
    }

    private void cargarDatos() {
        Historial h = new Historial(); // Creamos una instancia del historial para acceder a los registros
        int puesto = 1; // Variable para llevar el conteo del puesto en el ranking
        for (Registro r : h.obtenerTop10()) {
            modelo.addRow(new Object[]{puesto++, r.getNombre(), r.getPuntaje()}); // Agregamos cada registro al modelo de la tabla, mostrando el puesto, nombre y puntaje
        }
    }
}