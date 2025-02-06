package UserInterfaceComponent.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import UserInterfaceComponent.BibliotecaStyle;

import javax.swing.ImageIcon;

public class BiblioButton  extends JButton implements MouseListener {
    public BiblioButton(String text, Color colorBG, Color colorFG){
        customizeComponent(text, colorBG, colorFG);
    }
    public BiblioButton(String text, String iconPath, Color colorBG, Color colorFG){
        customizeComponent(text, iconPath, colorBG, colorFG);
    }

    public void customizeComponent(String text, String iconPath, Color colorBG, Color colorFG){ 
        
        setSize(20, 70);
        addMouseListener(this);
        customizeComponent(text, colorBG, colorFG);
        setBounds(50, 30, 90, 20); 
        
        setIcon(new ImageIcon(iconPath));
        setFont(BibliotecaStyle.FONT);
    }
    public void customizeComponent(String text, Color colorBG, Color colorFG) {
        setText(text);
        setPreferredSize(new Dimension(200, 40));
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(colorBG);
        setForeground(colorFG);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
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
