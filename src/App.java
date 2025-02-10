
import javax.swing.SwingUtilities;

import UserInterfaceComponent.Form.LoginFrame;
import UserInterfaceComponent.Form.MainForm;

public class App {
    public static void main(String[] args) throws Exception {

        // SwingUtilities.invokeLater(LoginFrame::new);

        SwingUtilities.invokeLater(MainForm::new);

    }
}
