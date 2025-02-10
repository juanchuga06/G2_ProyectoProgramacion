package UserInterfaceComponent.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import UserInterfaceComponent.BibliotecaStyle;

import javax.swing.ImageIcon;

public class PatButton  extends JButton implements MouseListener {
    public PatButton(String text){
        customizeComponent(text);
    }
    public PatButton(String text, String iconPath){
        customizeComponent(text, iconPath);
    }

    public void customizeComponent(String text, String iconPath){ 
        
        setSize(20, 70);
        addMouseListener(this);
        customizeComponent(text);
        setBounds(50, 30, 90, 20); 
        
        setIcon(new ImageIcon(iconPath));
        setFont(BibliotecaStyle.FONT);
    }
    public void customizeComponent(String text) {
        setText(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_LEFT);
        setFont(new Font("Arial", Font.PLAIN, 18));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //setForeground(Color.BLACK);
        setCursor(BibliotecaStyle.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //setForeground(Color.GRAY);
        setCursor(BibliotecaStyle.CURSOR_DEFAULT);
    }
}
