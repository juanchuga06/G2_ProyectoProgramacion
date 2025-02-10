package UserInterfaceComponent.Form.Libros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import BusinessLogicComponent.entities.Gestores.GestorLibros;
import BusinessLogicComponent.entities.Libros.Libro;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.PatTextBox;
import UserInterfaceComponent.Form.MainForm;
import UserInterfaceComponent.Form.CatalogoLibro.CatalogoPanel;

public class PanelLibros extends JFrame{
    private JPanel                 librosPanel, topPanel;
    private JButton                addLibroBtn, editarCatBtn;
    private JTextField             searchField;
    private JScrollPane            scrollPane;
    private JLabel                 titleLabel;
    private JLabel           searchLbl;
    private ArrayList<LibroForm>   libroForms;
    public GestorLibros            gestorLibros;
    private boolean buscando = false;

    public PanelLibros() {
        libroForms = new ArrayList<>();
        gestorLibros = new GestorLibros();

        setTitle("Gestión de Libros");
        setSize(1300, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        inicializarTopPanel();

        scrollPane = new JScrollPane(librosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(topPanel, BorderLayout.NORTH);  
        add(scrollPane, BorderLayout.CENTER); 
        setVisible(true);

        mostrarLibros();
    }
    
    public static void main(String[] args) {
        new PanelLibros();
    }

    private void mostrarLibros() {
        librosPanel.removeAll();
        libroForms.clear();
        int id = 0;
        for (Libro l : gestorLibros.LibroList) {
            LibroForm libroForm = new LibroForm(this, l, id);
            librosPanel.add(libroForm);
            libroForms.add(libroForm);
            id++;
        }
        librosPanel.revalidate();
        librosPanel.repaint();
    }

    protected void recargarLibros() {
        searchField.setText("");
        if(buscando)
           buscando = false;
        actualizarBoton(false);
        gestorLibros.cargarLibros();
        mostrarLibros();
    }

    private void agregarNuevoLibro() {
        LibroForm nuevoLibro = new LibroForm(this, null);
        libroForms.add(0, nuevoLibro);
        librosPanel.add(nuevoLibro, 0);
        librosPanel.revalidate();
        librosPanel.repaint();
    }

    private void inicializarTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        titleLabel = new JLabel("Gestionar Libros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton volverBtn = new BiblioButton("Volver", Color.DARK_GRAY, Color.WHITE);
        volverBtn.setAlignmentX(CENTER_ALIGNMENT);
        headerPanel.add(volverBtn);

         volverBtn.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainForm mf = new MainForm();
                mf.setVisible(true);
                dispose();
            }
        });
        
        try{
            ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
            Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(backgroundImage);
            JLabel logoLabel = new JLabel(scaledIcon); 
            headerPanel.add(logoLabel);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar el logo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        searchLbl = new JLabel("Buscar por libro por ISBN:");
        headerPanel.add(searchLbl);

        searchField = new PatTextBox();
        searchField.setToolTipText("Buscar libro...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buscarLibro(searchField.getText());
                    } else if(searchField.getText().isBlank()|| e.getKeyCode() == KeyEvent.VK_ENTER) {
                        recargarLibros();
                    }
                    
                }
            });
        headerPanel.add(searchField);
 
        addLibroBtn = new JButton();
        actualizarBoton(buscando);
        headerPanel.add(addLibroBtn);

        editarCatBtn = new JButton("Editar Catalogo");
        editarCatBtn.setPreferredSize(new Dimension(200, 40));
        editarCatBtn.setFont(new Font("Arial", Font.BOLD, 14));
        editarCatBtn.setBackground(Color.DARK_GRAY);
        editarCatBtn.setForeground(Color.WHITE);
        editarCatBtn.setFocusPainted(false);
        editarCatBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editarCatBtn.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CatalogoPanel cp = new CatalogoPanel();
                cp.setVisible(true);
                dispose();
            }
        });
        
        headerPanel.add(editarCatBtn);

        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        
        librosPanel = new JPanel();
        librosPanel.setLayout(new BoxLayout(librosPanel, BoxLayout.Y_AXIS));
    }

    private void buscarLibro(String criterio) {

        if (criterio.isBlank()) {
            recargarLibros();
            return;
        }

        librosPanel.removeAll();

        for(Libro l: gestorLibros.LibroList){
            if(l.getCodigoISBN().toLowerCase().contains(criterio.toLowerCase())){
                librosPanel.add(new LibroForm(this, l, gestorLibros.LibroList.indexOf(l)));
                break;
            }
            else if(l.getCodigoBarras().toLowerCase().contains(criterio.toLowerCase())){
                librosPanel.add(new LibroForm(this, l, gestorLibros.LibroList.indexOf(l)));
                break;
            }
        }
        
        if (true) {
            this.buscando = true;
            actualizarBoton(buscando);
            topPanel.revalidate();
            topPanel.repaint();
        }

        librosPanel.revalidate();
        librosPanel.repaint();
    }

    private void actualizarBoton(boolean enBusqueda) {
        for (var al : addLibroBtn.getActionListeners()) {
            addLibroBtn.removeActionListener(al);
        }
        
        if (enBusqueda) {
            addLibroBtn.setText("Cancelar");
            addLibroBtn.addActionListener(e -> recargarLibros());
            configurarTeclaEscape(enBusqueda);
            
        } else {
            addLibroBtn.setText("Agregar Libro");
            addLibroBtn.addActionListener(e -> agregarNuevoLibro());
        }

        // Luego solo se customiza el boton resultante
        addLibroBtn.setPreferredSize(new Dimension(200, 40));
        addLibroBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addLibroBtn.setBackground(Color.BLACK);
        addLibroBtn.setForeground(Color.WHITE);
        addLibroBtn.setFocusPainted(false);
        addLibroBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    private void configurarTeclaEscape(Boolean enBusqueda) {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "accionEscape");
        actionMap.put("accionEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enBusqueda) {
                    recargarLibros(); // Llamar a la función que necesites
                }
            }
        });
    }

}

