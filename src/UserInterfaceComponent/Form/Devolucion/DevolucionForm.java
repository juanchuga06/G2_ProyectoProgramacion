package UserInterfaceComponent.Form.Devolucion;

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
import UserInterfaceComponent.BibliotecaStyle;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;

public class DevolucionForm extends JPanel{
    private JLabel              clienteLbl, libroLbl, biblioLbl, numCopiasLbl, fechaAlqLbl, fechaDevLbl, codBarrasLbl, codISBNLbl;
    private JTextField          buscarLibroField;
    private Map<Integer, Libro>  libroMap;
    private Map<Integer, Cliente>clienteMap;
    private JComboBox<Libro>    libroBox;
    private JComboBox<Cliente>  clienteBox;
    private BiblioButton        cancelarBtn, eliminarBtn, marcarNoDevueltoBtn;
    private DevolucionPanel     parentFrame;
    private Image               backgroundImage;
    private Alquiler            devolucion;
    public Integer idDevolucion;

    public DevolucionForm(DevolucionPanel parentFrame, Alquiler alquiler, Integer idAlquiler) {
        this.parentFrame = parentFrame;
        this.devolucion = alquiler;
        
        if(idAlquiler != null)
            this.idDevolucion = idAlquiler;
        else
            return;

        initializeComponents();
        cargarDevolucion();
        mostrarDevolucion();

        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        eliminarBtn.addActionListener(e -> eliminarBtnClick());
    }

    public DevolucionForm(DevolucionPanel parentFrame, Alquiler alquiler) {
        this.parentFrame = parentFrame;
        this.devolucion = alquiler;
        initializeComponents();
        
        cargarDevolucion();
        mostrarDevolucion();

        cancelarBtn.addActionListener(e -> cancelarBtnClick());
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        g.drawImage(backgroundImage, x, y, this);
    }

    
    private void eliminarBtnClick(){
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea eliminar ?", 
                "Eliminación", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (opcion == JOptionPane.YES_OPTION) {
                if (!this.parentFrame.gestorDevoluciones.eliminarDevolucion(devolucion.getIdAlquiler())) {
                    throw new Exception("Error al eliminar");
                } else {
                    JOptionPane.showMessageDialog(this, "Devolución eliminado.");
                    
                    this.parentFrame.recargarDevoluciones();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al eliminar...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   

    private void cancelarBtnClick() {
        try {
            mostrarDevolucion();
            this.parentFrame.recargarDevoluciones();
        } catch (Exception e) {}
    }

    private void marcarNoDevueltoBtnClick(){
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea marcar como no devuelto a este alquiler?", 
                "Devolución", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (opcion == JOptionPane.YES_OPTION) {
                if (!this.parentFrame.gestorDevoluciones.marcarNoDevuelto(devolucion)) {
                    throw new Exception("Error al marcar como no devuelto");
                } else {
                    JOptionPane.showMessageDialog(this, "Devolucion " + idDevolucion + " marcado como devuelto el " + LocalDate.now().toString());
                    this.parentFrame.recargarDevoluciones();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al marcar como no devuelto...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDevolucion(){
        if(devolucion != null && idDevolucion!= null){
        try{
            devolucion = this.parentFrame.gestorDevoluciones.DevolucionList.get(idDevolucion);
        }
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(parentFrame, "Error al cargar Devolucion...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void mostrarDevolucion(){
        try{
            if(devolucion != null){
                clienteBox.setSelectedItem(clienteMap.get(devolucion.getCliente().getIdPersona()));
                libroBox.setSelectedItem(libroMap.get(devolucion.getLibro().getIdLibro()));
            }
            else{
                limpiarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al mostrar devolucion...!", "Error", JOptionPane.ERROR_MESSAGE);
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
        if(devolucion != null)
            lblTitle = new JLabel("Devolucion:  " + devolucion.getLibro().getTitulo());
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
        if(!this.parentFrame.gestorDevoluciones.gestorClientes.ClienteList.isEmpty()){
            clienteMap = new HashMap<>();
            for(Cliente c: this.parentFrame.gestorDevoluciones.gestorClientes.ClienteList){
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
        gbc.gridy = 1;
        libroLbl = new PatLabel("Libro:");
        libroLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
        centerPanel.add(libroLbl, gbc);
        
        gbc.gridx = 3;
        if(!this.parentFrame.gestorDevoluciones.gestorLibros.LibroList.isEmpty()){
            libroMap = new HashMap<>();
            for(Libro l: this.parentFrame.gestorDevoluciones.gestorLibros.LibroList){
                libroMap.put(l.getIdLibro(), l);
            }
            libroBox = new JComboBox<>(libroMap.values().toArray(new Libro[0]));
            libroBox.setEditable(false);
            centerPanel.add(libroBox, gbc);

            libroBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Libro libroSeleccionado = (Libro) libroBox.getSelectedItem();
                    
                    if (libroSeleccionado != null) {
                        numCopiasLbl.setText("Núm. copias disp.: " + libroSeleccionado.getNumeroEjemplares());
                        codISBNLbl.setText("ISBN: " + libroSeleccionado.getCodigoISBN());
                        codBarrasLbl.setText("CB: " + libroSeleccionado.getCodigoBarras());
                    }
            
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
            });
        }

        

        gbc.gridx = 3;
        gbc.gridy = 2;
        numCopiasLbl = new PatLabel("Núm. copias disp.: " + ((Libro) libroBox.getSelectedItem()).getNumeroEjemplares());
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

        gbc.gridy = 5;
        gbc.gridx = 3;
        if(devolucion != null){
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
            biblioField.setText(devolucion.getBibliotecario().toString());
            centerPanel.add(biblioField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            fechaAlqLbl = new PatLabel("Fecha de alquiler: ");
            fechaAlqLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
            centerPanel.add(fechaAlqLbl, gbc);

            gbc.gridx = 1;
            PatTextBox fechaField = new PatTextBox();
            fechaField.setEditable(false);
            fechaField.setText(devolucion.getFechaAlquiler());
            centerPanel.add(fechaField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            fechaDevLbl = new PatLabel("Fecha de Devolucion: ");
            fechaDevLbl.setHorizontalAlignment(BibliotecaStyle.ALIGNMENT_RIGHT);
            centerPanel.add(fechaDevLbl, gbc);

            gbc.gridx = 1;
            PatTextBox fechaDevField = new PatTextBox();
            fechaDevField.setEditable(false);
            fechaDevField.setText(devolucion.getFechaDevolucion().toString());
            centerPanel.add(fechaDevField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            marcarNoDevueltoBtn = new BiblioButton("Marcar No Devuelto", Color.CYAN, Color.WHITE);
            marcarNoDevueltoBtn.addActionListener(e -> marcarNoDevueltoBtnClick());

            centerPanel.add(marcarNoDevueltoBtn, gbc);
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


