package juego;

import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private int puntaje;

    // Constructor que recibe el nombre del jugador para crear una nueva instancia de Jugador con un puntaje inicial de 0
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }
    // Método para sumar puntos al jugador después de cada respuesta correcta, lo que permitirá llevar un registro del puntaje acumulado durante la partida
    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }
    // Getters para acceder al nombre y puntaje del jugador desde otras clases, como el juego o la interfaz
    public String getNombre() { 
        return nombre; 
    }
    // El método getPuntaje es necesario para mostrar el puntaje actualizado en la interfaz del juego después de cada respuesta
    public int getPuntaje() { 
        return puntaje; 
    }
}