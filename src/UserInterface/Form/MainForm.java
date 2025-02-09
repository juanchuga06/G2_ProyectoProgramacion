package UserInterface.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private MenuPanel menuPanel;
    private JPanel contentPanel;

    public MainForm() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1320, 720);
        setLocationRelativeTo(null);
        setResizable(false);

       
        menuPanel = new MenuPanel(this);
        add(menuPanel, BorderLayout.WEST);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

       
        System.out.println("✅ MainForm inicializado correctamente: " + this);

        setVisible(true);
    }

    // 📌 Método para volver al menú principal
    public void mostrarMenuPanel() {
        System.out.println("📌 Regresando al menú principal...");
        contentPanel.removeAll();
        contentPanel.add(menuPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
