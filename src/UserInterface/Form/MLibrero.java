package UserInterface.Form;


import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MLibrero {
    public MLibrero() {
        JFrame frame = new JFrame("Library System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        
        // Modo Librero Banner
        JLabel modeLabel = new JLabel("Usted se encuentra en Modo Librero");
        modeLabel.setBounds(500, 20, 300, 30);
        modeLabel.setOpaque(true);
        modeLabel.setBackground(Color.CYAN);
        frame.add(modeLabel);
        
        // Campo de búsqueda de libros
        JLabel searchLabel = new JLabel("Buscar libro");
        searchLabel.setBounds(20, 70, 200, 30);
        frame.add(searchLabel);
        
        JTextField searchField = new JTextField("Inserte el código ISBN del libro que desea buscar...");
        searchField.setBounds(20, 100, 300, 30);
        frame.add(searchField);
        
        JButton searchButton = new JButton("🔍");
        searchButton.setBounds(330, 100, 50, 30);
        frame.add(searchButton);
        
        // Información del libro
        JLabel bookTitle = new JLabel("Harry Potter y la Piedra Filosofal");
        bookTitle.setBounds(400, 150, 400, 30);
        frame.add(bookTitle);
        
        JLabel bookInfo = new JLabel("Autor: J.K. Rowling | Año: 1997 | Edición: 1ra");
        bookInfo.setBounds(400, 180, 400, 30);
        frame.add(bookInfo);
        
        JLabel bookStatus = new JLabel("5 copias en stock");
        bookStatus.setBounds(400, 210, 200, 30);
        bookStatus.setForeground(Color.GREEN);
        frame.add(bookStatus);
        
        JButton sellBook = new JButton("Gestionar venta de este libro");
        sellBook.setBounds(400, 250, 250, 40);
        frame.add(sellBook);
        
        JButton rentBook = new JButton("Gestionar alquiler de este libro");
        rentBook.setBounds(400, 300, 250, 40);
        frame.add(rentBook);
        
        frame.setVisible(true);
    }
}