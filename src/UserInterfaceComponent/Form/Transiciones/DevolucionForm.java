package UserInterfaceComponent.Form.Transiciones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BusinessLogicComponent.entities.Libros.Libro;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.BiblioComboBox;
import UserInterfaceComponent.CustomerControl.PatLabel;
import UserInterfaceComponent.CustomerControl.PatTextBox;
import UserInterfaceComponent.Form.Libros.PanelLibros;

public class DevolucionForm extends JFrame {
    private JTextField          tituloField, numeroEdField, numeroEjemField,
                                fechaPubField, precioField, codigoBarrasField, codigoISBNField;
    private BiblioComboBox      autorBox, generoLibBox, editorialBox;
    private BiblioButton        guardarBtn, cancelarBtn, eliminarBtn, cambiarPortBtn;
    private PatLabel            tituloLabel, numeroEdLabel, numeroEjempLabel,
                                fechaPubLabel, precioLabel, generoLibLabel, editorialLabel,
                                autorLabel, codigoBarrasLable, codigoISBNLbl;
    private PanelLibros         parentFrame;
    private Image               backgroundImage;
    private Libro               libro = null;
    public Integer idLibro;

    public DevolucionForm(PanelLibros parentFrame, Libro libro, Integer idLibro) {
        this.parentFrame = parentFrame;
        this.libro = libro;

        if(idLibro != null)
            this.idLibro = idLibro;
        else
            this.idLibro = 0;

        initializeComponents();
        cargarLibro();
        mostrarLibro();

        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        eliminarBtn.addActionListener(e -> eliminarBtnClick());
        cambiarPortBtn.addActionListener(e -> cambiarPortBtnClick());
    }

    public DevolucionForm(PanelLibros parentFrame, Libro libro) {
        this.parentFrame = parentFrame;
        this.libro = libro;
        initializeComponents();
        
        cargarLibro();
        mostrarLibro();

        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        cambiarPortBtn.addActionListener(e -> cambiarPortBtnClick());
    }

    public DevolucionForm(PanelDevolucion panelDevolucion, Libro l, int id) {
        //TODO Auto-generated constructor stub
    }


    private void guardarBtnClick() {
        if (tituloField.getText().isEmpty() || numeroEdField.getText().isEmpty() || numeroEjemField.getText().isEmpty() || 
            fechaPubField.getText().isEmpty() || precioField.getText().isEmpty() || codigoBarrasField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean libroNull = (libro == null);
        String confirmacion = libroNull ? "AGREGAR" : "ACTUALIZAR";
        
        try {
            int opcion = JOptionPane.showConfirmDialog(
                parentFrame, 
                "¿Seguro que desea " + confirmacion + "?", 
                "Confirmacion", 
                JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                if (libroNull) {
                    libro = new Libro(); 
            // Si el cliente es nuevo, se debe crear un nuevo cliente para añadirle valores
                    libro.setTitulo(tituloField.getText());
                    libro.setNumeroEdicion(Integer.parseInt(numeroEdField.getText()));
                    libro.setNumeroEjemplares(Integer.parseInt(numeroEjemField.getText()));
                    libro.setFechaPublicacion(fechaPubField.getText());
                    libro.setPrecio(Double.parseDouble(precioField.getText()));
                    libro.setCodigoBarras(codigoBarrasField.getText());
                    libro.setCodigoISBN(codigoISBNField.getText());
                    libro.setAutor(this.parentFrame.gestorLibros.AutorList.get(autorBox.getSelectedIndex()));
                    libro.setEditorial(this.parentFrame.gestorLibros.EditorialList.get(editorialBox.getSelectedIndex()));
                    libro.setGeneroLibro(this.parentFrame.gestorLibros.GeneroLibroList.get(generoLibBox.getSelectedIndex()));
                    this.parentFrame.gestorLibros.registrarLibro(libro);
                }
                else {
                    libro.setTitulo(tituloField.getText());
                    libro.setNumeroEdicion(Integer.parseInt(numeroEdField.getText()));
                    libro.setNumeroEjemplares(Integer.parseInt(numeroEjemField.getText()));
                    libro.setFechaPublicacion(fechaPubField.getText());
                    libro.setPrecio(Double.parseDouble(precioField.getText()));
                    libro.setCodigoBarras(codigoBarrasField.getText());
                    libro.setCodigoISBN(codigoISBNField.getText());
                    libro.setAutor(this.parentFrame.gestorLibros.AutorList.get(autorBox.getSelectedIndex()));
                    libro.setEditorial(this.parentFrame.gestorLibros.EditorialList.get(editorialBox.getSelectedIndex()));
                    libro.setGeneroLibro(this.parentFrame.gestorLibros.GeneroLibroList.get(generoLibBox.getSelectedIndex()));
                    this.parentFrame.gestorLibros.actualizarLibro(libro);
                }

                JOptionPane.showMessageDialog(parentFrame, "Libro " + confirmacion + " con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al guardar...!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        cargarLibro();
        mostrarLibro();

        this.parentFrame.recargarLibros();
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
                if (!this.parentFrame.gestorLibros.eliminarLibro(libro.getIdLibro())) {
                    throw new Exception("Error al eliminar");
                } else {
                    JOptionPane.showMessageDialog(this, "Libro eliminado.");
                    
                    this.parentFrame.recargarLibros();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(parentFrame, "Error al eliminar...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   

    private void cancelarBtnClick() {
        try {
            if(libro == null)
                cargarLibro();
            mostrarLibro();
            this.parentFrame.recargarLibros();
        } catch (Exception e) {}
    }

    private void cambiarPortBtnClick(){
        
    }


    private void cargarLibro(){
        if(libro != null && libro.getIdLibro() != null){
        try{
            libro = this.parentFrame.gestorLibros.LibroList.get(idLibro);
        }
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(parentFrame, "Error al cargar libro...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void mostrarLibro(){
        // Si el cliente es nuevo, se muestran los campos vacíos
        // Si no, se muestran los campos existentes del cliente
        try{
            if(libro != null){
                tituloField.setText(libro.getTitulo());
                numeroEdField.setText(libro.getNumeroEdicion().toString());
                numeroEjemField.setText(libro.getNumeroEjemplares().toString());
                fechaPubField.setText(libro.getFechaPublicacion());
                precioField.setText(libro.getPrecio().toString());
                codigoBarrasField.setText(libro.getCodigoBarras());
                codigoISBNField.setText(libro.getCodigoISBN());
                autorBox.setSelectedIndex(libro.getAutor().getIdAutor() - 1);
                editorialBox.setSelectedIndex(libro.getEditorial().getIdEditorial() - 1);
                generoLibBox.setSelectedIndex(libro.getGeneroLibro().getIdGeneroLibro() - 1);
            }
            else{
                limpiarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error al mostrar libro...!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeComponents(){
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 240));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        gbc.weightx = 1.0;


        JLabel lblTitle;
        if(libro != null)
            lblTitle = new JLabel(libro.getTitulo());
        else 
            lblTitle = new JLabel("Nuevo Libro");
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setHorizontalAlignment(SwingConstants.LEADING);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        tituloLabel = new PatLabel("Titulo:");
        centerPanel.add(tituloLabel, gbc);

        gbc.gridx = 2;
        tituloField = new PatTextBox();
        tituloField.setToolTipText("Ingresa el titulo del libro");
        centerPanel.add(tituloField, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 1;
        numeroEdLabel = new PatLabel("Numero de Edicion:");
        centerPanel.add(numeroEdLabel, gbc);

        gbc.gridx = 2;
        numeroEdField = new PatTextBox();
        numeroEdField.setToolTipText("Ingresa el numero de edicion");
        centerPanel.add(numeroEdField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        numeroEjempLabel = new PatLabel("Numero de ejemplares:");
        centerPanel.add(numeroEjempLabel, gbc);

        gbc.gridx = 2;
        numeroEjemField = new PatTextBox();
        numeroEjemField.setToolTipText("Ingresa el numero de ejemplares");
        centerPanel.add(numeroEjemField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        fechaPubLabel = new PatLabel("Fecha de publicacion:");
        centerPanel.add(fechaPubLabel, gbc);

        gbc.gridx = 2;
        fechaPubField = new PatTextBox();
        fechaPubField.setToolTipText("Ingresa al año de publicacion");
        centerPanel.add(fechaPubField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        precioLabel = new PatLabel("Precio por unidad:");
        centerPanel.add(precioLabel, gbc);

        gbc.gridx = 2;
        precioField = new PatTextBox();
        precioField.setToolTipText("Ingresa el precio por unidad");
        centerPanel.add(precioField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 3;
        codigoBarrasLable = new PatLabel("Codigo de barras:");
        centerPanel.add(codigoBarrasLable, gbc);

        gbc.gridx = 4;
        codigoBarrasField = new PatTextBox();
        codigoBarrasField.setToolTipText("Ingresa el codigo de barras");
        codigoBarrasField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Cuando el escáner envía "Enter"
                    String codigo = codigoBarrasField.getText().trim();
                    System.out.println("Código escaneado: " + codigo);
                }
            }
        });
        centerPanel.add(codigoBarrasField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 3;
        codigoISBNLbl = new PatLabel("Codigo ISBN:");
        centerPanel.add(codigoISBNLbl, gbc);

        gbc.gridx = 4;
        codigoISBNField = new PatTextBox();
        codigoISBNField.setToolTipText("Ingresa el codigo ISBN");
        centerPanel.add(codigoISBNField, gbc);


        gbc.gridx = 3;
        gbc.gridy = 3;
        autorLabel = new PatLabel("Autor:");
        centerPanel.add(autorLabel, gbc);

        String[] nombresAutor = new String[this.parentFrame.gestorLibros.AutorList.size()];
        for (int i = 0; i < nombresAutor.length; i++) {
            nombresAutor[i] = this.parentFrame.gestorLibros.AutorList.get(i).getNombre();
        }
        gbc.gridx = 4;
        autorBox = new BiblioComboBox(nombresAutor);
        centerPanel.add(autorBox,gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        editorialLabel = new PatLabel("Editorial:");
        centerPanel.add(editorialLabel, gbc);

        String[] nombresEditorial = new String[this.parentFrame.gestorLibros.EditorialList.size()];
        for (int i = 0; i < nombresEditorial.length; i++) {
            nombresEditorial[i] = this.parentFrame.gestorLibros.EditorialList.get(i).getNombre();
        }
        gbc.gridx = 4;
        editorialBox = new BiblioComboBox(nombresEditorial);
        centerPanel.add(editorialBox,gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        generoLibLabel = new PatLabel("Genero de Libro:");
        centerPanel.add(generoLibLabel, gbc);

        String[] nombresGeneros = new String[this.parentFrame.gestorLibros.GeneroLibroList.size()];
        for (int i = 0; i < nombresGeneros.length; i++) {
            nombresGeneros[i] = this.parentFrame.gestorLibros.GeneroLibroList.get(i).getNombre();
        }
        gbc.gridx = 4;
        generoLibBox = new BiblioComboBox(nombresGeneros);
        centerPanel.add(generoLibBox,gbc);
        
        gbc.gridy = 6;
        gbc.gridx = 0;
        cambiarPortBtn = new BiblioButton("Cambiar portada", Color.BLACK, Color.WHITE);
        centerPanel.add(cambiarPortBtn, gbc);

        gbc.gridx = 1;
        guardarBtn = new BiblioButton("Guardar", Color.GREEN, Color.WHITE);
        centerPanel.add(guardarBtn, gbc);

        gbc.gridx = 2;
        if(libro != null){
            eliminarBtn = new BiblioButton("Eliminar", Color.BLACK, Color.WHITE);
            eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            centerPanel.add(eliminarBtn, gbc);
            gbc.gridx = 3;
        }
            cancelarBtn = new BiblioButton("Cancelar", Color.RED, Color.WHITE);
            centerPanel.add(cancelarBtn, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void limpiarCampos() {
        tituloField.setText("");
        numeroEdField.setText("");
        numeroEjemField.setText("");
        fechaPubField.setText("");
        precioField.setText("");
        codigoBarrasField.setText("");
        codigoISBNField.setText("");
        autorBox.setSelectedIndex(0);
        editorialBox.setSelectedIndex(0);
        generoLibBox.setSelectedIndex(0);
    }

}
