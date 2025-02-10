package UserInterfaceComponent.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    Integer IDBibliotecarioSistema;
    public MainForm() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245)); // Fondo claro

        JLabel lblWelcome = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblWelcome, BorderLayout.CENTER);
        
        add(panel);
        setVisible(true);
    }
}
