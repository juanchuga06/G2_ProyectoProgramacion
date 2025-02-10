package UserInterfaceComponent.Form.CatalogoLibro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import BusinessLogicComponent.entities.gestores.GestorCatalogoLibro;
import UserInterfaceComponent.CustomerControl.BiblioButton;


public class CatalogoPanel extends JFrame {
    private JPanel             catalogoPanel, topPanel;
    private JLabel             titleLabel;
    public GestorCatalogoLibro gestorCatalogoLibro;
    private CatalogoForm catalogoForm;

    public CatalogoPanel() { 
        gestorCatalogoLibro = new GestorCatalogoLibro(1);  // Modo inicial
        this.catalogoForm = new CatalogoForm(this);

        setTitle("Gestion de catalogo");
        setSize(1300, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(catalogoForm, BorderLayout.CENTER); 
        inicializarTopPanel();
        inicializarCatalogoPanel();
        setVisible(true);
        
    }

    public static void main(String[] args) {
        new CatalogoPanel();
    }

    private void inicializarTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
    
        titleLabel = new JLabel("Gestionar catalogo de libros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);
    
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
       
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        headerPanel.add(logoLabel);
    
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonesPanel.setOpaque(false); 
        
        JButton autorBtn = crearBoton("Autor", 1);
        JButton editorialBtn = crearBoton("Editorial", 2);
        JButton generoBtn = crearBoton("Género de libro", 3);
        BiblioButton volverBtn = new BiblioButton("Volver", Color.RED, Color.WHITE);

        botonesPanel.add(autorBtn);
        botonesPanel.add(editorialBtn);
        botonesPanel.add(generoBtn);
        botonesPanel.add(volverBtn);
    
        headerPanel.add(botonesPanel);
    
        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    
        catalogoPanel = new JPanel();
        catalogoPanel.setLayout(new BoxLayout(catalogoPanel, BoxLayout.Y_AXIS));
    }


    private void inicializarCatalogoPanel() {
        catalogoPanel = new JPanel(new BorderLayout());
        CatalogoForm catalogoForm = new CatalogoForm(this);
        catalogoPanel.add(catalogoForm, BorderLayout.CENTER);
    }
    
   
    private JButton crearBoton(String texto, int modo) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
        boton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        boton.addActionListener(e -> {
                try {
                    this.catalogoForm.cargarDatos(modo);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
        });
    
        return boton;
    }
    
}
  
