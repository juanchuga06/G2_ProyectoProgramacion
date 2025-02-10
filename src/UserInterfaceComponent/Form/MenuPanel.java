package UserInterfaceComponent.Form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Gestores.GestorLibros;
import UserInterfaceComponent.CustomerControl.PatButton;
import UserInterfaceComponent.Form.Alquiler.AlquilerPanel;
import UserInterfaceComponent.Form.Cliente.PanelClientes;
import UserInterfaceComponent.Form.Devolucion.DevolucionPanel;
import UserInterfaceComponent.Form.Libros.PanelLibros;

public class MenuPanel extends JPanel {
    @SuppressWarnings("unused")
    private MainForm parentMainForm;
    @SuppressWarnings("unused")
    private LoginFrame parentLoginFrame;
    private GestorLibros gestorLibros;

    public PatButton btnGestionVyD = new PatButton("Gestionar \n libros"),
                     btnGestionarDev = new PatButton("Gestionar devolución\n de libros"),
                     btnGestionarAl = new PatButton("Gestionar alquileres\n de libros"),
                     btnGestionarClien = new PatButton("Gestionar clientes"),
                     btnSalir = new PatButton("Salir de modo librero");

    public MenuPanel(MainForm parentMainForm) {
        this.gestorLibros = new GestorLibros();
        this.parentMainForm = parentMainForm;
        setupUI();
    }

    public MenuPanel(LoginFrame parentLoginFrame) {
        this.gestorLibros = new GestorLibros();
        this.parentLoginFrame = parentLoginFrame;
        setupUI();
    }

    private void setupUI() {
        // Usaremos posicionamiento absoluto para los componentes fijos del menú
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        setBackground(new Color(220, 230, 240));

        // ── Botón de Salir ──
        btnSalir.setBounds(10, 10, 200, 40);
        btnSalir.setBackground(new Color(0, 51, 153));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setOpaque(true);
        btnSalir.setBorderPainted(false);
        add(btnSalir);
        btnSalir.addActionListener(e -> System.exit(0));

        // ── Botones del Menú en Paneles ──
        addButtonPanel(btnGestionVyD, 10, 160, new Color(70, 130, 180));
        addButtonPanel(btnGestionarDev, 10, 220, new Color(70, 130, 180));
        addButtonPanel(btnGestionarAl, 10, 280, new Color(70, 130, 180));

        btnGestionVyD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelLibros panelLibros = new PanelLibros();
                panelLibros.setVisible(true);
                SwingUtilities.getWindowAncestor(MenuPanel.this).setVisible(false);
            }
        });

        btnGestionarDev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DevolucionPanel panelDevolucion = new DevolucionPanel();
                panelDevolucion.setVisible(true);
                SwingUtilities.getWindowAncestor(MenuPanel.this).setVisible(false);
            }
        });

        btnGestionarAl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlquilerPanel panelAlquiler = new AlquilerPanel();
                panelAlquiler.setVisible(true);
                SwingUtilities.getWindowAncestor(MenuPanel.this).setVisible(false);
            }
        });
    
        // ── Panel de Clientes ──
        JPanel clientPanel = new JPanel(null);
        clientPanel.setBounds(10, 600, 250, 50);
        clientPanel.setBackground(new Color(200, 0, 0));
        btnGestionarClien.setBounds(5, 5, 240, 40);
        btnGestionarClien.setBackground(Color.WHITE);
        btnGestionarClien.setForeground(Color.BLACK);
        btnGestionarClien.setFont(new Font("Arial", Font.BOLD, 14));
        clientPanel.add(btnGestionarClien);
        add(clientPanel);

        btnGestionarClien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelClientes pl = new PanelClientes();
                pl.setVisible(true);
                SwingUtilities.getWindowAncestor(MenuPanel.this).setVisible(false);
            }
        }); 

        // ── Zona de búsqueda (fija) ──
        JTextField searchField = new JTextField("Inserte el código ISBN del libro...");
        searchField.setBounds(280, 20, 300, 30);
        add(searchField);

        JButton searchButton = new JButton(new ImageIcon("search-icon.png")); // Ícono de lupa
        searchButton.setBounds(590, 20, 40, 30);
        add(searchButton);

        // ── Contenedor para los libros ──
        // Se utiliza un JPanel con BoxLayout en el eje Y para apilar las "tarjetas" de cada libro
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS));
        booksContainer.setBackground(new Color(220, 230, 240));

        ImageIcon port;
        // Agregar libros al contenedor (llama al método createBookPanel con la información de cada libro)
        for(Libro l: gestorLibros.LibroList){
            if(gestorLibros.obtenerPortada(l.getIdLibro()) == null){
                port = new ImageIcon(getClass().getResource("../Resource/placeholder-vertical.png"));
            }else{ 
                 port = gestorLibros.obtenerPortada(l.getIdLibro()).getPortada();
            }
            booksContainer.add(createBookPanel(
                port,
                l.getTitulo(),
                l.getAutor().getNombre(),
                l.getFechaPublicacion() + " | " + l.getNumeroEdicion() + "a Edición",
                l.getNumeroEjemplares() + " copias en stock",
                "US$" + l.getPrecio(),
                l.getEditorial().getNombre()
            ));
        }

        
        JScrollPane bookScrollPane = new JScrollPane(booksContainer);
        bookScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bookScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookScrollPane.getViewport().setBackground(new Color(220, 230, 240));
        bookScrollPane.setBounds(280, 60, 1000, 580);
        add(bookScrollPane);
    }

    // Método que crea una "tarjeta" de libro con la imagen y sus características
    private JPanel createBookPanel(ImageIcon portada, String title, String author, String yearEdition, String stock, String price, String editorial) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setPreferredSize(new Dimension(2000, 350));
        bookPanel.setMaximumSize(new Dimension(2000, 350));
        bookPanel.setBackground(Color.WHITE);

        // Imagen del libro
        // portada.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(portada);
        imageLabel.setBounds(10, 20, 200, 300);
        bookPanel.add(imageLabel);

        // Título
        JLabel bookTitle = new JLabel(title);
        bookTitle.setFont(new Font("Arial", Font.BOLD, 14));
        bookTitle.setBounds(220, 20, 400, 20);
        bookPanel.add(bookTitle);

        // Autor
        JLabel bookAuthor = new JLabel("Autor: " + author);
        bookAuthor.setBounds(220, 45, 400, 20);
        bookPanel.add(bookAuthor);

        // Año/Edición
        JLabel bookYear = new JLabel("Año/Edición: " + yearEdition);
        bookYear.setBounds(220, 70, 400, 20);
        bookPanel.add(bookYear);

        // Stock
        JLabel bookStock = new JLabel(stock);
        bookStock.setBounds(220, 95, 120, 20);
        bookStock.setOpaque(true);
        bookStock.setBackground(new Color(50, 200, 50));
        bookStock.setForeground(Color.WHITE);
        bookStock.setHorizontalAlignment(SwingConstants.CENTER);
        bookPanel.add(bookStock);

        JLabel bookPrice = new JLabel("Precio: " + price);
        bookPrice.setBounds(220, 120, 400, 20);
        bookPanel.add(bookPrice);

        JLabel bookEditorial = new JLabel("Editorial: " + editorial);
        bookEditorial.setBounds(220, 145, 400, 20);
        bookPanel.add(bookEditorial);

        // Separador inferior (opcional)
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 335, 2000, 1);
        bookPanel.add(separator);

        return bookPanel;
    }

    private void addButtonPanel(PatButton button, int x, int y, Color color) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 250, 50);
        panel.setBackground(color);
        button.setBounds(5, 5, 240, 40);
        button.setBackground(color.darker());
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
        panel.add(button);
        add(panel);
    }
}
