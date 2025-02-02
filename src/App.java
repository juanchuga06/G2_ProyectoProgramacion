import javax.swing.SwingUtilities;

import UserInterface.Form.LoginFrame;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(LoginFrame :: new);
    }
}
