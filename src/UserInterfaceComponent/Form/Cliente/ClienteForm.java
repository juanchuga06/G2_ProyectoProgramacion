package UserInterfaceComponent.Form.Cliente;

import BusinessLogicComponent.entities.Personas.Cliente;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.BiblioComboBox;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


// Los comentarios agregados son momentaneos, hay que eliminarlos después
// los comentarios son solo para que el código sea más entendible
public class ClienteForm extends JPanel {
    private JTextField          nombreField, apellidoField, cedulaField, telefonoField, correoField;
    private BiblioComboBox      sexoBox, estadoCivilBox, direccionesBox;
    private BiblioButton             guardarBtn, cancelarBtn, eliminarBtn;
    private PatLabel            nombreLabel, apellidoLabel, cedulaLabel, telefonoLabel, correoLabel, sexoLabel, estadoCivilLabel, labelAux;
    private PanelClientes       parentFrame;
    private Image               backgroundImage;
    private Cliente             cliente = null;
    public Integer idCliente;

    // Este constructor es para cuando hay que mostrar un cliente existente
    public ClienteForm(PanelClientes parentFrame, Cliente cliente, Integer idCliente) {
        this.parentFrame = parentFrame;
        this.cliente = cliente;
        
        // Basicamente, se tiene que controlar si el panel de este cliente es para un nuevo cliente 
        // o si es para editar o mostrar un cliente existente
        if(idCliente != null)
            this.idCliente = idCliente;
        else
            this.idCliente = 0;

        initializeComponents();
        cargarCliente();
        mostrarCliente();

        // Se le da funcion a los botones de guardar, cancelar y eliminar
        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        eliminarBtn.addActionListener(e -> eliminarBtnClick());
    }

    // Este constructor sirve para manejar un cliente nuevo
    public ClienteForm(PanelClientes parentFrame, Cliente cliente) {
        this.parentFrame = parentFrame;
        this.cliente = cliente;
        initializeComponents();
        
        cargarCliente();
        mostrarCliente();

        // No se agrega el boton de cancelar, porque es un cliente nuevo
        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        g.drawImage(backgroundImage, x, y, this);
    }

    private void guardarBtnClick() {
        // Primero se controla que todos los campos de texto no estén vacios
        if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() || cedulaField.getText().isEmpty() || telefonoField.getText().isEmpty() || correoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Luego se controla si el cliente es nuevo o es uno existente
        // Esto se hace para que el sistema sepa si debe agregarlo o actualizarlo
        boolean clienteNull = (cliente == null);
        String confirmacion = clienteNull ? "AGREGAR" : "ACTUALIZAR";
        
        try {
            // Se verifica si el usuario quiere agregar o actualizar el cliente
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea " + confirmacion + "?", 
                "Confirmacion", 
                JOptionPane.YES_NO_OPTION
            );
            
            // Si se acepta el cambio, el sistema guarda o actualiza el cliente
            // Se actualizan todos los campos menos la direccion ahem ahem Mayerli haz eso
            // Se agrega o actualiza según el caso
            if (opcion == JOptionPane.YES_OPTION) {
                if (clienteNull) {
                    cliente = new Cliente(); 
            // Si el cliente es nuevo, se debe crear un nuevo cliente para añadirle valores
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setCedula(cedulaField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setCorreoElectronico(correoField.getText());
                    cliente.setEstadoCivil(this.parentFrame.gestorClientes.EstadoCivilList.get(estadoCivilBox.getSelectedIndex()));
                    cliente.setSexo(this.parentFrame.gestorClientes.SexoList.get(sexoBox.getSelectedIndex()));
                    this.parentFrame.gestorClientes.registrarCliente(cliente);
                }
                else {
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setCedula(cedulaField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setCorreoElectronico(correoField.getText());
                    cliente.setEstadoCivil(this.parentFrame.gestorClientes.EstadoCivilList.get(estadoCivilBox.getSelectedIndex()));
                    cliente.setSexo(this.parentFrame.gestorClientes.SexoList.get(sexoBox.getSelectedIndex()));
                    this.parentFrame.gestorClientes.actualizarCliente(cliente);
                }
        
                // Al final se muestra un mensaje de exito, o un mensaje de error si algo salió mal
                JOptionPane.showMessageDialog(parentFrame, "Cliente " + confirmacion + " con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al guardar...!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        cargarCliente();
        mostrarCliente();

        // Se recarga el panel de clientes con los cambios realizados
        this.parentFrame.recargarClientes();
    }
    
    private void eliminarBtnClick(){
        // Primero se muestra un mensaje de confirmacion para asegurarse de que el usuario quiere eliminar el cliente
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea eliminar ?", 
                "Eliminación", 
                JOptionPane.YES_NO_OPTION
            );
            
        // Si la opción es si, se elimina al cliente de forma lógico
            if (opcion == JOptionPane.YES_OPTION) {
                this.parentFrame.gestorClientes.eliminarCliente(cliente.getIdPersona());
                if (true) { // replace with actual condition if needed
                    throw new Exception("Error al eliminar");
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado.");
                    
                    this.parentFrame.recargarClientes();
                }
            }
            // Se muestra un mensaje que muestra si la eliminación se hizo exitosa o no
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al eliminar...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   

    private void cancelarBtnClick() {
        // Si se está tratando de crear un nuevo cliente, se elimina el panel
        // Si se estaba editando un cliente, se devuelve al estado anterior
        try {
            if(cliente == null)
                cargarCliente();
            mostrarCliente();
            this.parentFrame.recargarClientes();
        } catch (Exception e) {}
    }

    private void cargarCliente(){
        if(cliente != null && idCliente != null){
        try{
            cliente = this.parentFrame.gestorClientes.ClienteList.get(idCliente);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al cargar cliente...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void mostrarCliente(){
        // Si el cliente es nuevo, se muestran los campos vacíos
        // Si no, se muestran los campos existentes del cliente
        try{
            if(cliente != null){
                nombreField.setText(cliente.getNombre());
                apellidoField.setText(cliente.getApellido());
                cedulaField.setText(cliente.getCedula());
                telefonoField.setText(cliente.getTelefono());
                correoField.setText(cliente.getCorreoElectronico());
                sexoBox.setSelectedIndex(cliente.getSexo().getIdSexo() - 1);
                estadoCivilBox.setSelectedIndex(cliente.getEstadoCivil().getIdEstadoCivil() - 1);
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
        nombreLabel = new PatLabel("Nombres:");
        centerPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = new PatTextBox();
        nombreField.setToolTipText("Ingresa el nombre");
        centerPanel.add(nombreField, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        apellidoLabel = new PatLabel("Apellidos:");
        centerPanel.add(apellidoLabel, gbc);

        gbc.gridx = 1;
        apellidoField = new PatTextBox();
        apellidoField.setToolTipText("Ingresa el apellido");
        centerPanel.add(apellidoField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        cedulaLabel = new PatLabel("Cedula:");
        centerPanel.add(cedulaLabel, gbc);

        gbc.gridx = 1;
        cedulaField = new PatTextBox(10);
        cedulaField.setToolTipText("Ingresa la cedula");
        centerPanel.add(cedulaField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        telefonoLabel = new PatLabel("Telefono:");
        centerPanel.add(telefonoLabel, gbc);

        gbc.gridx = 1;
        telefonoField = new PatTextBox(10);
        telefonoField.setToolTipText("Ingresa el telefono");
        centerPanel.add(telefonoField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        correoLabel = new PatLabel("Correo Electronico:");
        centerPanel.add(correoLabel, gbc);

        gbc.gridx = 1;
        correoField = new PatTextBox();
        correoField.setToolTipText("Ingresa el correo");
        centerPanel.add(correoField, gbc);

        if(cliente != null){
            gbc.gridx = 0;
            gbc.gridy = 6;
            correoLabel = new PatLabel("Direcciones:");
            centerPanel.add(correoLabel, gbc);

            String[] nombreDirecciones = new String[cliente.Direcciones.size()];
            for (int i = 0; i < nombreDirecciones.length; i++) {
                nombreDirecciones[i] = cliente.Direcciones.get(i).getCallePrimaria() + "  y " + cliente.Direcciones.get(i).getCalleSecundaria();
            }
            gbc.gridx = 1;
            direccionesBox = new BiblioComboBox(nombreDirecciones);
            centerPanel.add(direccionesBox, gbc);
        }

        gbc.gridx = 2;
        gbc.gridy = 1;
        sexoLabel = new PatLabel("Sexo:");
        centerPanel.add(sexoLabel, gbc);

        String[] nombreSexos = new String[this.parentFrame.gestorClientes.SexoList.size()];
        for (int i = 0; i < nombreSexos.length; i++) {
            nombreSexos[i] = this.parentFrame.gestorClientes.SexoList.get(i).getNombre();
        }
        gbc.gridx = 3;
        sexoBox = new BiblioComboBox(nombreSexos);
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
        estadoCivilBox = new BiblioComboBox(nombreEC);
        centerPanel.add(estadoCivilBox, gbc);
        

        gbc.gridx = 3;
        gbc.gridy = 3;
        guardarBtn = new BiblioButton("Guardar", Color.GREEN, Color.WHITE);
        centerPanel.add(guardarBtn, gbc);

        gbc.gridy = 4;
        if(cliente != null){
            eliminarBtn = new BiblioButton("Eliminar", Color.BLACK, Color.WHITE);
            eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            centerPanel.add(eliminarBtn, gbc);
            gbc.gridy = 5;
        }

            cancelarBtn = new BiblioButton("Cancelar", Color.RED, Color.WHITE);
            centerPanel.add(cancelarBtn, gbc);

        gbc.gridy = 7;
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
