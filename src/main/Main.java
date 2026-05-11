package main;

import vista.MenuInicioFrame;

public class Main {
    public static void main(String[] args) {
        // Iniciamos la aplicación en el hilo de eventos de Swing
        java.awt.EventQueue.invokeLater(() -> {
            new MenuInicioFrame().setVisible(true);
        });
    }
}
