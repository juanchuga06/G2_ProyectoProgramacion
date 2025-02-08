package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private MenuPanel menuPanel;
    private JPanel contentPanel;

    public MainForm() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1020, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        // ✅ Ahora `MenuPanel` acepta `MainForm` como parámetro
        menuPanel = new MenuPanel(this);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));

        JLabel lblWelcome = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));

        contentPanel.add(lblWelcome, BorderLayout.CENTER);

        // Agregar paneles a la ventana principal
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.WEST);   // Menú en la izquierda
        add(contentPanel, BorderLayout.CENTER);  // Contenido principal en el centro

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainForm::new);
    }
}
