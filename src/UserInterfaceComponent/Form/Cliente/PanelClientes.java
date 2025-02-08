package UserInterfaceComponent.Form.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import BusinessLogicComponent.entities.gestores.GestorClientes;
import BusinessLogicComponent.entities.Personas.Cliente;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;

// Los comentarios agregados son momentaneos, hay que eliminarlos después
// los comentarios son solo para que el código sea más entendible
public class PanelClientes extends JFrame {
    private JPanel                 clientesPanel, topPanel;
    private JButton                addClienteBtn;
    private JTextField             searchField;
    private JScrollPane            scrollPane;
    private JLabel                 titleLabel;
    private PatLabel               searchLbl;
    private ArrayList<ClienteForm> clienteForms;
    public GestorClientes          gestorClientes;
    private boolean buscando = false;

    public PanelClientes() {
        clienteForms = new ArrayList<>();
        gestorClientes = new GestorClientes();

        setTitle("Gestión de Clientes");
        setSize(1020, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        inicializarTopPanel();

        scrollPane = new JScrollPane(clientesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(topPanel, BorderLayout.NORTH);  
        add(scrollPane, BorderLayout.CENTER); 
        setVisible(true);

        mostrarClientes();
    }
    
    public static void main(String[] args) {
        new PanelClientes();
    }

    private void mostrarClientes() {
        clientesPanel.removeAll();
        clienteForms.clear();
        int id = 0;
        for (Cliente c : gestorClientes.ClienteList) {
            ClienteForm clienteForm = new ClienteForm(this, c, id);
            clientesPanel.add(clienteForm);
            clienteForms.add(clienteForm);
            id++;
        }
        clientesPanel.revalidate();
        clientesPanel.repaint();
    }

    protected void recargarClientes() {
        if(buscando)
           buscando = false;
        actualizarBoton(false);
        gestorClientes.cargarClientes();
        mostrarClientes();
    }

    private void agregarNuevoCliente() {
        ClienteForm nuevoCliente = new ClienteForm(this, null);
        clienteForms.add(0, nuevoCliente);
        clientesPanel.add(nuevoCliente, 0);
        clientesPanel.revalidate();
        clientesPanel.repaint();
    }

    private void inicializarTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        // Agregar el Titulo del panel
        titleLabel = new JLabel("Gestionar Clientes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Agregar el logo en el encabezado del panel
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon); 
        headerPanel.add(logoLabel);

        // Se agrega la etiqueta de busqueda
        searchLbl = new PatLabel("Buscar por cliente por nombre:");
        headerPanel.add(searchLbl);

        // Se agrega la barra de busqueda, también se agrega un detector de evento que permite 
        // reaccionar cuando se aprieta enter, así se comienza la busqueda.
        searchField = new PatTextBox();
        searchField.setToolTipText("Buscar cliente...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buscarCliente(searchField.getText());
                    } else if(searchField.getText().isBlank()|| e.getKeyCode() == KeyEvent.VK_ENTER) {
                        recargarClientes();
                    }
                    
                }
            });
        headerPanel.add(searchField);

        // El boton de agregar clientes tiene doble función, si se está buscando,
        // sirve para cancenar la búsqueda, si no, sirve para agregar un nuevo cliente. 
        addClienteBtn = new JButton();
        actualizarBoton(buscando);
        headerPanel.add(addClienteBtn);

        // Se añade el logo, barra de búsqueda y boton de agregar clientes en el encabezado
        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        // Se agrega todo el encabezado en la parte superior del panel de clientes

        clientesPanel = new JPanel();
        clientesPanel.setLayout(new BoxLayout(clientesPanel, BoxLayout.Y_AXIS));
    }

    private void buscarCliente(String criterio) {
        // Si el criterio está vacío, este método no hace nada
        if (criterio.isBlank()) {
            recargarClientes();
            return;
        }

        // Se quita de pantalla todos los clientes que se estaban mostrando
        clientesPanel.removeAll();

        for(Cliente c: gestorClientes.ClienteList){
            if(c.getNombre().toLowerCase().contains(criterio.toLowerCase())){
                clientesPanel.add(new ClienteForm(this, c, gestorClientes.ClienteList.indexOf(c)));
                break;
            }
            else if(c.getApellido().toLowerCase().contains(criterio.toLowerCase())){
                clientesPanel.add(new ClienteForm(this, c, gestorClientes.ClienteList.indexOf(c)));
                break;
            }
        }
        
        // Aunque el metodo encuentre o no al cliente, debe cambiar el boton de agregar 
        // cliente por uno de cancelar que permita salir de ese estado, luego se reinicia el panel de clientes
        if (true) {
            this.buscando = true;
            actualizarBoton(buscando);
            topPanel.revalidate();
            topPanel.repaint();
        }

        // Se reinicia todo el panel mostrando el cliente buscado y el boton de cancelar
        clientesPanel.revalidate();
        clientesPanel.repaint();
    }

    private void actualizarBoton(boolean enBusqueda) {
        // Este metodo se encarga de cambiar el boton de agregar cliente con uno de cancelar 
        // si se esta buscando un cliente o no

        for (var al : addClienteBtn.getActionListeners()) {
            addClienteBtn.removeActionListener(al);
        }
        
        if (enBusqueda) {
            addClienteBtn.setText("Cancelar");
            addClienteBtn.addActionListener(e -> recargarClientes());
            configurarTeclaEscape(enBusqueda);
        } else {
            addClienteBtn.setText("Agregar Cliente");
            addClienteBtn.addActionListener(e -> agregarNuevoCliente());
        }

        // Luego solo se customiza el boton resultante
        addClienteBtn.setPreferredSize(new Dimension(200, 40));
        addClienteBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addClienteBtn.setBackground(Color.BLACK);
        addClienteBtn.setForeground(Color.WHITE);
        addClienteBtn.setFocusPainted(false);
        addClienteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void configurarTeclaEscape(Boolean enBusqueda) {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "accionEscape");
        actionMap.put("accionEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enBusqueda) {
                    recargarClientes(); // Llamar a la función que necesites
                }
            }
        });
    }

}
