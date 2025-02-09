package UserInterface.Form;

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

       
        Login loginPanel = new Login(this);
        MenuPanel menuPanel = new MenuPanel(this);

        
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(menuPanel, "Menu");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        setVisible(true);
    }

    
    public void showMainMenu() {
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        this.dispose();
    }
}