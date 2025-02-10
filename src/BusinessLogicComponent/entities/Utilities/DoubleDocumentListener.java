package BusinessLogicComponent.entities.Utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DoubleDocumentListener implements DocumentListener {
    private JTextField textField;

    public DoubleDocumentListener(JTextField textField) {
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
            
            if (!texto.matches("\\d*\\.?\\d*")) {
                textField.setText(texto.replaceAll("[^\\d.]", "")); 
                
                int firstDotIndex = textField.getText().indexOf(".");
                if (firstDotIndex != -1) { 
                    String beforeDot = textField.getText().substring(0, firstDotIndex + 1);
                    String afterDot = textField.getText().substring(firstDotIndex + 1).replace(".", "");
                    textField.setText(beforeDot + afterDot);
                }
            }
        });
    }
}


