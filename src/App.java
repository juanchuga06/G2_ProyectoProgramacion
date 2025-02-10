
import javax.swing.SwingUtilities;

import UserInterfaceComponent.Form.MainForm;

public class App {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(MainForm::new);

    }
}
