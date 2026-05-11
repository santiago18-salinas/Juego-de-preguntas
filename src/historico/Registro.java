package historico;

import java.io.Serializable;

// La clase Registro representa un registro individual en el historial de puntajes, con el nombre del jugador y su puntaje obtenido
public class Registro implements Serializable {
    private String nombre;
    private int puntaje;

    // Constructor que recibe el nombre del jugador y su puntaje para crear un nuevo registro en el historial
    public Registro(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    // Getters para acceder al nombre y puntaje del registro desde otras clases
    public String getNombre() { 
        return nombre; 
    }
    // El método getPuntaje es necesario para mostrar el puntaje de cada registro en la interfaz del historial
    public int getPuntaje() { 
        return puntaje; 
    }
}