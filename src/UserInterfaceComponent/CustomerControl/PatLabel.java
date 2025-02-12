package UserInterfaceComponent.CustomerControl;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

import UserInterfaceComponent.BibliotecaStyle;

public class PatLabel extends JLabel{
    public PatLabel(){
        customizeComponent();
    }
    public PatLabel(String text){
        setText(text);
        customizeComponent();
    }
    private void customizeComponent(){
        setCustomizeComponent(getText(), BibliotecaStyle.FONT, BibliotecaStyle.COLOR_FONT_LIGHT, BibliotecaStyle.ALIGNMENT_LEFT);
    }
    public void setCustomizeComponent(String text, Font  font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        setPreferredSize(new Dimension(200, 20));
        //setIcon(new ImageIcon(iconPath));
    }
}