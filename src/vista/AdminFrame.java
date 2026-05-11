package vista;

import administrador.GestorPreguntas;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Pregunta;

public class AdminFrame extends JFrame {
    private GestorPreguntas gestor;
    private JTable tablaPreguntas;
    private DefaultTableModel modeloTabla;
    private JTextField txtCodigo, txtEnunciado, txtOpA, txtOpB, txtOpC, txtOpD;
    private JComboBox<String> comboCorrecta;

    public AdminFrame() {
        gestor = new GestorPreguntas(); // Carga las preguntas existentes 
        initComponents();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Panel Administrativo - Gestión de Preguntas"); // Título de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Permite cerrar esta ventana sin afectar al resto de la aplicación
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar el formulario en la parte superior, la tabla en el centro y los botones en la parte inferior   

        //  Formulario de Pregunta
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 5, 5)); // Usamos GridLayout para organizar los campos del formulario en 8 filas y 2 columnas con espacio entre ellos
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos de la Pregunta")); // Título para el panel del formulario

        panelForm.add(new JLabel("Código:")); // Campo para el código de la pregunta (ID único)
        txtCodigo = new JTextField(); // Campo para el código de la pregunta (ID único)
        panelForm.add(txtCodigo); // Agregamos el campo para el código de la pregunta (ID único)

        panelForm.add(new JLabel("Enunciado:")); // Campo para el enunciado de la pregunta
        txtEnunciado = new JTextField(); // Campo para el enunciado de la pregunta
        panelForm.add(txtEnunciado); // Agregamos el campo para el enunciado de la pregunta

        panelForm.add(new JLabel("Opción A:")); // Agregamos el campo para la opción A
        txtOpA = new JTextField();  // Agregamos el campo para la opción A
        panelForm.add(txtOpA);

        panelForm.add(new JLabel("Opción B:"));  // Agregamos el campo para la opción B
        txtOpB = new JTextField();  // Agregamos el campo para la opción B
        panelForm.add(txtOpB);

        panelForm.add(new JLabel("Opción C:"));  // Agregamos el campo para la opción C
        txtOpC = new JTextField();  // Agregamos el campo para la opción C
        panelForm.add(txtOpC);

        panelForm.add(new JLabel("Opción D:"));
        txtOpD = new JTextField();  // Agregamos el campo para la opción D
        panelForm.add(txtOpD); // Agregamos el campo para la opción D

        panelForm.add(new JLabel("Respuesta Correcta:")); // Agregamos el campo para la respuesta correcta
        comboCorrecta = new JComboBox<>(new String[]{"A", "B", "C", "D"});  // Opciones para la respuesta correcta
        panelForm.add(comboCorrecta); // Agregamos el combo para la respuesta correcta

        JButton btnGuardar = new JButton("Registrar Pregunta"); // Botón para registrar una nueva pregunta
        btnGuardar.addActionListener(e -> accionGuardar()); // Al hacer clic en el botón, se ejecuta el método para guardar la pregunta
        panelForm.add(btnGuardar); // Agregamos el botón para registrar una nueva pregunta al formulario

        add(panelForm, BorderLayout.NORTH); // Agregamos el panel del formulario a la parte superior de la ventana 

        // Panel central tabla 
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Enunciado", "Correcta"}, 0); // Modelo para la tabla con columnas para código, enunciado y respuesta correcta
        tablaPreguntas = new JTable(modeloTabla); // Creamos la tabla con el modelo definido
        add(new JScrollPane(tablaPreguntas), BorderLayout.CENTER); // Agregamos la tabla dentro de un JScrollPane para permitir desplazamiento si hay muchas preguntas
        JPanel panelBotones = new JPanel(new FlowLayout()); // Panel para los botones de modificar, eliminar y limpiar campos

        JButton btnModificar = new JButton("Modificar"); // Botón para modificar la pregunta seleccionada
        btnModificar.addActionListener(e -> accionModificar()); // Al hacer clic en el botón, se ejecuta el método para modificar la pregunta seleccionada

        JButton btnEliminar = new JButton("Eliminar"); // Botón para eliminar la pregunta seleccionada
        btnEliminar.setBackground(Color.RED); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.addActionListener(e -> accionEliminar());

        JButton btnLimpiar = new JButton("Limpiar"); // Botón para limpiar los campos del formulario
        btnLimpiar.addActionListener(e -> limpiarCampos()); // Al hacer clic en el botón, se ejecuta el método para limpiar los campos del formulario

        panelBotones.add(btnModificar); // Agregamos el botón para modificar la pregunta seleccionada al panel de botones
        panelBotones.add(btnEliminar); // Agregamos el botón para eliminar la pregunta seleccionada al panel de botones
        panelBotones.add(btnLimpiar); // Agregamos el botón para limpiar los campos del formulario al panel de botones

        add(panelBotones, BorderLayout.SOUTH); // Agregamos el panel de botones a la parte inferior de la ventana
        JButton btnCerrarSesion = new JButton("Cerrar Sesión"); // Botón para volver al menú principal
        btnCerrarSesion.setBackground(Color.blue);
        btnCerrarSesion.setForeground(Color.white);
        btnCerrarSesion.addActionListener(e -> {
            dispose(); // Cierra la ventana de administración
            new LoginFrame().setVisible(true);
        });
        panelBotones.add(btnCerrarSesion); // Agregamos el botón para cerrar sesión al panel de botones

        // Al hacer clic en una fila, cargamos los datos en el formulario
    tablaPreguntas.getSelectionModel().addListSelectionListener(e -> {
    int fila = tablaPreguntas.getSelectedRow(); // Obtenemos la fila seleccionada en la tabla
    if (fila != -1) {
        int codigo = (int) modeloTabla.getValueAt(fila, 0); // Obtenemos el código de la pregunta seleccionada en la tabla
        // Buscamos la pregunta completa en el gestor
        Pregunta p = gestor.buscarPorCodigo(codigo); // Método que debes implementar en el GestorPreguntas para buscar una pregunta por su código
        if (p != null) {
            txtCodigo.setText(String.valueOf(p.getCodigo())); // Cargamos el código de la pregunta en el campo correspondiente
            txtCodigo.setEditable(false); // No permitimos editar el código (ID único)
            txtEnunciado.setText(p.getEnunciado()); // Cargamos el enunciado de la pregunta en el campo correspondiente
            String[] opciones = p.getOpciones(); // Obtenemos las opciones de la pregunta para cargarlas en los campos correspondientes
            txtOpA.setText(opciones[0]); // Cargamos la opción A de la pregunta en el campo correspondiente
            txtOpB.setText(opciones[1]);
            txtOpC.setText(opciones[2]);
            txtOpD.setText(opciones[3]);
            comboCorrecta.setSelectedItem(String.valueOf(p.getRespuestaCorrecta())); // Cargamos la respuesta correcta de la pregunta en el combo correspondiente
        }
    }
    });
    }

    // Método para actualizar la tabla con las preguntas del gestor
    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiamos la tabla antes de cargar los datos nuevamente
        for (Pregunta p : gestor.consultar()) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getEnunciado(), p.getRespuestaCorrecta()}); // Agregamos cada pregunta al modelo de la tabla mostrando el código, enunciado y respuesta correcta
        }
    }

    // Método para validar que todos los campos estén completos
    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Código no puede estar vacío");
            return false;
        }
        if (txtEnunciado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Enunciado no puede estar vacío");
            return false;
        }
        if (txtOpA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Opción A no puede estar vacío");
            return false;
        }
        if (txtOpB.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Opción B no puede estar vacío");
            return false;
        }
        if (txtOpC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Opción C no puede estar vacío");
            return false;
        }
        if (txtOpD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Opción D no puede estar vacío");
            return false;
        }
        return true;
    }

    // Métodos para las acciones de los botones
    private void accionGuardar() {
        // Validamos que todos los campos estén completos y el código sea un número
        if (!validarCampos()) {
            return; // Si no pasan la validación, salimos del método
        }
        try {
            int cod = Integer.parseInt(txtCodigo.getText()); // Convertimos el código a entero para usarlo como ID de la pregunta
            String enun = txtEnunciado.getText(); // Obtenemos el enunciado de la pregunta desde el campo correspondiente
            char resp = comboCorrecta.getSelectedItem().toString().charAt(0); // Obtenemos la respuesta correcta seleccionada en el combo y la convertimos a char para crear la pregunta

            Pregunta nueva = new Pregunta(cod, enun, txtOpA.getText(), txtOpB.getText(), txtOpC.getText(), txtOpD.getText(), resp); // Creamos una nueva pregunta con los datos ingresados en el formulario
            
            if (gestor.registrar(nueva)) {
                JOptionPane.showMessageDialog(this, "Pregunta registrada con éxito"); // Si la pregunta se registró correctamente, mostramos un mensaje de éxito
                actualizarTabla(); // Actualizamos la tabla para mostrar la nueva pregunta registrada
            } else {
                JOptionPane.showMessageDialog(this, "Error: Pregunta duplicada"); // Si el gestor no pudo registrar la pregunta (por ejemplo, si el código ya existe), mostramos un mensaje de error indicando que hay un duplicado
            }
        } catch (NumberFormatException e) { // Si el código no es un número, mostramos un mensaje de error indicando que el código debe ser numérico
            JOptionPane.showMessageDialog(this, "El código debe ser un número");
        }
    }
    
    // Método para modificar la pregunta seleccionada en la tabla
    private void accionModificar() {
        int fila = tablaPreguntas.getSelectedRow(); // Obtenemos la fila seleccionada en la tabla para saber qué pregunta se va a modificar
        if (fila != -1) {
            // Validamos que todos los campos estén completos
            if (!validarCampos()) {
                return; // Si no pasan la validación, salimos del método
            }
            try {
                int cod = Integer.parseInt(txtCodigo.getText()); // Obtenemos el código de la pregunta desde el campo correspondiente (aunque no se puede modificar, lo usamos para identificar la pregunta a modificar)
                String enun = txtEnunciado.getText(); // Obtenemos el enunciado de la pregunta desde el campo correspondiente
                char resp = comboCorrecta.getSelectedItem().toString().charAt(0); // Obtenemos la respuesta correcta seleccionada en el combo y la convertimos a char para crear la pregunta modificada

                Pregunta modificada = new Pregunta(cod, enun, txtOpA.getText(), txtOpB.getText(), txtOpC.getText(), txtOpD.getText(), resp);
                
                if (gestor.modificar(modificada)) {
                    JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito");
                    actualizarTabla(); // Actualizamos la tabla para mostrar los cambios realizados en la pregunta
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar la pregunta");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El código debe ser un número");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una pregunta para modificar");
        }
    }

    // Método para eliminar la pregunta seleccionada en la tabla
    private void accionEliminar() {
        int fila = tablaPreguntas.getSelectedRow(); // Obtenemos la fila seleccionada en la tabla para saber qué pregunta se va a eliminar
        if (fila != -1) {
            int cod = (int) modeloTabla.getValueAt(fila, 0); // Obtenemos el código de la pregunta seleccionada en la tabla para identificarla y eliminarla del gestor
            if (gestor.eliminar(cod)) {
                JOptionPane.showMessageDialog(this, "Pregunta eliminada con éxito");
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la pregunta");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una pregunta para eliminar");
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtCodigo.setEditable(true); // Permitimos editar el código para registrar una nueva pregunta
        txtEnunciado.setText("");
        txtOpA.setText("");
        txtOpB.setText("");
        txtOpC.setText("");
        txtOpD.setText("");
        comboCorrecta.setSelectedIndex(0);
    }
}