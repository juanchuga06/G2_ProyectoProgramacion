package UserInterface.Form.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BusinessLogic.entities.Personas.Cliente;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;


public class ClienteForm extends JPanel {
    private JTextField nombreField, apellidoField, cedulaField, telefonoField, correoField;
    private JComboBox<String> sexoBox, estadoCivilBox;
    private JButton guardarBtn, cancelarBtn, eliminarBtn;
    private PatLabel nombreLabel, apellidoLabel, cedulaLabel, telefonoLabel, correoLabel, sexoLabel, estadoCivilLabel, labelAux;
    private PanelClientes parentFrame;
    private Image backgroundImage;

    private Cliente cliente = null;
    Integer idCliente = 0;

    public ClienteForm(PanelClientes parentFrame, Cliente cliente, Integer idCliente) {
        this.parentFrame = parentFrame;
        this.cliente = cliente;
        if(idCliente != null)
            this.idCliente = idCliente;
        else
            this.idCliente = 0;

        initializeComponents();
        
        cargarCliente();
        mostrarCliente();

        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        eliminarBtn.addActionListener(e -> eliminarBtnClick());

    }

    public ClienteForm(PanelClientes parentFrame, Cliente cliente) {
        this.parentFrame = parentFrame;
        this.cliente = cliente;
        initializeComponents();
        
        cargarCliente();
        mostrarCliente();

        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        if(cliente != null)
            eliminarBtn.addActionListener(e -> eliminarBtnClick());
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Labels y Campos de Texto

        // int imgWidth = backgroundImage.getWidth(this);
        // int imgHeight = backgroundImage.getHeight(this);
        int x = 10;
        int y = 10; // Margen superior

        g.drawImage(backgroundImage, x, y, this);
    }

    private void guardarBtnClick() {
        if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() || cedulaField.getText().isEmpty() || telefonoField.getText().isEmpty() || correoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean clienteNull = (cliente == null);
        String confirmacion = clienteNull ? "AGREGAR" : "ACTUALIZAR";
        
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea " + confirmacion + "?", 
                "Confirmacion", 
                JOptionPane.YES_NO_OPTION
            );
    
            if (opcion == JOptionPane.YES_OPTION) {
                if (clienteNull) {
                    cliente = new Cliente(); // Se crea un nuevo cliente para evitar NullPointerException
                    
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setCedula(cedulaField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setCorreoElectronico(correoField.getText());
                    cliente.setEstadoCivil(this.parentFrame.gestorClientes.EstadoCivilList.get(estadoCivilBox.getSelectedIndex()));
                    cliente.setSexo(this.parentFrame.gestorClientes.SexoList.get(sexoBox.getSelectedIndex()));
                    // Poner la parte de sexo y estado civil
                }
                else {
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setCedula(cedulaField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setCorreoElectronico(correoField.getText());
                    cliente.setEstadoCivil(this.parentFrame.gestorClientes.EstadoCivilList.get(estadoCivilBox.getSelectedIndex()));
                    cliente.setSexo(this.parentFrame.gestorClientes.SexoList.get(sexoBox.getSelectedIndex()));
                }
    
                // Eliminamos la asignación a "exito" porque los métodos devuelven void
                if (clienteNull) {
                    this.parentFrame.gestorClientes.registrarCliente(cliente);
                } else {
                    this.parentFrame.gestorClientes.actualizarCliente(cliente);
                }
    
                JOptionPane.showMessageDialog(parentFrame, "Cliente " + confirmacion + " con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al guardar...!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        cargarCliente();
        mostrarCliente();

        this.parentFrame.recargarClientes();
    }
    
    private void eliminarBtnClick() {
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea eliminar ?", 
                "Eliminación", 
                JOptionPane.YES_NO_OPTION
            );
    
            if (opcion == JOptionPane.YES_OPTION) {
                if (!this.parentFrame.gestorClientes.eliminarCliente(cliente.getIdPersona())) {
                    System.out.println(cliente.getIdPersona());
                    throw new Exception("Error al eliminar");
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado.");
                    
                    this.parentFrame.recargarClientes();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al eliminar...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void cancelarBtnClick() {
        try {
            if(cliente == null)
                cargarCliente();
            mostrarCliente();
            this.parentFrame.recargarClientes();
        } catch (Exception e) {}
    }

    private void cargarCliente(){
        if(cliente != null){
        try{
            cliente = this.parentFrame.gestorClientes.ClienteList.get(idCliente);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al cargar cliente...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void mostrarCliente(){
        try{
            if(cliente != null){
                nombreField.setText(cliente.getNombre());
                apellidoField.setText(cliente.getApellido());
                cedulaField.setText(cliente.getCedula());
                telefonoField.setText(cliente.getTelefono());
                correoField.setText(cliente.getCorreoElectronico());
            }
            else{
                limpiarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al mostrar cliente...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeComponents(){
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 240));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle;
        if(cliente != null)
            lblTitle = new JLabel("Cliente " + (idCliente + 1));
        else 
            lblTitle = new JLabel("Nuevo Cliente");
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        nombreLabel = new PatLabel("Nombre:");
        centerPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = new PatTextBox();
        nombreField.setPreferredSize(new Dimension(250, 30));
        nombreField.setOpaque(true);
        nombreField.setBackground(Color.WHITE); // Fondo negro
        nombreField.setForeground(Color.BLACK); // Texto blanco
        nombreField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borde gris
        nombreField.setToolTipText("Ingresa el nombre");
        centerPanel.add(nombreField, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        apellidoLabel = new PatLabel("Apellido:");
        centerPanel.add(apellidoLabel, gbc);

        gbc.gridx = 1;
        apellidoField = new PatTextBox();
        apellidoField.setPreferredSize(new Dimension(250, 30));
        apellidoField.setOpaque(true);
        apellidoField.setBackground(Color.WHITE); // Mantener blanco
        apellidoField.setForeground(Color.BLACK);
        apellidoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        apellidoField.setToolTipText("Ingresa el apellido");
        centerPanel.add(apellidoField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        cedulaLabel = new PatLabel("Cedula:");
        centerPanel.add(cedulaLabel, gbc);

        gbc.gridx = 1;
        cedulaField = new PatTextBox();
        cedulaField.setPreferredSize(new Dimension(250, 30));
        cedulaField.setOpaque(true);
        cedulaField.setBackground(Color.WHITE); // Mantener blanco
        cedulaField.setForeground(Color.BLACK);
        cedulaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cedulaField.setToolTipText("Ingresa la cedula");
        centerPanel.add(cedulaField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        telefonoLabel = new PatLabel("Telefono:");
        centerPanel.add(telefonoLabel, gbc);

        gbc.gridx = 1;
        telefonoField = new PatTextBox();
        telefonoField.setPreferredSize(new Dimension(250, 30));
        telefonoField.setOpaque(true);
        telefonoField.setBackground(Color.WHITE); // Mantener blanco
        telefonoField.setForeground(Color.BLACK);
        telefonoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        telefonoField.setToolTipText("Ingresa el telefono");
        centerPanel.add(telefonoField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        correoLabel = new PatLabel("Correo Electronico:");
        centerPanel.add(correoLabel, gbc);

        gbc.gridx = 1;
        correoField = new PatTextBox();
        correoField.setPreferredSize(new Dimension(250, 30));
        correoField.setOpaque(true);
        correoField.setBackground(Color.WHITE); // Mantener blanco
        correoField.setForeground(Color.BLACK);
        correoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        correoField.setToolTipText("Ingresa la cedula");
        centerPanel.add(correoField, gbc);

        // Selectores
        gbc.gridx = 2;
        gbc.gridy = 1;
        sexoLabel = new PatLabel("Sexo:");
        centerPanel.add(sexoLabel, gbc);


        String[] nombreSexos = new String[this.parentFrame.gestorClientes.SexoList.size()];
        for (int i = 0; i < nombreSexos.length; i++) {
            nombreSexos[i] = this.parentFrame.gestorClientes.SexoList.get(i).getNombre();
        }
        
        gbc.gridx = 3;
        sexoBox = new JComboBox<>(nombreSexos);
        sexoBox.setPreferredSize(new Dimension(250, 30));
        sexoBox.setBackground(Color.WHITE);
        sexoBox.setForeground(Color.BLACK);
        sexoBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(sexoBox,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        estadoCivilLabel = new PatLabel("Estado Civil:");
        centerPanel.add(estadoCivilLabel, gbc);
        
        String[] nombreEC = new String[this.parentFrame.gestorClientes.EstadoCivilList.size()];
        for (int i = 0; i < nombreEC.length; i++) {
            nombreEC[i] = this.parentFrame.gestorClientes.EstadoCivilList.get(i).getNombre();
        }
        gbc.gridx = 3;
        estadoCivilBox = new JComboBox<>(nombreEC);
        estadoCivilBox.setPreferredSize(new Dimension(250, 30));
        estadoCivilBox.setBackground(Color.WHITE);
        estadoCivilBox.setForeground(Color.BLACK);
        estadoCivilBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(estadoCivilBox, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        guardarBtn = new JButton("Guardar");
        guardarBtn.setPreferredSize(new Dimension(200, 40));
        guardarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        guardarBtn.setBackground(Color.GREEN);
        guardarBtn.setForeground(Color.WHITE);
        guardarBtn.setFocusPainted(false);
        guardarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        centerPanel.add(guardarBtn, gbc);

        gbc.gridy = 4;
        if(cliente != null){
            eliminarBtn = new JButton("Eliminar");
            eliminarBtn.setPreferredSize(new Dimension(200, 40));
            eliminarBtn.setFont(new Font("Arial", Font.BOLD, 14));
            eliminarBtn.setBackground(Color.BLACK);
            eliminarBtn.setForeground(Color.WHITE);
            eliminarBtn.setFocusPainted(false);
            eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            centerPanel.add(eliminarBtn, gbc);
            gbc.gridy = 5;
        }

            cancelarBtn = new JButton("Cancelar");
            cancelarBtn.setPreferredSize(new Dimension(200, 40));
            cancelarBtn.setFont(new Font("Arial", Font.BOLD, 14));
            cancelarBtn.setBackground(Color.RED);
            cancelarBtn.setForeground(Color.WHITE);
            cancelarBtn.setFocusPainted(false);
            cancelarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            centerPanel.add(cancelarBtn, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        labelAux = new PatLabel("                             ");
        centerPanel.add(labelAux, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }


    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        cedulaField.setText("");
        telefonoField.setText("");
        correoField.setText("");
        sexoBox.setSelectedIndex(0);
        estadoCivilBox.setSelectedIndex(0);
    }

}
