package administrador;

import java.io.*;
import java.util.ArrayList;
import modelo.Pregunta;

public class GestorPreguntas {
    private ArrayList<Pregunta> preguntas; // Lista para almacenar las preguntas en memoria
    private final String rutaArchivo = "preguntas.dat"; // Archivo donde se guardará la info

    // Constructor que inicializa la lista y carga las preguntas desde el archivo
    public GestorPreguntas() {
        this.preguntas = new ArrayList<>();
        cargarDesdeArchivo(); // Al iniciar, intentamos recuperar lo guardado
    }

    // Registrar una nueva pregunta 
    public boolean registrar(Pregunta p) {
        if (existeDuplicado(p.getEnunciado())) {
            return false; // No permite duplicados 
        }
        if (buscarPorCodigo(p.getCodigo()) != null) {
            return false; // No permite códigos duplicados
        }
        preguntas.add(p);
        guardarEnArchivo(); // Guardamos la lista actualizada después de agregar la nueva pregunta
        return true;
    }

    // Verificar duplicados por enunciado 
    public boolean existeDuplicado(String enun) {
        for (Pregunta p : preguntas) {
            if (p.getEnunciado().equalsIgnoreCase(enun)) return true; // Si encontramos un enunciado igual, consideramos que es un duplicado
        }
        return false;
    }

    // Eliminar pregunta por su código 
    public boolean eliminar(int cod) {
        for (int i = 0; i < preguntas.size(); i++) {
            if (preguntas.get(i).getCodigo() == cod) {
                preguntas.remove(i); // Eliminamos la pregunta encontrada por su código
                guardarEnArchivo(); // Guardamos la lista actualizada después de eliminar la pregunta
                return true;
            }
        }
        return false; // Si no se encuentra la pregunta con el código dado, retorna false
    }

    // Consultar todas las preguntas 
    public ArrayList<Pregunta> consultar() {
        return preguntas;
    }

    // Persistencia: Guardar lista en archivo (Req. 84)
    public void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(preguntas); // Serializamos la lista de preguntas y la guardamos en el archivo
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage()); // En caso de error al guardar, imprimimos un mensaje de error con la descripción 
        }
    }

    // Persistencia cargar lista desde archivo 
    @SuppressWarnings("unchecked")
    // El método cargarDesdeArchivo verifica si el archivo existe, si es así, intenta cargar la lista de preguntas desde el archivo utilizando un ObjectInputStream
    public void cargarDesdeArchivo() {
        File file = new File(rutaArchivo); // Verificamos si el archivo existe antes de intentar cargarlo
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
                preguntas = (ArrayList<Pregunta>) ois.readObject(); // Intentamos cargar la lista de preguntas desde el archivo
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar: " + e.getMessage()); // En caso de error al cargar
            }
        }
    }
    // Método para buscar una pregunta por su código
    public Pregunta buscarPorCodigo(int cod) {
        for (Pregunta p : preguntas) {
            if (p.getCodigo() == cod) return p; // Si encontramos una pregunta con el código dado, la retornamos
        }
        return null;
    }
    // Método para modificar una pregunta existente
    public boolean modificar(Pregunta modificada) {
        for (int i = 0; i < preguntas.size(); i++) {
            if (preguntas.get(i).getCodigo() == modificada.getCodigo()) {
                // Verificar si el nuevo enunciado ya existe en otra pregunta
                for (Pregunta p : preguntas) {
                    if (p.getEnunciado().equalsIgnoreCase(modificada.getEnunciado()) && p.getCodigo() != modificada.getCodigo()) {
                        return false; // No permite duplicados
                    }
                }
                preguntas.set(i, modificada);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
}
