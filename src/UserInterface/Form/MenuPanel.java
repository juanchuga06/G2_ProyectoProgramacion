package UserInterface.Form;

import java.awt.*;
import javax.swing.*;
import UserInterface.CustomerControl.PatButton;

public class MenuPanel extends JPanel {
    private MainForm parentMainForm;
    private LoginFrame parentLoginFrame;

    public PatButton btnGestionarVenta = new PatButton("Gestionar ventas de libros"),
                     btnGestionVyD = new PatButton("Gestionar entrada de volúmenes y documentación"),
                     btnGestionarDev = new PatButton("Gestionar devolución de libros"),
                     btnGestionarAl = new PatButton("Gestionar alquileres de libros"),
                     btnGestionarClien = new PatButton("Gestionar clientes"),
                     btnGestionarVentaL = new PatButton("Gestionar venta de este libro"),
                     btnGestionAlquiler = new PatButton("Gestionar alquiler de este libro"),
                     btnSalir = new PatButton("Salir de modo librero");

    public MenuPanel(MainForm parentMainForm) {
        this.parentMainForm = parentMainForm;
        setupUI();
    }

    public MenuPanel(LoginFrame parentLoginFrame) {
        this.parentLoginFrame = parentLoginFrame;
        setupUI();;
    }

    private void setupUI() {
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        setBackground(new Color(220, 230, 240));

        // Botón "Salir de modo librero"
        btnSalir.setBounds(10, 10, 200, 40);
        btnSalir.setBackground(new Color(0, 51, 153));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setOpaque(true);
        btnSalir.setBorderPainted(false);
        add(btnSalir);
        btnSalir.addActionListener(e -> System.exit(0));

        // Botón "Gestionar ventas de libros"
        addButtonPanel(btnGestionarVenta, 10, 100, new Color(70, 130, 180));
        btnGestionarVenta.addActionListener(e -> parentMainForm.mostrarGVentasLPanel());

        // Botón "Gestionar alquileres de libros"
        addButtonPanel(btnGestionarAl, 10, 160, new Color(70, 130, 180));
        btnGestionarAl.addActionListener(e -> parentMainForm.mostrarGAlquileresLPanel());

        // Botón "Gestionar devolución de libros"
        addButtonPanel(btnGestionarDev, 10, 220, new Color(70, 130, 180));

        // Botón "Gestionar clientes"
        JPanel clientPanel = new JPanel();
        clientPanel.setBounds(10, 600, 250, 50);
        clientPanel.setBackground(new Color(200, 0, 0));
        clientPanel.setLayout(null);

        btnGestionarClien.setBounds(5, 5, 240, 40);
        btnGestionarClien.setBackground(Color.WHITE);
        btnGestionarClien.setForeground(Color.BLACK);
        btnGestionarClien.setFont(new Font("Arial", Font.BOLD, 14));

        clientPanel.add(btnGestionarClien);
        add(clientPanel);

        // Campo de búsqueda
        JTextField searchField = new JTextField("Inserte el código ISBN del libro...");
        searchField.setBounds(280, 20, 300, 30);

        JButton searchButton = new JButton(new ImageIcon("search-icon.png")); // Ícono de lupa
        searchButton.setBounds(590, 20, 40, 30);

        add(searchField);
        add(searchButton);

        // Información del libro
        JLabel bookTitle = new JLabel("El diario de Ana Frank");
        bookTitle.setBounds(410, 80, 300, 20);
        bookTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel bookAuthor = new JLabel("Autor: Jane Austen");
        bookAuthor.setBounds(410, 100, 300, 20);

        JLabel bookYear = new JLabel("Año: 1947 | Edición: Anaya");
        bookYear.setBounds(410, 120, 300, 20);

        JLabel bookStock = new JLabel("5 copias en stock");
        bookStock.setBounds(410, 140, 120, 25);
        bookStock.setOpaque(true);
        bookStock.setBackground(new Color(50, 200, 50)); // Verde
        bookStock.setForeground(Color.WHITE);
        bookStock.setHorizontalAlignment(SwingConstants.CENTER);

        add(bookTitle);
        add(bookAuthor);
        add(bookYear);
        add(bookStock);

        // Botones de acción
        addButtonPanel(btnGestionarVentaL, 1030, 100, new Color(135, 206, 250));
        addButtonPanel(btnGestionAlquiler, 1030, 160, new Color(138, 43, 226));
    }

    private void addButtonPanel(PatButton button, int x, int y, Color color) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, 250, 50);
        panel.setBackground(color);
        panel.setLayout(null);

        button.setBounds(5, 5, 240, 40);
        button.setBackground(color.darker()); // Hace que el botón sea un poco más oscuro que el fondo
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);

        panel.add(button);
        add(panel);
    }
}