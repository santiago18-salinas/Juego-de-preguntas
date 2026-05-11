package vista;

import administrador.Administrador;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame {
    public JPanel panel;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin,btnVolverMenuInicio;

    // Constructor para configurar la ventana de login
    public LoginFrame(){
        this.setSize(400, 500); //establecemos el tamaño de la ventana
        setTitle("Juego de preguntas"); //titulo de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); //finalizamos el programa al cerrar la ventana
        setLocationRelativeTo(null); //centramos la ventana
        IniciarComponentes();
        
    }
    private void IniciarComponentes(){
        
        colocarPanel(); // Método para colocar el panel principal donde se agregarán los demás componentes
        colocarBotones(); // Método para colocar el botón de login y asignarle su acción
        colocarEtiquetas(); // Método para colocar las etiquetas de bienvenida, usuario y contraseña
        colocarCajasDeTexto(); // Método para colocar los campos de texto para el usuario y la contraseña, y configurar sus propiedades (como ocultar el texto en el caso de la contraseña)
    }
    
    // Métodos para configurar y colocar los componentes en la ventana
    private void colocarPanel(){
        panel= new JPanel(); // creamos el panel
        panel.setLayout(null); // usamos layout null para posicionar los componentes manualmente con setBounds
        
        this.getContentPane().add(panel); //agregamos el panel a la ventana
       
    }
    private void colocarEtiquetas(){
        JLabel etiqueta = new JLabel("Bienvenido",SwingConstants.CENTER); // Creamos una etiqueta de bienvenida centrada en la parte superior de la ventana
        JLabel usuario = new JLabel("Usuario:"); // Creamos una etiqueta para el campo de usuario
        JLabel contraseña = new JLabel("Contraseña:"); // Creamos una etiqueta para el campo de contraseña
        etiqueta.setBounds(100,10 , 200, 50); // Posicionamos la etiqueta de bienvenida en la parte superior central de la ventana
        etiqueta.setOpaque(true); // permiso para cambiar el fondo de la etiqueta
        etiqueta.setForeground(Color.white); //color de la letra de la etiqueta
        etiqueta.setBackground(Color.gray); // color del fondo de la etiqueta
        etiqueta.setFont(new Font ("Arial",Font.BOLD,15));
        usuario.setBounds(25, 100, 80, 30);
        contraseña.setBounds(25, 140, 80, 30);
        panel.add(etiqueta);
        panel.add(usuario);
        panel.add(contraseña);
    }


    private void colocarCajasDeTexto() {
       
        txtUsuario = new JTextField(); // Campo para el nombre de usuario
        txtContrasena = new JPasswordField(); // Campo para contraseña, oculta el texto ingresado

        txtUsuario.setBounds(100, 100, 200, 30); // Posición y tamaño del campo de usuario
        txtContrasena.setBounds(100, 140, 200, 30); // Posición y tamaño del campo de contraseña

        panel.add(txtUsuario); // Agrega el campo de usuario al panel
        panel.add(txtContrasena); // Agrega el campo de contraseña al panel
    }


    private void colocarBotones(){
        btnLogin= new JButton("login"); // Creamos el botón de login
        btnLogin.setForeground(Color.white); // Color del texto del botón
        btnLogin.setBackground(Color.BLUE); // Color de fondo del botón
        btnLogin.setBounds(150, 180, 100, 40); // Posición y tamaño del botón
        btnLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde del botón
        btnLogin.addActionListener(e -> validarLogin()); // Agrega un ActionListener para validar el login al hacer clic
        panel.add(btnLogin); // Agrega el botón al panel

        btnVolverMenuInicio = new JButton("Menú de Inicio"); // Botón para volver al menú principal
        btnVolverMenuInicio.setBounds(150, 230, 100, 40); // Posición y tamaño del botón
        btnVolverMenuInicio.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2)); // Borde del botón
        btnVolverMenuInicio.setForeground(Color.WHITE);
        btnVolverMenuInicio.setBackground(Color.RED); // Color de fondo del botón
        btnVolverMenuInicio.addActionListener(e -> {
            dispose(); // Cierra la ventana de login
            new MenuInicioFrame().setVisible(true);
        });
        panel.add(btnVolverMenuInicio); // Agrega el botón al panel
    }

    
    private void validarLogin(){
        String usuario = txtUsuario.getText(); // Obtiene el texto ingresado en el campo de usuario
        String contrasena = new String(txtContrasena.getPassword()); // Obtiene el texto ingresado en el campo de contraseña (como un arreglo de caracteres, por eso se convierte a String)
        
        // Creamos una instancia del administrador para validar las credenciales
        Administrador admin = new Administrador();
        if (admin.login(usuario, contrasena)) {
            // Login exitoso
            JOptionPane.showMessageDialog(this, "¡Login exitoso!");
            // Abre el panel de administración
            AdminFrame adminFrame = new AdminFrame();
            adminFrame.setVisible(true);
            this.dispose(); // Cierra la ventana de login
        } else {
            // Login fallido
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
    
}
