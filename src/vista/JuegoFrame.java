package vista;

import historico.Historial;
import java.awt.*;
import javax.swing.*;
import juego.Juego;
import modelo.Pregunta;

public class JuegoFrame extends JFrame {
    private Juego partida; // Aquí almacenamos la partida actual
    private Pregunta preguntaActual; // Para mostrar la pregunta actual en la interfaz
    
    // Componentes visuales
    private JLabel lblEnunciado, lblPuntaje, lblNombre;  // Para mostrar el enunciado, puntaje y nombre del jugador
    private JButton btnA, btnB, btnC, btnD, btnCancelar; // Botones para las opciones y cancelar partida

    // Constructor que recibe el nombre del jugador para iniciar la partida
   // Cambia el constructor de JuegoFrame
    public JuegoFrame(String nombreJugador, int cantidad) {
    // Ahora le pasamos la cantidad a la clase Juego
    this.partida = new Juego(nombreJugador, cantidad); 
    initComponents();
    mostrarSiguientePregunta();
}

    // Método para inicializar los componentes visuales de la ventana
    private void initComponents() {
        setTitle("Partida en curso");
        setSize(600, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Obligamos a usar el botón cancelar
        setLayout(new BorderLayout(20, 20)); // Usamos BorderLayout para organizar mejor los componentes
        setLocationRelativeTo(null); // Centra la ventana

        // Cabecera: Nombre y Puntaje
        JPanel panelInfo = new JPanel(new GridLayout(1, 2)); // Usamos GridLayout para colocar el nombre a la izquierda y el puntaje a la derecha
        lblNombre = new JLabel(" Jugador: " + partida.getJugador().getNombre()); // Mostramos el nombre del jugador en la cabecera
        lblPuntaje = new JLabel("Puntaje: 0 ", SwingConstants.RIGHT); // Mostramos el puntaje inicial a la derecha
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo para el nombre del jugador
        panelInfo.add(lblNombre); // Agregamos el nombre al panel de información
        panelInfo.add(lblPuntaje); // Agregamos el puntaje al panel de información
        add(panelInfo, BorderLayout.NORTH); // Agregamos el panel de información a la parte superior de la ventana

        // Centro: Enunciado
        lblEnunciado = new JLabel("Cargando pregunta...", SwingConstants.CENTER); // Inicialmente mostramos un mensaje de carga mientras se obtiene la primera pregunta
        lblEnunciado.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo para el enunciado de la pregunta
        add(lblEnunciado, BorderLayout.CENTER); // Agregamos el enunciado al centro de la ventana

        // Sur: Opciones y Cancelar
        JPanel panelInferior = new JPanel(new BorderLayout()); // Usamos BorderLayout para colocar las opciones en el centro y el botón cancelar abajo
        JPanel panelOpciones = new JPanel(new GridLayout(2, 2, 10, 10)); // Usamos GridLayout para organizar los botones de opciones en una cuadrícula de 2x2

        btnA = new JButton("A"); // Inicialmente solo mostramos la letra, el texto completo se asignará cuando se muestre la pregunta
        btnB = new JButton("B"); 
        btnC = new JButton("C");
        btnD = new JButton("D");

        // Asignar eventos a los botones
        btnA.addActionListener(e -> validarRespuesta('A')); // Cuando se presione el botón A, se validará la respuesta con la letra 'A'
        btnB.addActionListener(e -> validarRespuesta('B'));
        btnC.addActionListener(e -> validarRespuesta('C'));
        btnD.addActionListener(e -> validarRespuesta('D')); 

        panelOpciones.add(btnA); // Agregamos el botón A al panel de opciones
        panelOpciones.add(btnB); // Agregamos el botón B al panel de opciones
        panelOpciones.add(btnC); // Agregamos el botón C al panel de opciones
        panelOpciones.add(btnD); // Agregamos el botón D al panel de opciones


        btnCancelar = new JButton("Cancelar Partida"); // Botón para cancelar la partida y volver al menú principal
        btnCancelar.setBackground(Color.LIGHT_GRAY); // Estilo para el botón cancelar
        // Asignamos el evento para cancelar la partida y volver al menú principal
        btnCancelar.addActionListener(e -> {
            dispose();
            new MenuInicioFrame().setVisible(true);
        });

        panelInferior.add(panelOpciones, BorderLayout.CENTER); // Agregamos el panel de opciones al centro del panel inferior
        panelInferior.add(btnCancelar, BorderLayout.SOUTH); // Agregamos el botón cancelar a la parte inferior del panel inferior
        add(panelInferior, BorderLayout.SOUTH); // Agregamos el panel inferior a la parte inferior de la ventana
    }

    private void mostrarSiguientePregunta() {
        preguntaActual = partida.obtenerSiguientePregunta(); // Obtenemos la siguiente pregunta de la partida
        // Si hay una pregunta disponible, la mostramos. Si no, finalizamos el juego.
        if (preguntaActual != null) {
            lblEnunciado.setText("<html><body style='width: 400px; text-align: center;'>"   // Ajustamos el texto para que se vea bien en el JLabel
                                + preguntaActual.getEnunciado() + "</body></html>");  // Ajustamos el texto para que se vea bien en el JLabel
            String[] opciones = preguntaActual.getOpciones();
            btnA.setText("A: " + opciones[0]); // Asignamos el texto completo a cada botón de opción
            btnB.setText("B: " + opciones[1]);
            btnC.setText("C: " + opciones[2]);
            btnD.setText("D: " + opciones[3]);
        } else {
            // Ya no hay más preguntas
            finalizarJuego();
        }
    }

    private void validarRespuesta(char letra) {
        boolean correcta = preguntaActual.verificar(letra); // Verificamos si la respuesta seleccionada es correcta
        partida.responder(correcta); // Actualizamos el puntaje del jugador según si la respuesta fue correcta o no
        // Mostramos un mensaje de feedback al jugador
        if (correcta) {
            JOptionPane.showMessageDialog(this, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto. La respuesta era: " + preguntaActual.getRespuestaCorrecta());
        }

        lblPuntaje.setText("Puntaje: " + partida.getJugador().getPuntaje() + " ");  // Actualizamos el puntaje en la interfaz
        mostrarSiguientePregunta(); // Mostramos la siguiente pregunta o finalizamos si no hay más
    }

    private void finalizarJuego() {

        int puntosFinales = partida.getJugador().getPuntaje(); // Obtenemos el puntaje final del jugador al finalizar la partida
        String nombre = partida.getJugador().getNombre(); // Obtenemos el nombre del jugador para mostrarlo en el mensaje final y guardarlo en el historial
        
        Historial historial = new Historial(); // Creamos una instancia del historial para guardar el resultado de la partida
        
        // comprobamos si es récord comparando con lo que ya existe
        boolean esRecord = historial.esNuevoRecord(puntosFinales);
        
        //guardamos el resultado actual
        historial.guardarResultado(nombre, puntosFinales);
        
        //preparamos el mensaje
        String mensaje = "Partida terminada, " + nombre + ".\n" +
                         "Tu puntaje final: " + puntosFinales;
        
        if (esRecord && puntosFinales > 0) {
            mensaje += "\n\n¡INCREÍBLE! 🎉\nHas establecido un NUEVO RÉCORD.";
        } else if (puntosFinales == 0) {
            mensaje += "\n\n¡No te desanimes! Intenta responder al menos una pregunta correctamente para establecer un récord.";
        } else {
            mensaje += "\n\n¡Buen intento! Sigue practicando para superar el récord actual.";
        }

        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);

        // Regresamos al Menú Principal
        new MenuInicioFrame().setVisible(true);
        this.dispose();
    }
}