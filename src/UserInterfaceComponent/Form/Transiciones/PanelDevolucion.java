package UserInterfaceComponent.Form.Transiciones;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class PanelDevolucion extends JFrame {

    // Variables de componentes
    private JPanel mainPanel ,topPanel ,headerPanel ,centerPanel ,formAndPagoPanel ,pagoPanel ,bottomPanel;
    private JTextField clienteField, nombreLibroField, autorField, anioField, fechaDevolucionField, isbnField, totalField;
    private JComboBox<String> demoraBox, clienteBox, libroBox, bibliotecarioBox;
    private JToggleButton consumidorFinalBtn, consumidorDatosBtn;
    private JButton facturarBtn;
    private Font customFont, pagFont, Font2;

    private Border roundedBorder;

    public PanelDevolucion() {
        // Inicialización de la ventana
        setTitle("Gestionar Devolución de Libro");
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Llamamos a la función que organiza y configura los componentes
        initializeComponents();

        // Configuración del panel principal
        configureMainPanel();

        // Mostrar la ventana
        add(mainPanel);
        setVisible(true);
    }

    // Método para inicializar los componentes
    private void initializeComponents() {
        // Fuentes
        customFont = new Font("Arial", Font.BOLD, 17);
        pagFont = new Font("Arial", Font.BOLD, 20);
        Font2 = new Font("Arial", Font.BOLD, 18);

        // Bordes redondeados
        roundedBorder = BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true);

        // Crear los campos de texto
        clienteField = new JTextField(25);
        nombreLibroField = new JTextField(25);
        autorField = new JTextField(25);
        anioField = new JTextField(6);
        fechaDevolucionField = new JTextField(12);
        isbnField = new JTextField(15);
        totalField = new JTextField(20);

        // Crear el JComboBox para Demora
        String[] opcionesDemora = {"Sí", "No"};
        demoraBox = new JComboBox<>(opcionesDemora);

        String[] opcionesCliente = {"Sí", "No"};
        clienteBox = new JComboBox<>(opcionesCliente);

        String[] opcionesLibro = {"Sí", "No"};
        libroBox = new JComboBox<>(opcionesLibro);

        String[] opcionesBibliotecario = {"Sí", "No"};
        bibliotecarioBox = new JComboBox<>(opcionesBibliotecario);

        // Crear los botones para las formas de pago
        consumidorFinalBtn = new JToggleButton("Consumidor final", true);
        consumidorDatosBtn = new JToggleButton("Consumidor con datos");

        // Botón de facturar
        facturarBtn = new JButton("Facturar");
    }

    // Método para configurar el panel principal
    private void configureMainPanel() {
        // Panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 230, 240));
    
        // Configuración del panel superior
        configureTopPanel();
    
        // Configuración del panel central (formulario)
        configureCenterPanel();
    
        // Configuración del panel de pago
        //configurePagoPanel();
    
        // Configuración del panel inferior
        configureBottomPanel();
    
        // Cambiar el color de fondo del panel de formulario y pago sin afectar disposición
        formAndPagoPanel.setBackground(new Color(220, 230, 240)); // Color de fondo nuevo
        formAndPagoPanel.setBorder(roundedBorder); // Reestablecer disposición
    
        // Añadir el panel de facturación al panel de formulario y pago
        JPanel facturaPanelWrapper = new JPanel();
        facturaPanelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        facturaPanelWrapper.setBackground(new Color(220, 230, 240)); // Mismo color de fondo para mantener la uniformidad
        facturaPanelWrapper.add(bottomPanel);
    
        // Añadir los paneles al panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(formAndPagoPanel, BorderLayout.CENTER);
        mainPanel.add(facturaPanelWrapper, BorderLayout.SOUTH);
    }

    // Método para configurar el panel superior
    private void configureTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Panel para el logo y el título
        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Cargar la imagen del logo
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon);

        // Título principal
        JLabel titleLabel = new JLabel("Gestionar devolución de este libro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Agregar el logo y el título al panel del encabezado
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(titleLabel);

        // Agregar el salto de línea entre los títulos
        headerPanel.add(Box.createVerticalStrut(20));

        // Título secundario
        JLabel title2Label = new JLabel("Datos de devolución:", SwingConstants.LEFT);
        title2Label.setFont(new Font("Arial", Font.BOLD, 30));

        // Añadir el panel del encabezado y el título secundario
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(title2Label, BorderLayout.CENTER);
    }

    // Método para configurar el panel central (formulario)
    private void configureCenterPanel() {
        // Panel de formulario
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 2, 20, 30)); // Puedes ajustar el espacio entre los campos
        centerPanel.setBackground(new Color(220, 230, 240)); // Color celeste claro

        // Aumentamos el tamaño de los JTextField en el formulario
        clienteField.setPreferredSize(new Dimension(300, 30));
        nombreLibroField.setPreferredSize(new Dimension(300, 30));
        autorField.setPreferredSize(new Dimension(300, 30));
        anioField.setPreferredSize(new Dimension(100, 30));
        fechaDevolucionField.setPreferredSize(new Dimension(150, 30));
        isbnField.setPreferredSize(new Dimension(150, 30));


        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setFont(customFont);
        centerPanel.add(clienteLabel);
        centerPanel.add(clienteBox);

        JLabel libroLabel = new JLabel("Nombre del libro:");
        libroLabel.setFont(customFont);
        centerPanel.add(libroLabel);
        centerPanel.add(libroBox);

        JLabel bibliotecarioLabel = new JLabel("Nombre del bibliotecario:");
        bibliotecarioLabel.setFont(customFont);
        centerPanel.add(bibliotecarioLabel);
        centerPanel.add(bibliotecarioBox);

        // Añadir los campos de texto y sus etiquetas
        //addLabelAndField("Cliente:", clienteField);
        //addLabelAndField("Nombre del libro:", nombreLibroField);
        //addLabelAndField("Bibliotecario:", autorField);
        addLabelAndField("Año de publicación:", anioField);
        addLabelAndField("Fecha de devolución:", fechaDevolucionField);
        addLabelAndField("Código ISBN:", isbnField);

        // Añadir el JComboBox para Demora
        JLabel demoraLabel = new JLabel("Demora:");
        demoraLabel.setFont(customFont);
        centerPanel.add(demoraLabel);
        centerPanel.add(demoraBox);

        // Crear un panel contenedor para centrar el formulario
        JPanel centerWrapperPanel = new JPanel();
        centerWrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerWrapperPanel.setPreferredSize(new Dimension(640, 440));
        centerWrapperPanel.setBackground(new Color(220, 230, 240));

        // Añadir el formulario al panel contenedor
        centerWrapperPanel.add(centerPanel);

        // Añadir el contenedor centrado al panel de formulario y pago
        formAndPagoPanel = new JPanel();
        formAndPagoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        formAndPagoPanel.add(centerWrapperPanel);
    }

    // Método para agregar una etiqueta y un campo de texto al formulario
    private void addLabelAndField(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(customFont);
        centerPanel.add(label);
        centerPanel.add(field);
    }

    // Método para configurar el panel de pago
    // private void configurePagoPanel() {
    //     pagoPanel = new JPanel();
    //     pagoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    //     pagoPanel.setFont(pagFont);
    //     pagoPanel.setBackground(new Color(220, 230, 240));
    //     pagoPanel.setBorder(BorderFactory.createTitledBorder("Formas de pago"));

    //     // Crear el grupo de botones de pago
    //     ButtonGroup pagoModel = new ButtonGroup();
    //     pagoModel.add(consumidorFinalBtn);
    //     consumidorFinalBtn.setBackground(new Color(240, 240, 240));
    //     pagoModel.add(consumidorDatosBtn);
    //     consumidorDatosBtn.setBackground(new Color(240, 240, 240));

    //     // Añadir los botones al panel
    //     pagoPanel.add(consumidorFinalBtn);
    //     pagoPanel.add(consumidorDatosBtn);

    //     // Ajustar tamaño del panel de pago
    //     pagoPanel.setPreferredSize(new Dimension(340, 80));

    //     // Crear un panel contenedor para centrar el panel de pago
    //     JPanel pagoWrapperPanel = new JPanel();
    //     pagoWrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    //     pagoWrapperPanel.setBackground(new Color(200, 200, 255));
    //     pagoWrapperPanel.setBackground(new Color(220, 230, 240));

    //     // Añadir el panel de pago al contenedor centrado
    //     pagoWrapperPanel.add(pagoPanel);

    //     // Añadir el contenedor centrado al panel de formulario y pago
    //     formAndPagoPanel.add(pagoWrapperPanel);
    // }

    // Método para configurar el panel inferior (total y botón)
    private void configureBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(220, 230, 240));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Añadir separación entre los componentes

        // Modificar la fuente del texto "Total a pagar"
        JLabel totalLabel = new JLabel("Total a pagar:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Aumenta el tamaño de la fuente
        bottomPanel.add(totalLabel);

        // Campo para el total
        //totalField.setEditable(false);  // Aseguramos que no sea editable
        totalField.setBackground(new Color(240, 240, 240));
        totalField.setFont(new Font("Arial", Font.BOLD, 16));
        totalField.setPreferredSize(new Dimension(250, 30)); // Ajustamos el tamaño del campo

        // Botón de facturar
        facturarBtn.setFont(Font2);
        facturarBtn.setBackground(new Color(50, 150, 50));
        facturarBtn.setForeground(Color.WHITE);
        facturarBtn.setPreferredSize(new Dimension(110, 30));
        facturarBtn.setBorder(roundedBorder);

        // Añadir al panel inferior
        bottomPanel.add(totalField);
        bottomPanel.add(facturarBtn);
    }

    // Método principal
    public static void main(String[] args) {
        new PanelDevolucion();
    }
}