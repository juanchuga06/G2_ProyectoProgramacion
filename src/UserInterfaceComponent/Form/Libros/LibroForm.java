package UserInterfaceComponent.Form.Libros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Libros.Portada;
import BusinessLogicComponent.entities.Utilities.DoubleDocumentListener;
import BusinessLogicComponent.entities.Utilities.NumericDocumentListener;
import UserInterfaceComponent.CustomerControl.BiblioComboBox;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.PatTextBox;


// Los comentarios agregados son momentaneos, hay que eliminarlos después
// los comentarios son solo para que el código sea más entendible
public class LibroForm extends JPanel {
    private JTextField          tituloField, numeroEdField, numeroEjemField,
                                fechaPubField, precioField, codigoBarrasField, codigoISBNField;
    private BiblioComboBox      autorBox, generoLibBox, editorialBox;
    private BiblioButton        guardarBtn, cancelarBtn, eliminarBtn, cambiarPortBtn;
    private JLabel              PortadaLabel;
    private PanelLibros         parentFrame;
    private Image               backgroundImage;
    private Libro               libro = null;
    public Integer idLibro;

    public LibroForm(PanelLibros parentFrame, Libro libro, Integer idLibro) {
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

    public LibroForm(PanelLibros parentFrame, Libro libro) {
        this.parentFrame = parentFrame;
        this.libro = libro;
        initializeComponents();
        
        cargarLibro();
        mostrarLibro();

        guardarBtn.addActionListener(e -> guardarBtnClick());
        cancelarBtn.addActionListener(e -> cancelarBtnClick());
        cambiarPortBtn.addActionListener(e -> cambiarPortBtnClick());
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        g.drawImage(backgroundImage, x, y, this);
    }

    private void guardarBtnClick() {
        System.out.println("Codigo de Barras: " + libro.getCodigoBarras() + " Codigo ISBN: " + libro.getCodigoISBN());
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
        System.out.println("Codigo de Barras: " + libro.getCodigoBarras() + " Codigo ISBN: " + libro.getCodigoISBN());
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
        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes PNG", "png");
        fileChooser.setFileFilter(filtro);

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
        
            if (archivoSeleccionado.getName().toLowerCase().endsWith(".png")) {
                ImageIcon imagen = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                
                Image imagenEscalada = imagen.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                int opcion = JOptionPane.showConfirmDialog(parentFrame, "¿Seguro que desea actualizar la portada del libro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
    
                if (opcion == JOptionPane.YES_OPTION){
                    if(this.parentFrame.gestorLibros.obtenerPortada(idLibro) == null){
                        guardarBtnClick();
                        parentFrame.gestorLibros.crearPortada(new Portada(new ImageIcon(imagenEscalada), libro.getIdLibro()));
                        this.parentFrame.recargarLibros();
                    }else { 
                        guardarBtnClick();
                        parentFrame.gestorLibros.actualizarPortada(new Portada(parentFrame.gestorLibros.obtenerPortada(libro.getIdLibro()).getIdPortada(), new ImageIcon(imagenEscalada), libro.getIdLibro()));
                        this.parentFrame.recargarLibros();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten imágenes PNG.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
                
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
        gbc.gridwidth = 3;
        centerPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // Portada (colocada en la primera columna con un tamaño fijo)
        if(libro != null){
            if(this.parentFrame.gestorLibros.obtenerPortada(libro.getIdLibro()) != null ){
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridheight = 6; // Para que la imagen abarque varias filas sin afectar a otros elementos
                gbc.anchor = GridBagConstraints.NORTH; // Mantener en la parte superior
                PortadaLabel = new JLabel(this.parentFrame.gestorLibros.obtenerPortada(libro.getIdLibro()).getPortada());
                centerPanel.add(PortadaLabel, gbc);
                gbc.gridheight = 1;
            } // Restablecer valor predeterminado
        }
        // Primera columna de etiquetas
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(new JLabel("Título:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Número de Edición:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Número de Ejemplares:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Fecha de Publicación:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Precio:"), gbc);

        // Segunda columna de campos de texto
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tituloField = new PatTextBox();
        tituloField.setToolTipText("Ingresa el titulo del libro");
        centerPanel.add(tituloField, gbc);
        centerPanel.add(tituloField, gbc);

        gbc.gridy++;
        numeroEdField = new PatTextBox();
        numeroEdField.setToolTipText("Ingresa el numero de edicion");
        numeroEdField.getDocument().addDocumentListener(new NumericDocumentListener(numeroEdField));
        centerPanel.add(numeroEdField, gbc);

        gbc.gridy++;
        numeroEjemField = new PatTextBox();
        numeroEjemField.setToolTipText("Ingresa el numero de ejemplares");
        numeroEjemField.getDocument().addDocumentListener(new NumericDocumentListener(numeroEjemField));
        centerPanel.add(numeroEjemField, gbc);

        gbc.gridy++;
        fechaPubField = new PatTextBox();
        fechaPubField.setColumns(4);
        fechaPubField.setToolTipText("Ingresa al año de publicacion");
        fechaPubField.getDocument().addDocumentListener(new NumericDocumentListener(fechaPubField));
        centerPanel.add(fechaPubField, gbc);

        // Revisar:
        gbc.gridy++;
        precioField = new PatTextBox();
        precioField.setToolTipText("Ingresa el precio por unidad");
        precioField.getDocument().addDocumentListener(new DoubleDocumentListener(precioField));
        centerPanel.add(precioField, gbc);

        // Tercera columna de etiquetas
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(new JLabel("Código de Barras:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Código ISBN:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Autor:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Editorial:"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Género de Libro:"), gbc);

        // Cuarta columna de campos
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        codigoBarrasField = new PatTextBox();
        codigoBarrasField.setToolTipText("Ingresa el codigo de barras");
        codigoBarrasField.getDocument().addDocumentListener(new NumericDocumentListener(codigoBarrasField));
        centerPanel.add(codigoBarrasField, gbc);

        // Revisar
        gbc.gridy++;
        codigoISBNField = new PatTextBox();
        codigoISBNField.setToolTipText("Ingresa el codigo ISBN");
        codigoISBNField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTexto();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTexto();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTexto();
            }

            private void filtrarTexto() {
                SwingUtilities.invokeLater(() -> {
                    String texto = codigoISBNField.getText();
                    if (!texto.matches("[\\d-]*")) { 
                        codigoISBNField.setText(texto.replaceAll("[^\\d-]", ""));
                    }
                });
            }
        });
        centerPanel.add(codigoISBNField, gbc);

        gbc.gridy++;
        String[] nombresAutor = new String[this.parentFrame.gestorLibros.AutorList.size()];
        for (int i = 0; i < nombresAutor.length; i++) {
            nombresAutor[i] = this.parentFrame.gestorLibros.AutorList.get(i).getNombre();
        }
        gbc.gridx = 4;
        autorBox = new BiblioComboBox(nombresAutor);
        centerPanel.add(autorBox, gbc);

        gbc.gridy++;
        String[] nombresEditorial = new String[this.parentFrame.gestorLibros.EditorialList.size()];
        for (int i = 0; i < nombresEditorial.length; i++) {
            nombresEditorial[i] = this.parentFrame.gestorLibros.EditorialList.get(i).getNombre();
        }
        gbc.gridx = 4;
        editorialBox = new BiblioComboBox(nombresEditorial);
        centerPanel.add(editorialBox, gbc);

        gbc.gridy++;
        String[] nombresGeneros = new String[this.parentFrame.gestorLibros.GeneroLibroList.size()];
        for (int i = 0; i < nombresGeneros.length; i++) {
            nombresGeneros[i] = this.parentFrame.gestorLibros.GeneroLibroList.get(i).getNombre();
        }
        gbc.gridx = 4;
        generoLibBox = new BiblioComboBox(nombresGeneros);
        centerPanel.add(generoLibBox, gbc);

        // Botones en la parte inferior
        if(libro != null)
            gbc.gridy = 7;
        else
            gbc.gridy = 6;
        gbc.gridx = 0;
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        cambiarPortBtn = new BiblioButton("Cambiar portada", Color.BLACK, Color.WHITE);
        centerPanel.add(cambiarPortBtn, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        guardarBtn = new BiblioButton("Guardar", Color.GREEN, Color.WHITE);
        centerPanel.add(guardarBtn, gbc);

        gbc.gridx = 2;
        cancelarBtn = new BiblioButton("Cancelar", Color.RED, Color.WHITE);
        centerPanel.add(cancelarBtn, gbc);

        if (libro != null) {
            gbc.gridx = 3;
            eliminarBtn = new BiblioButton("Eliminar", Color.BLACK, Color.WHITE);
            centerPanel.add(eliminarBtn, gbc);
        }

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
