package UserInterfaceComponent.CustomerControl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class BiblioComboBox extends JComboBox<String>{

    public BiblioComboBox(String[] items) {
        super(items);
        customizeComponent();
    }

    private void customizeComponent(){
        setPreferredSize(new Dimension(120, 30));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
