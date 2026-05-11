package juego;

import administrador.GestorPreguntas;
import java.util.ArrayList;
import java.util.Collections;
import modelo.Pregunta;

public class Juego {
    private Jugador jugador;
    private ArrayList<Pregunta> bancoPreguntas;
    private int indicePreguntaActual;
    private boolean activo;
    private int cantidadSolicitada; // Nueva variable para almacenar la cantidad de preguntas que se jugarán en esta partida

    // Constructor que recibe el nombre del jugador para iniciar la partida
    public Juego(String nombreJugador) {
        this.jugador = new Jugador(nombreJugador); // Creamos un nuevo jugador con el nombre proporcionado
        this.indicePreguntaActual = 0; // Empezamos desde la primera pregunta
        this.activo = true;  // Marcamos la partida como activa para que el juego pueda continuar
        
        // Cargamos las preguntas desde el gestor
        GestorPreguntas gestor = new GestorPreguntas();
        this.bancoPreguntas = gestor.consultar();
        
        // Mezclar las preguntas para que sean aleatorias
        Collections.shuffle(this.bancoPreguntas);
    }

    // Método para obtener la siguiente pregunta del banco, si no hay más preguntas, se marca la partida como inactiva
    public Pregunta obtenerSiguientePregunta() {
        if (indicePreguntaActual < bancoPreguntas.size()) {
            return bancoPreguntas.get(indicePreguntaActual++); // Retorna la pregunta actual y luego incrementa el índice para la próxima llamada
        }
        this.activo = false; // Si no hay más preguntas, la partida termina
        return null;
    }
    public Juego(String nombreJugador, int cantidadSolicitada) {
    this.jugador = new Jugador(nombreJugador);
    this.indicePreguntaActual = 0;
    this.activo = true;
    this.cantidadSolicitada = cantidadSolicitada;
    
    GestorPreguntas gestor = new GestorPreguntas();
    ArrayList<Pregunta> todas = gestor.consultar();
    
    // Mezclamos todas las preguntas primero
    Collections.shuffle(todas);
    
    // Tomamos solo la cantidad que el usuario eligió (o todas las que haya si son menos)
    int limite = Math.min(cantidadSolicitada, todas.size());
    this.bancoPreguntas = new ArrayList<>(todas.subList(0, limite));
}

    // Método para responder una pregunta, recibe un booleano que indica si la respuesta fue correcta o no
    public void responder(boolean esCorrecta) {
        if (esCorrecta) {
            jugador.sumarPuntos(1); // 1 punto por respuesta correcta
        }
    }

    // Método para verificar si la partida sigue activa, es decir, si aún hay preguntas por responder
    public boolean isActivo() { 
        return activo; 
    }
    // El método getJugador es necesario para mostrar el nombre y puntaje del jugador en la interfaz del juego
    public Jugador getJugador() {
        return jugador; 
    }
}