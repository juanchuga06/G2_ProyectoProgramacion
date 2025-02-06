package UserInterfaceComponent.CustomerControl;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import UserInterfaceComponent.BibliotecaStyle;

public class PatTextBox extends JTextField {

    public PatTextBox() {
        customizeComponent();
    }

    public PatTextBox(Integer maxLength) {
        super(maxLength);
        customizeComponent();
    }

    private void customizeComponent() {
        setBorderRect();
        setFont(BibliotecaStyle.FONT);  
        setForeground(BibliotecaStyle.COLOR_FONT_LIGHT);  
        setCaretColor(BibliotecaStyle.COLOR_CURSOR);    // Color del cursor
        setPreferredSize(new Dimension(200, 30));
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borde gris
        setBorder(BorderFactory.createLineBorder(Color.GRAY));                       // Fondo transparente
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(BibliotecaStyle.COLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);  // Márgenes internos
        setBorder( new CompoundBorder(lineBorder, emptyBorder));
    }

    public void setBorderLine(){
        int thickness = 1;
        setBorder(  BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, thickness, 0),
                    BorderFactory.createMatteBorder(0, 0, thickness, 0, BibliotecaStyle.COLOR_BORDER) 
        ));
    }
}
