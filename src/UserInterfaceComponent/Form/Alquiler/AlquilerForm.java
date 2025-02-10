package UserInterfaceComponent.Form.Alquiler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Personas.Cliente;
import BusinessLogicComponent.entities.Transacciones.Alquiler;
import BusinessLogicComponent.entities.Utilities.NumericDocumentListener;
import UserInterfaceComponent.BibliotecaStyle;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;
import UserInterfaceComponent.Form.Login;

public class AlquilerForm extends JPanel{
    private JLabel              clienteLbl, libroLbl, biblioLbl, numCopiasLbl, buscarLbl, fechaAlqLbl,  codBarrasLbl, codISBNLbl;
    private JTextField          buscarLibroField;
    private Map<Integer, Libro>  libroMap;
    private Map<Integer, Cliente>clienteMap;
    private JComboBox<Libro>    libroBox;
    private JComboBox<Cliente>  clienteBox;
    private BiblioButton        guardarBtn, cancelarBtn, eliminarBtn, marcarDevueltoBtn;
    private AlquilerPanel       parentFrame;
    private Image               backgroundImage;
    private Alquiler            alquiler = null;
    public Integer idAlquiler;

    // Este constructor es para cuando hay que mostrar un cliente existente
    public AlquilerForm(AlquilerPanel parentFrame, Alquiler alquiler, Integer idAlquiler) {
        this.parentFrame = parentFrame;
        this.alquiler = alquiler;
        
        // Basicamente, se tiene que controlar si el panel de este cliente es para un nuevo cliente 
        // o si es para editar o mostrar un cliente existente
        if(idAlquiler != null)
            this.idAlquiler = idAlquiler;
        else
            this.idAlquiler = 0;

        initializeComponents();
        cargarAlquiler();
        mostrarAlquiler();

        // Se le da funcion a los botones de guardar, cancelar y eliminar
        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        eliminarBtn.addActionListener(e -> eliminarBtnClick());
    }

    // Este constructor sirve para manejar un cliente nuevo
    public AlquilerForm(AlquilerPanel parentFrame, Alquiler alquiler) {
        this.parentFrame = parentFrame;
        this.alquiler = alquiler;
        initializeComponents();
        
        cargarAlquiler();
        mostrarAlquiler();

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

        // Luego se controla si el cliente es nuevo o es uno existente
        // Esto se hace para que el sistema sepa si debe agregarlo o actualizarlo
        boolean alquilerNull = (alquiler == null);
        String confirmacion = alquilerNull ? "AGREGAR" : "ACTUALIZAR";
        
            // Se verifica si el usuario quiere agregar o actualizar el alquiler
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
            if (alquilerNull) {
                alquiler = new Alquiler();
                alquiler.setCliente((Cliente) clienteBox.getSelectedItem());
                alquiler.setLibro((Libro) libroBox.getSelectedItem());
                alquiler.setBibliotecario(this.parentFrame.gestorAlquileres.gestorBibliotecarios.getBibliotecarioByID(Login.idBibliotecarioSistema));
                alquiler.setEstadoAlquiler(this.parentFrame.gestorAlquileres.getEstAlquilerByID(1));
                if(!this.parentFrame.gestorAlquileres.registrarAlquiler(alquiler)){
                    return;
                }

            }
            else {
                alquiler.setCliente((Cliente) clienteBox.getSelectedItem());
                alquiler.setLibro((Libro) libroBox.getSelectedItem());
                alquiler.setBibliotecario(this.parentFrame.gestorAlquileres.gestorBibliotecarios.getBibliotecarioByID(Login.idBibliotecarioSistema));
                alquiler.setEstadoAlquiler(this.parentFrame.gestorAlquileres.getEstAlquilerByID(1));
                if(!this.parentFrame.gestorAlquileres.actualizarAlquiler(alquiler)){
                    return;
                }
                }
            }
    
            // Al final se muestra un mensaje de exito, o un mensaje de error si algo salió mal
            JOptionPane.showMessageDialog(parentFrame, "Alquiler " + confirmacion + " con exito el " + LocalDate.now().toString(), "Exito", JOptionPane.INFORMATION_MESSAGE);
        cargarAlquiler();
        mostrarAlquiler();

        // Se recarga el panel de clientes con los cambios realizados
        this.parentFrame.recargarAlquileres();
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
                if (!this.parentFrame.gestorAlquileres.eliminarAlquiler(alquiler.getIdAlquiler())) {
                    throw new Exception("Error al eliminar");
                } else {
                    JOptionPane.showMessageDialog(this, "Alquiler eliminado.");
                    
                    this.parentFrame.recargarAlquileres();
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
            if(alquiler == null)
                cargarAlquiler();
            mostrarAlquiler();
            this.parentFrame.recargarAlquileres();
        } catch (Exception e) {}
    }

    private void marcarDevueltoBtnClick(){
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea marcar como devuelto a este alquiler?", 
                "Devolución", 
                JOptionPane.YES_NO_OPTION
            );
            
        // Si la opción es si, se elimina al cliente de forma lógico
            if (opcion == JOptionPane.YES_OPTION) {
                if (!this.parentFrame.gestorAlquileres.marcarDevuelto(alquiler)) {
                    throw new Exception("Error al marcar como devuelto");
                } else {
                    JOptionPane.showMessageDialog(this, "Alquiler " + idAlquiler + " marcado como devuelto el " + LocalDate.now().toString());
                    this.parentFrame.recargarAlquileres();
                }
            }
            // Se muestra un mensaje que muestra si la eliminación se hizo exitosa o no
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al marcar como devuelto...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarAlquiler(){
        if(alquiler != null && idAlquiler != null){
        try{
            alquiler = this.parentFrame.gestorAlquileres.AlquilerList.get(idAlquiler);
        }
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(parentFrame, "Error al cargar Alquiler...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void mostrarAlquiler(){
        // Si el cliente es nuevo, se muestran los campos vacíos
        // Si no, se muestran los campos existentes del cliente
        try{
            if(alquiler != null){
                clienteBox.setSelectedItem(clienteMap.get(alquiler.getCliente().getIdPersona()));
                System.out.println(alquiler.getCliente().getIdPersona());
                libroBox.setSelectedItem(libroMap.get(alquiler.getLibro().getIdLibro()));
            }
            else{
                limpiarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al mostrar alquiler...!", "Error", JOptionPane.ERROR_MESSAGE);
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
        if(alquiler != null)
            lblTitle = new JLabel("Alquiler:  " + alquiler.getLibro().getTitulo());
        else 
            lblTitle = new JLabel("Nuevo Alquiler");
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(lblTitle, gbc);


        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        clienteLbl = new PatLabel("Cliente:");
        clienteLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
        centerPanel.add(clienteLbl, gbc);

        // implementación selector clientes
        gbc.gridx = 1;
        if(!this.parentFrame.gestorAlquileres.gestorClientes.ClienteList.isEmpty()){
            clienteMap = new HashMap<>();
            for(Cliente c: this.parentFrame.gestorAlquileres.gestorClientes.ClienteList){
                clienteMap.put(c.getIdPersona(), c);
            }
            clienteBox = new JComboBox<>(clienteMap.values().toArray(new Cliente[0]));
            centerPanel.add(clienteBox, gbc);

            clienteBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    revalidate();
                    repaint();
                }
            });
        }

        gbc.gridx = 2;
        gbc.gridy = 0;
        buscarLbl = new PatLabel("Buscar por codigo:");
        buscarLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
        centerPanel.add(buscarLbl, gbc);
        
        gbc.gridx = 3;
        buscarLibroField = new PatTextBox();
        buscarLibroField.getDocument().addDocumentListener(new NumericDocumentListener(buscarLibroField));
        buscarLibroField.addKeyListener(new KeyAdapter() {
        @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String textoBuscado = buscarLibroField.getText();
                        for (int i = 0; i < libroBox.getItemCount(); i++) {
                            String item = ((Libro) libroBox.getItemAt(i)).getCodigoBarras();
                            if (item.contains(textoBuscado)) { 
                                libroBox.setSelectedIndex(i);
                                break;
                            }
                        }
                    } else if(buscarLibroField.getText().isBlank()|| e.getKeyCode() == KeyEvent.VK_ENTER) {
                        
                    }
                    
                }
            });
        centerPanel.add(buscarLibroField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        libroLbl = new PatLabel("Libro:");
        libroLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
        centerPanel.add(libroLbl, gbc);
        
        gbc.gridx = 3;
        if(!this.parentFrame.gestorAlquileres.gestorLibros.LibroList.isEmpty()){
            libroMap = new HashMap<>();
            for(Libro l: this.parentFrame.gestorAlquileres.gestorLibros.LibroList){
                libroMap.put(l.getIdLibro(), l);
            }
            libroBox = new JComboBox<>(libroMap.values().toArray(new Libro[0]));
            centerPanel.add(libroBox, gbc);

            libroBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Libro libroSeleccionado = (Libro) libroBox.getSelectedItem();
                    
                    if (libroSeleccionado != null) {
                        numCopiasLbl.setText("Número de copias: " + libroSeleccionado.getNumeroEjemplares());
                        codISBNLbl.setText("ISBN: " + libroSeleccionado.getCodigoISBN());
                        codBarrasLbl.setText("CB: " + libroSeleccionado.getCodigoBarras());
                    }
            
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
            });
        }

        
        gbc.gridx = 2;
        gbc.gridy = 2;
        guardarBtn = new BiblioButton("Guardar", Color.GREEN, Color.WHITE);
        centerPanel.add(guardarBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        numCopiasLbl = new PatLabel("Número de copias: " + ((Libro) libroBox.getSelectedItem()).getNumeroEjemplares());
        numCopiasLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
        centerPanel.add(numCopiasLbl, gbc);

        gbc.gridy = 3;
        codISBNLbl = new PatLabel("ISBN: " + ((Libro) libroBox.getSelectedItem()).getCodigoISBN());
        numCopiasLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_LEFT);
        centerPanel.add(codISBNLbl, gbc);

        gbc.gridy = 4;
        codBarrasLbl = new PatLabel("CB: " + ((Libro) libroBox.getSelectedItem()).getCodigoBarras());
        numCopiasLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_LEFT);
        centerPanel.add(codBarrasLbl, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        if(alquiler != null){
            eliminarBtn = new BiblioButton("Eliminar", Color.BLACK, Color.WHITE);
            eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            centerPanel.add(eliminarBtn, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            // Implementar luego
            biblioLbl = new PatLabel("Bibliotecario: ");
            biblioLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
            centerPanel.add(biblioLbl, gbc);
             
            gbc.gridx = 1;
            PatTextBox biblioField = new PatTextBox();
            biblioField.setEditable(false);
            biblioField.setText(alquiler.getBibliotecario().toString());
            centerPanel.add(biblioField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            fechaAlqLbl = new PatLabel("Fecha de alquiler: ");
            fechaAlqLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
            centerPanel.add(fechaAlqLbl, gbc);

            gbc.gridx = 1;
            PatTextBox fechaField = new PatTextBox();
            fechaField.setEditable(false);
            fechaField.setText(alquiler.getFechaAlquiler());
            centerPanel.add(fechaField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            marcarDevueltoBtn = new BiblioButton("Marcar Devuelto", Color.CYAN, Color.WHITE);
            marcarDevueltoBtn.addActionListener(e -> marcarDevueltoBtnClick());

            centerPanel.add(marcarDevueltoBtn, gbc);
            gbc.gridx = 2;
        }

        cancelarBtn = new BiblioButton("Cancelar", Color.RED, Color.WHITE);
        centerPanel.add(cancelarBtn, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }    
    

    private void limpiarCampos() {
        clienteBox.setSelectedItem(0);
        libroBox.setSelectedItem(0);
        buscarLibroField.setText("");
    }

}


