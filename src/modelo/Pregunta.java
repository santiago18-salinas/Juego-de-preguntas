package modelo;

public class Pregunta implements java.io.Serializable {

// Implementamos Serializable para poder guardar el objeto en archivos fácilmente
    
    private static final long serialVersionUID = 1L; // Para control de versiones en la serialización
    private int codigo;
    private String enunciado;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private char respuestaCorrecta;

    // Constructor 
    public Pregunta(int codigo, String enunciado, String opcionA, String opcionB, String opcionC, String opcionD, char respuestaCorrecta) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuestaCorrecta = Character.toUpperCase(respuestaCorrecta);
    }

    // Getters necesarios para la lógica y las tablas 
    public int getCodigo() { 
        return codigo; 
    }
    // El enunciado es lo que se mostrará al jugador, por eso es importante tener un getter para acceder a él
    public String getEnunciado() { 
        return enunciado; 
    }
    // Las opciones también necesitan getters para mostrar el texto completo de cada opción en la interfaz del juego
    public String[] getOpciones() { 
        return new String[]{opcionA, opcionB, opcionC, opcionD}; 
    }
    // El método getRespuestaCorrecta es útil para mostrar la respuesta correcta al jugador cuando responde incorrectamente
    public char getRespuestaCorrecta() { 
        return respuestaCorrecta; 
    }

    // Método para validar si la respuesta del usuario es la correcta 
    public boolean verificar(char respuestaUsuario) {
        return Character.toUpperCase(respuestaUsuario) == this.respuestaCorrecta;
    }

    @Override
    public String toString() {
        return "Pregunta #" + codigo + ": " + enunciado;
    }

}
