//PROYECTO BIBLIOTECA
//GRUPO 2
//ANTHONY GAMBOA  02-02-2025


package UserInterfaceComponent.Form;
import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

import BusinessLogicComponent.entities.Gestores.GestorBibliotecarios;
import BusinessLogicComponent.entities.Personas.Bibliotecario;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;

public class Login extends JPanel {                                
    private PatLabel lblUsername, lblPassword;
    private PatTextBox txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private Image backgroundImage;
    private LoginFrame parentFrame;

    public static Integer idBibliotecarioSistema;

    public Login(LoginFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
        btnLogin.addActionListener(e -> btnLoginClick());

        // Cargar y redimensionar imagen correctamente
        try{
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar el logo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar imagen centrada en la parte superior
        int imgWidth = backgroundImage.getWidth(this);
        // int imgHeight = backgroundImage.getHeight(this);
        int x = (getWidth() - imgWidth) / 2;
        int y = 50; // Margen superior

        g.drawImage(backgroundImage, x, y, this);
    }

    private void btnLoginClick() {
        GestorBibliotecarios gestorBibliotecarios = new GestorBibliotecarios();
        String username = txtUsername.getText();
        char[] password = txtPassword.getPassword();
 
        boolean loginSuccess = false; 

        for (Bibliotecario b : gestorBibliotecarios.BibliotecarioList) {
            if (b.getUsuario().equals(username) && Arrays.equals(b.getContrasena().toCharArray(), password)) {
                JOptionPane.showMessageDialog(this, "¡Login Exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                idBibliotecarioSistema = b.getIdPersona();
                parentFrame.showMainMenu();
                loginSuccess = true; 
                break; 
            }
        }

        if (!loginSuccess) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 240));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar título
        JLabel lblTitle = new JLabel("Ingreso al Modo Librero");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(lblTitle, gbc);

        // Fila 1: Usuario
        gbc.gridwidth = 1; // Volver a una columna por celda
        gbc.gridy = 1;
        gbc.gridx = 0; // Columna de la etiqueta
        lblUsername = new PatLabel("Usuario:");
        centerPanel.add(lblUsername, gbc);

        gbc.gridx = 1; // Columna de la celda de entrada
        txtUsername = new PatTextBox();
        txtUsername.setPreferredSize(new Dimension(250, 30));
        txtUsername.setOpaque(true);
        txtUsername.setBackground(Color.WHITE); // Fondo negro
        txtUsername.setForeground(Color.BLACK); // Texto blanco
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borde gris
        txtUsername.setToolTipText("Ingresa tu usuario");
        centerPanel.add(txtUsername, gbc);

        // **Fila 2: Contraseña**
        gbc.gridy = 2;
        gbc.gridx = 0; // Columna de la etiqueta
        lblPassword = new PatLabel("Contraseña:");
        centerPanel.add(lblPassword, gbc);

        gbc.gridx = 1; // Columna de la celda de entrada
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(250, 30));
        txtPassword.setBackground(Color.WHITE); // Mantener blanco
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtPassword.setToolTipText("Ingresa tu contraseña");
        centerPanel.add(txtPassword, gbc);

        // Fila 3: Botón Ingresar
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Centrar el botón en ambas columnas
        btnLogin = new JButton("INGRESAR");
        btnLogin.setPreferredSize(new Dimension(200, 40));
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        centerPanel.add(btnLogin, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }
}
