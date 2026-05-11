package administrador;


public class Administrador {
    // Atributos privados según el diagrama de clases 
    private String usuario;
    private  String contrasena;
    private boolean autenticado;

    // Constructor con credenciales por defecto
    public Administrador() {
        this.usuario = "admin";       // credenciales por defecto
        this.contrasena = "1234";    // credenciales por defecto
        this.autenticado = false;   // inicialmente no autenticado
    }

    /**
     * Valida si las credenciales ingresadas son correctas.
     * @param usuario Ingresado en el LoginFrame
     * @param contrasena Ingresada en el LoginFrame
     * @return true si coinciden, false de lo contrario
     */
    public boolean login(String usuario, String contrasena) {
        if (this.usuario.equals(usuario) && this.contrasena.equals(contrasena)) {
            this.autenticado = true;
            return true;
        }
        this.autenticado = false;
        return false;
    }

    // Finaliza la sesión del administrador 
    public void logout() {
        this.autenticado = false;
    }

    // Verifica el estado actual de la sesión 
    public boolean isAutenticado() {
        return autenticado;
    }
}
