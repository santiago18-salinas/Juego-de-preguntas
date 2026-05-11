package vista;

import java.awt.*;
import javax.swing.*;

public class MenuInicioFrame extends JFrame {
    private JTextField txtNombre;
    private JButton btnJugar, btnVerRecords, btnAdmin;
    private JComboBox<Integer> comboCantidad; // ComboBox para seleccionar la cantidad de preguntas

    // Constructor para configurar la ventana del menú de inicio
    public MenuInicioFrame() {
        initComponents(); // Método para inicializar los componentes visuales de la ventana
    }

    private void initComponents() {
        setTitle("Trivia Master - PEP Unilibre");
        setSize(400, 600); // Un poco más alto para que quepa todo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Finalizamos el programa al cerrar la ventana
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Usamos GridBagLayout para organizar los componentes en filas con espacio entre ellos
        getContentPane().setBackground(new Color(45, 45, 45)); // Fondo oscuro para un estilo más moderno

        GridBagConstraints gbc = new GridBagConstraints(); // Usamos GridBagConstraints para organizar los componentes en filas con espacio entre ellos
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Hacemos que los componentes ocupen todo el ancho disponible
        gbc.gridx = 0; // Columna 0 para todos los componentes, organizados en filas por el valor de gbc.gridy

        // 1. Título (Fila 0)
        JLabel lblTitulo = new JLabel("PEP- Unilibre", SwingConstants.CENTER); // Título centrado en la parte superior del menú de inicio
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridy = 0;
        add(lblTitulo, gbc); // Agregamos el título en la fila 0

        //Etiqueta Nombre 
        JLabel lblNombre = new JLabel("Ingresa tu nombre:");
        lblNombre.setForeground(Color.LIGHT_GRAY); // Color de texto para la etiqueta del nombre
        gbc.gridy = 1;
        add(lblNombre, gbc); // Agregamos la etiqueta para el nombre debajo del título

        //Campo de Texto para el Nombre
        txtNombre = new JTextField(); // Campo de texto para ingresar el nombre del jugador
        txtNombre.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido para el campo de texto del nombre
        gbc.gridy = 2;
        add(txtNombre, gbc); // Agregamos el campo de texto para el nombre debajo de la etiqueta correspondiente

        //Etiqueta Cantidad 
        JLabel lblCant = new JLabel("¿Cuántas preguntas responder?"); // Nueva etiqueta para la cantidad de preguntas
        lblCant.setForeground(Color.LIGHT_GRAY); // Color de texto para la etiqueta de cantidad
        gbc.gridy = 3;
        add(lblCant, gbc); // Agregamos la etiqueta para la cantidad de preguntas debajo del campo de texto del nombre

        //Combo de Cantidad
        comboCantidad = new JComboBox<>(new Integer[]{10, 15, 20}); // Opciones para la cantidad de preguntas
        gbc.gridy = 4;
        add(comboCantidad, gbc); // Agregamos el ComboBox para seleccionar la cantidad de preguntas

        // Botón Jugar 
        btnJugar = new JButton("¡EMPEZAR A JUGAR!");
        btnJugar.setBackground(new Color(46, 204, 113));
        btnJugar.setForeground(Color.WHITE); // Color de texto blanco
        btnJugar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo para el botón de jugar
        btnJugar.addActionListener(e -> iniciarJuego());
        gbc.gridy = 5; // Fila 5 para el botón de jugar
        add(btnJugar, gbc); // Agregamos el botón de jugar al menú de inicio

        //Botón Récords
        btnVerRecords = new JButton("Ver Top 10");
        btnVerRecords.addActionListener(e -> new HistorialFrame().setVisible(true)); // Abre la ventana de historial al hacer clic
        gbc.gridy = 6; // Fila 6 para el botón de ver récords
        add(btnVerRecords, gbc); // Agregamos el botón de ver récords al menú de inicio

        //Botón Modo Admin
        btnAdmin = new JButton("Modo Administrador");
        btnAdmin.setFont(new Font("Arial", Font.ITALIC, 10)); // Estilo más pequeño e informal para el botón de admin
        btnAdmin.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
        gbc.gridy = 7; // Fila 7 para el botón de modo administrador
        add(btnAdmin, gbc); // Agregamos el botón de modo administrador al menú de inicio
    }

    private void iniciarJuego() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa tu nombre", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer cantidadPreguntas = (Integer) comboCantidad.getSelectedItem();
        new JuegoFrame(nombre, cantidadPreguntas).setVisible(true);
        this.dispose(); // Cerramos el menú de inicio al abrir el juego
    }
}