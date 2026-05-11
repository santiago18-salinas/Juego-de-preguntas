package historico;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Historial {
    private ArrayList<Registro> registros;
    private final String FILE_NAME = "historico.dat";

    // Constructor que inicializa la lista de registros y carga el historial desde el archivo, si existe
    public Historial() {
        registros = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Método para guardar un nuevo resultado en el historial, recibe el nombre del jugador y su puntaje, crea un nuevo registro y lo agrega a la lista, luego ordena la lista de registros de mayor a menor puntaje y guarda el historial actualizado en el archivo
    public void guardarResultado(String nombre, int puntaje) {
        registros.add(new Registro(nombre, puntaje));
        // Ordenamos de mayor a menor puntaje
        Collections.sort(registros, (r1, r2) -> Integer.compare(r2.getPuntaje(), r1.getPuntaje())); // El registro con el puntaje más alto quedará al inicio de la lista
        guardarEnArchivo(); // Guardamos el historial actualizado después de agregar el nuevo resultado
    }

    // Método para verificar si el puntaje actual del jugador es un nuevo récord, compara el puntaje actual con el puntaje más alto registrado en el historial, si el historial está vacío, cualquier puntaje es considerado un nuevo récord
    public boolean esNuevoRecord(int puntajeActual) {
        if (registros.isEmpty()) return true;
        // El primer registro es el puntaje más alto (el récord)
        return puntajeActual > registros.get(0).getPuntaje(); // Si el puntaje actual es mayor que el récord actual, entonces es un nuevo récord
    }

    // Método para obtener el top 10 de jugadores con los puntajes más altos, devuelve una lista con los primeros 10 registros del historial ordenados de mayor a menor puntaje, si el historial tiene menos de 10 registros, devuelve todos los registros disponibles
    public ArrayList<Registro> obtenerTop10() {
        if (registros.size() > 10) {
            return new ArrayList<>(registros.subList(0, 10)); // Retorna solo los primeros 10 registros
        }
        return registros;
    }

    // Métodos privados para manejar la persistencia del historial en un archivo, el método guardarEnArchivo serializa la lista de registros y la guarda en un archivo, mientras que el método cargarDesdeArchivo intenta cargar la lista de registros desde el archivo, si el archivo no existe o hay un error al cargarlo, se inicializa una nueva lista vacía
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registros); // Serializamos la lista de registros y la guardamos en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // En caso de error al guardar, imprimimos la traza del error para diagnóstico
        }
    }

    // El método cargarDesdeArchivo verifica si el archivo existe, si es así, intenta cargar la lista de registros desde el archivo utilizando un ObjectInputStream, si el archivo no existe o hay un error durante la carga (como una excepción de clase no encontrada), se inicializa una nueva lista vacía para evitar que el programa falle al intentar acceder a un historial inexistente
    private void cargarDesdeArchivo() {
        File file = new File(FILE_NAME); // Verificamos si el archivo existe antes de intentar cargarlo
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                registros = (ArrayList<Registro>) ois.readObject(); // Intentamos cargar la lista de registros desde el archivo
            } catch (Exception e) {
                registros = new ArrayList<>(); // Si hay un error al cargar (como IOException o ClassNotFoundException), inicializamos una nueva lista vacía para evitar que el programa falle
            }
        }
    }
}