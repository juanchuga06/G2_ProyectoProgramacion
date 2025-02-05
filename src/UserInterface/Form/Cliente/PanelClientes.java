package UserInterface.Form.Cliente;

import javax.swing.*;
import BusinessLogic.entities.Gestores.GestorClientes;
import BusinessLogic.entities.Personas.Cliente;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PanelClientes extends JFrame {
    private JPanel clientesPanel, topPanel;
    private JButton addClienteBtn;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private boolean buscando = false;
    private ArrayList<ClienteForm> clienteForms = new ArrayList<>();
    
    public GestorClientes gestorClientes;

    public PanelClientes() {
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

        gestorClientes = new GestorClientes();
        mostrarClientes();
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
        this.buscando = false;
        actualizarBoton(false);
        gestorClientes.cargarClientes();
        mostrarClientes();
    }

    private void agregarNuevoCliente() {
        System.out.println("Me he creado");
        ClienteForm nuevoCliente = new ClienteForm(this, null);
        
        clienteForms.add(0, nuevoCliente);
        clientesPanel.add(nuevoCliente, 0);
        clientesPanel.revalidate();
        clientesPanel.repaint();
    }

    public static void main(String[] args) {
        new PanelClientes();
    }

    private void inicializarTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        titleLabel = new JLabel("Gestionar Clientes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterface/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon); 
        headerPanel.add(logoLabel);

        PatLabel searchLbl = new PatLabel("Buscar por cliente por nombre:");
        headerPanel.add(searchLbl);

        searchField = new PatTextBox();
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setOpaque(true);
        searchField.setBackground(Color.WHITE); // Mantener blanco
        searchField.setForeground(Color.BLACK);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

        addClienteBtn = new JButton();
        actualizarBoton(buscando);
        headerPanel.add(addClienteBtn);

        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        clientesPanel = new JPanel();
        clientesPanel.setLayout(new BoxLayout(clientesPanel, BoxLayout.Y_AXIS));
    }


    private void buscarCliente(String criterio) {
        if (criterio.isBlank()) {
            recargarClientes();
            return;
        }

        clientesPanel.removeAll();
        boolean encontrado = true;

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

        if (encontrado) {
            this.buscando = true;
            actualizarBoton(true);
            topPanel.revalidate();
            topPanel.repaint();
        }

        clientesPanel.revalidate();
        clientesPanel.repaint();
    }


    private void actualizarBoton(boolean enBusqueda) {
        for (var al : addClienteBtn.getActionListeners()) {
            addClienteBtn.removeActionListener(al);
        }
        
        if (enBusqueda) {
            addClienteBtn.setText("Cancelar");
            addClienteBtn.setPreferredSize(new Dimension(200, 40));
            addClienteBtn.setFont(new Font("Arial", Font.BOLD, 14));
            addClienteBtn.setBackground(Color.BLACK);
            addClienteBtn.setForeground(Color.WHITE);
            addClienteBtn.setFocusPainted(false);
            addClienteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addClienteBtn.addActionListener(e -> recargarClientes());
        } else {
            addClienteBtn.setText("Añadir Cliente");
            addClienteBtn.setPreferredSize(new Dimension(200, 40));
            addClienteBtn.setFont(new Font("Arial", Font.BOLD, 14));
            addClienteBtn.setBackground(Color.BLACK);
            addClienteBtn.setForeground(Color.WHITE);
            addClienteBtn.setFocusPainted(false);
            addClienteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addClienteBtn.addActionListener(e -> agregarNuevoCliente());
        }
    }
}
