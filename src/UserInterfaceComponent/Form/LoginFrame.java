package UserInterfaceComponent.Form;

import javax.swing.*;

public class LoginFrame extends JFrame {
    Integer idBibliotecarioSistema;
    public LoginFrame() {
        setTitle("Acceso al Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1020, 720); // Tamaño más acorde con el diseño
        setLocationRelativeTo(null);
        setResizable(false);

        Login loginPanel = new Login(this); // Pasar referencia al Login
        add(loginPanel);
        setVisible(true);
    }

    public void showMainMenu() {
        this.dispose();
        MainForm mf = new MainForm();
        mf.IDBibliotecarioSistema = this.idBibliotecarioSistema;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
