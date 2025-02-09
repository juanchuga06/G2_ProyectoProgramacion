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
                     btnSalir = new PatButton("Salir de modo librero");

    public MenuPanel(MainForm parentMainForm) {
        this.parentMainForm = parentMainForm;
        setupUI();
    }

    public MenuPanel(LoginFrame parentLoginFrame) {
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
        addButtonPanel(btnGestionarVenta, 10, 100, new Color(70, 130, 180));
        addButtonPanel(btnGestionVyD, 10, 160, new Color(70, 130, 180));
        addButtonPanel(btnGestionarDev, 10, 220, new Color(70, 130, 180));
        addButtonPanel(btnGestionarAl, 10, 280, new Color(70, 130, 180));

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

        // Agregar libros al contenedor (llama al método createBookPanel con la información de cada libro)
        booksContainer.add(createBookPanel(
                "/UserInterface/Resource/ElDiariodeAnaFrank.png",
                "El diario de Ana Frank",
                "Jane Austen",
                "1947 | Edición: Anaya",
                "5 copias en stock",
                "En su diario, Anne Frank describe sus pensamientos y sentimientos en el aislamiento forzado con su padre, su madre y su hermana, así como con otros cuatro judíos refugiados en el anexo secreto ubicado en Prinsengracht, frente a uno de los canales más icónicos de la capital holandesa.12 "
        ));
        booksContainer.add(createBookPanel(
                "/UserInterface/Resource/Mataraunruiseñor.png",
                "Matar a un Ruiseñor",
                "Autor 2",
                "1950 | Edición: Primera",
                "3 copias en stock",
                "Reseña del Libro 2: Una aventura llena de misterio y sorpresas que atrapa al lector..."
        ));
        booksContainer.add(createBookPanel(
                "/UserInterface/Resource/Orgulloyprejuicio.png",
                "Orgullo y prejuicio",
                "Autor 3",
                "1980 | Edición: Moderna",
                "2 copias en stock",
                "Una reseña del Libro 3 que explica la relevancia histórica de la obra y su impacto cultural..."
        ));
        booksContainer.add(createBookPanel(
                "/UserInterface/Resource/DonQuijotedelamancha.png",
                "Don Quijote de la Mancha",
                "Autor 4",
                "2000 | Edición: Actual",
                "4 copias en stock",
                "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));
        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/Dune.png",
            "Dune",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));

        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/Elseñordelosanillos.png",
            "El Señor de los Anillos",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));
        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/Losjuegosdelhambre.png",
            "Los Juegos del Hambre",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));
        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/Elalquinista.png",
            "El Alquimista",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));
        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/1984.png",
            "1984",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));
        booksContainer.add(createBookPanel(
            "/UserInterface/Resource/ElcodigoDaVinci.png",
            "El Codigo Da Vinci",
            "Autor 4",
            "2000 | Edición: Actual",
            "4 copias en stock",
            "Este libro ofrece una visión contemporánea sobre temas actuales, con una narrativa ágil y conmovedora..."
        ));

        
        JScrollPane bookScrollPane = new JScrollPane(booksContainer);
        bookScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bookScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookScrollPane.getViewport().setBackground(new Color(220, 230, 240));
        bookScrollPane.setBounds(280, 60, 1000, 580);
        add(bookScrollPane);
    }

    // Método que crea una "tarjeta" de libro con la imagen y sus características
    private JPanel createBookPanel(String imagePath, String title, String author, String yearEdition, String stock, String review) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setPreferredSize(new Dimension(2000, 200));
        bookPanel.setMaximumSize(new Dimension(2000, 200));
        bookPanel.setBackground(Color.WHITE);

        // Imagen del libro
        ImageIcon bookImage = new ImageIcon(new ImageIcon(getClass().getResource(imagePath))
                .getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(bookImage);
        imageLabel.setBounds(10, 20, 120, 160);
        bookPanel.add(imageLabel);

        // Título
        JLabel bookTitle = new JLabel(title);
        bookTitle.setFont(new Font("Arial", Font.BOLD, 14));
        bookTitle.setBounds(140, 20, 400, 20);
        bookPanel.add(bookTitle);

        // Autor
        JLabel bookAuthor = new JLabel("Autor: " + author);
        bookAuthor.setBounds(140, 45, 400, 20);
        bookPanel.add(bookAuthor);

        // Año/Edición
        JLabel bookYear = new JLabel("Año/Edición: " + yearEdition);
        bookYear.setBounds(140, 70, 400, 20);
        bookPanel.add(bookYear);

        // Stock
        JLabel bookStock = new JLabel(stock);
        bookStock.setBounds(140, 95, 120, 20);
        bookStock.setOpaque(true);
        bookStock.setBackground(new Color(50, 200, 50));
        bookStock.setForeground(Color.WHITE);
        bookStock.setHorizontalAlignment(SwingConstants.CENTER);
        bookPanel.add(bookStock);

        // Review
        JTextArea bookReview = new JTextArea(review);
        bookReview.setLineWrap(true);
        bookReview.setWrapStyleWord(true);
        bookReview.setEditable(false);
        bookReview.setBackground(bookPanel.getBackground());
        bookReview.setBounds(140, 120, 400, 60);
        bookPanel.add(bookReview);

        // Separador inferior (opcional)
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 195, 2000, 1);
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
