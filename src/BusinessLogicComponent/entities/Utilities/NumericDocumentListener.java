package BusinessLogicComponent.entities.Utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NumericDocumentListener implements DocumentListener {
    private JTextField textField;

    public NumericDocumentListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        filtrarTexto();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filtrarTexto();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        filtrarTexto();
    }

    private void filtrarTexto() {
        SwingUtilities.invokeLater(() -> {
            String texto = textField.getText();
            if (!texto.matches("\\d*")) { 
                textField.setText(texto.replaceAll("[^\\d]", ""));
            }
        });
    }
}


