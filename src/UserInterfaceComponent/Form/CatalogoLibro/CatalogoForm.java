package UserInterfaceComponent.Form.CatalogoLibro;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import UserInterfaceComponent.BibliotecaStyle;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import BusinessLogicComponent.entities.Gestores.GestorCatalogoLibro;
import BusinessLogicComponent.entities.Libros.Autor;
import BusinessLogicComponent.entities.Libros.Editorial;
import BusinessLogicComponent.entities.Libros.GeneroLibro;
import java.awt.*;



public class CatalogoForm extends JPanel {
    private JButton nuevoBtn, guardarBtn, eliminarBtn, cancelarBtn;
    private JLabel lblTitle;
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private int modo; // 1 = Autores, 2 = Editoriales, 3 = Géneros
    private GestorCatalogoLibro gestorCatalogo;
    private CatalogoPanel parentFrame;

    public CatalogoForm(CatalogoPanel parentFrame) {
        this.parentFrame = parentFrame;
        this.lblTitle = new JLabel();
        this.gestorCatalogo = this.parentFrame.gestorCatalogoLibro;
        initializeComponents();
    }

    private void initializeComponents(){
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 240));
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nuevoBtn = new BiblioButton("Nuevo", Color.CYAN, Color.BLACK);
        nuevoBtn.setSize(new Dimension(200, 30));
        guardarBtn = new BiblioButton("Guardar", Color.GREEN, Color.WHITE);
        guardarBtn.setSize(new Dimension(200, 30));
        eliminarBtn = new BiblioButton("Eliminar", Color.BLACK, Color.WHITE);
        eliminarBtn.setSize(new Dimension(200, 30));
        cancelarBtn = new BiblioButton("Cancelar", Color.RED, Color.WHITE);
        cancelarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarBtn.setSize(new Dimension(200, 30));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(nuevoBtn, gbc);

        gbc.gridy++;
        centerPanel.add(guardarBtn, gbc);

        gbc.gridy++;
        centerPanel.add(eliminarBtn, gbc);

        gbc.gridy++;
        centerPanel.add(cancelarBtn, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 5;
           // Crear tabla y scroll
        modeloTabla = new DefaultTableModel();
        cargarDatos(1);
        tablaDatos = new JTable(modeloTabla);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
        renderer.setBackground(new Color(230, 240, 250)); // Color de fondo
        renderer.setForeground(new Color(50, 50, 50)); // Color del texto
        tablaDatos.setDefaultRenderer(Object.class, renderer);

        JTableHeader header = tablaDatos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14)); // Cambiar la fuente
        header.setBackground(new Color(100, 150, 200)); // Color de fondo
        header.setForeground(Color.WHITE); // Color del texto

        tablaDatos.setCursor(BibliotecaStyle.CURSOR_HAND);
        tablaDatos.setFillsViewportHeight(true);
   
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        
           // Agregar tabla al panel
        centerPanel.add(scrollPane, gbc);
        scrollPane.revalidate();
        scrollPane.repaint();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblTitle, gbc);
   
        setVisible(true);
        
        // Eventos de botones
        nuevoBtn.addActionListener(e -> nuevoElemento());
        guardarBtn.addActionListener(e -> guardarElemento());
        eliminarBtn.addActionListener(e -> eliminarElemento());
        cancelarBtn.addActionListener(e -> cancelarAccion());

        add(centerPanel, BorderLayout.CENTER);
    }
    
    public void cargarDatos(int modo) {
        this.modo = modo;
        
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);
        
        try {
            gestorCatalogo.cargarCatalogoLibro(modo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        switch (modo) {
            case 1: // Autores
                modeloTabla.setColumnIdentifiers(new String[]{"Nombre del autor"});
                for (Autor autor : gestorCatalogo.AutorList) {
                    modeloTabla.addRow(new Object[]{autor.getNombre()});
                    lblTitle.setText("Se ha seleccionado el modo Autores");
                }
                break;
            case 2: // Editoriales
                modeloTabla.setColumnIdentifiers(new String[]{"Nombre del editorial"});
                for (Editorial editorial : gestorCatalogo.EditorialList) {
                    modeloTabla.addRow(new Object[]{editorial.getNombre()});
                    lblTitle.setText("Se ha seleccionado el modo Editoriales");
                }
                break;
            case 3: // Géneros
                modeloTabla.setColumnIdentifiers(new String[]{"Nombre del genero de Libro"});
                for (GeneroLibro genero : gestorCatalogo.GeneroLibroList) {
                    modeloTabla.addRow(new Object[]{genero.getNombre()});
                    lblTitle.setText("Se ha seleccionado el modo Genero de libro");
                }
                break;
        }
    }
    
    private void nuevoElemento() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            switch (modo) {
                case 1:
                    gestorCatalogo.registrarAutor(new Autor(nombre));
                    break;
                case 2:
                    gestorCatalogo.registrarEditorial(new Editorial(nombre));
                    break;
                case 3:
                    gestorCatalogo.registrarGeneroLibro(new GeneroLibro(nombre));
                    break;
            }
            cargarDatos(modo); // Asegúrate de que esto actualice la tabla
        }
    }

    
    private void guardarElemento() {
        int filaSeleccionada = tablaDatos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", modeloTabla.getValueAt(filaSeleccionada, 1));
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                switch (modo) {
                    case 1: gestorCatalogo.actualizarAutor(new Autor(id, nuevoNombre)); break;
                    case 2: gestorCatalogo.actualizarEditorial(new Editorial(id, nuevoNombre)); break;
                    case 3: gestorCatalogo.actualizarGeneroLibro(new GeneroLibro(id, nuevoNombre)); break;
                }
                cargarDatos(modo);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un elemento para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarElemento() {
        int filaSeleccionada = tablaDatos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este elemento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = false;
                try {
                    switch (modo) {
                        case 1: eliminado = gestorCatalogo.eliminarAutor(id); break;
                        case 2: eliminado = gestorCatalogo.eliminarEditorial(id); break;
                        case 3: eliminado = gestorCatalogo.eliminarGeneroLibro(id); break;
                    }
                    if (!eliminado) {
                        JOptionPane.showMessageDialog(this, "Existen registros que utilizan ese parámetro, no se pudo eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        cargarDatos(modo);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un elemento para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cancelarAccion() {
        cargarDatos(modo);
    }
   
    
}
