package UserInterfaceComponent.Form;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private MenuPanel menuPanel;
    private JPanel contentPanel;

    public MainForm() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 720);
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

        setVisible(true);
    }

    public void mostrarMenuPanel() {
        contentPanel.removeAll();
        contentPanel.add(menuPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
