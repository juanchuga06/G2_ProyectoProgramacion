package UserInterfaceComponent.Form;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LoginFrame() {
        setTitle("Acceso al Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1320, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // ✅ Crear paneles de Login y Menú
        Login loginPanel = new Login(this);
        MenuPanel menuPanel = new MenuPanel(this);

        // ✅ Agregar los paneles a `CardLayout`
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(menuPanel, "Menu");

        // ✅ Establecer el panel inicial (Login)
        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        setVisible(true);
    }

    // ✅ Método para mostrar el menú después del login
    public void showMainMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}