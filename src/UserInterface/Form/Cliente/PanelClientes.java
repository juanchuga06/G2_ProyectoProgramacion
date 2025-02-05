package UserInterface.Form.Cliente;

import javax.swing.*;

import BusinessLogic.entities.Gestores.GestorClientes;
import BusinessLogic.entities.Personas.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelClientes extends JFrame {
    private JPanel clientesPanel;
    private JButton addClienteBtn;
    private JScrollPane scrollPane;
    private ArrayList<ClienteForm> clienteForms = new ArrayList<>();
    
    public GestorClientes gestorClientes;


    public PanelClientes() {
        setTitle("Gestión de Clientes");
        setSize(1020, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel contenedor de clientes con desplazamiento
        clientesPanel = new JPanel();
        clientesPanel.setLayout(new BoxLayout(clientesPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(clientesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botón para añadir clientes
        addClienteBtn = new JButton("Añadir Cliente");
        addClienteBtn.addActionListener(e -> agregarNuevoCliente());

        add(addClienteBtn, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        gestorClientes = new GestorClientes();
        mostrarClientes();
    }

    private void mostrarClientes(){
        Integer id = 0;
        for(Cliente c: gestorClientes.ClienteList){
            ClienteForm clienteForm = new ClienteForm(this, c, id);
            clientesPanel.add(clienteForm);
            clienteForms.add(clienteForm);
            clientesPanel.revalidate();
            clientesPanel.repaint();
            id++;
        }
    }

    private void agregarNuevoCliente() {
        ClienteForm nuevoCliente = new ClienteForm(this, null);
        clienteForms.add(nuevoCliente);
        clientesPanel.add(nuevoCliente);
        clientesPanel.revalidate();
        clientesPanel.repaint();
    }

    public static void main(String[] args) {
        new PanelClientes();
    }
}
    

