import javax.swing.SwingUtilities;

import UserInterfaceComponent.Form.LoginFrame;
public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
