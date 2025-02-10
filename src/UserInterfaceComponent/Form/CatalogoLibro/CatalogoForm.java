package UserInterfaceComponent.Form.CatalogoLibro;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import BusinessLogicComponent.entities.gestores.GestorCatalogoLibro;
import BusinessLogicComponent.entities.Libros.Autor;
import BusinessLogicComponent.entities.Libros.Editorial;
import BusinessLogicComponent.entities.Libros.GeneroLibro;
import java.awt.*;



public class CatalogoForm extends JPanel {
    private JButton nuevoBtn, guardarBtn, eliminarBtn, cancelarBtn;
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private int modo; // 1 = Autores, 2 = Editoriales, 3 = Géneros
    private GestorCatalogoLibro gestorCatalogo;
    private JPanel formPanel;

    public CatalogoForm(GestorCatalogoLibro gestorCatalogo) {
        this.gestorCatalogo = gestorCatalogo;
        setLayout(new BorderLayout());
        formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        
        // Panel de botones a la izquierda
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 5, 5));
        
        nuevoBtn = new JButton("(+) Nuevo");
        guardarBtn = new JButton("Guardar");
        eliminarBtn = new JButton("Eliminar");
        cancelarBtn = new JButton("Cancelar");

        nuevoBtn.setPreferredSize(new Dimension(200, 5));
        nuevoBtn.setFont(new Font("Arial", Font.BOLD, 14));
        nuevoBtn.setBackground(Color.CYAN);
        nuevoBtn.setForeground(Color.BLACK);
        nuevoBtn.setFocusPainted(false);
        nuevoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        guardarBtn.setPreferredSize(new Dimension(200, 5));
        guardarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        guardarBtn.setBackground(Color.GREEN);
        guardarBtn.setForeground(Color.WHITE);
        guardarBtn.setFocusPainted(false);
        guardarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        eliminarBtn.setPreferredSize(new Dimension(200, 5));
        eliminarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        eliminarBtn.setBackground(Color.BLACK);
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.setFocusPainted(false);
        eliminarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelarBtn.setPreferredSize(new Dimension(200, 5));
        cancelarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarBtn.setBackground(Color.RED);
        cancelarBtn.setForeground(Color.WHITE);
        cancelarBtn.setFocusPainted(false);
        cancelarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        nuevoBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        guardarBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eliminarBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelarBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelBotones.add(nuevoBtn);
        panelBotones.add(guardarBtn);
        panelBotones.add(eliminarBtn);
        panelBotones.add(cancelarBtn);
        
        add(panelBotones, BorderLayout.WEST);
        
        // Tabla de datos a la derecha
           // Configurar panel principal
        formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        add(formPanel);
   
           // Crear tabla y scroll
        modeloTabla = new DefaultTableModel();
        tablaDatos = new JTable(modeloTabla);
        tablaDatos.setFillsViewportHeight(true);
   
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        scrollPane.setPreferredSize(new Dimension(400, 200));
   
           // Agregar tabla al panel
        formPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.revalidate();
        scrollPane.repaint();
   
        setVisible(true);
        
        // Eventos de botones
        nuevoBtn.addActionListener(e -> nuevoElemento());
        guardarBtn.addActionListener(e -> guardarElemento());
        eliminarBtn.addActionListener(e -> eliminarElemento());
        cancelarBtn.addActionListener(e -> cancelarAccion());
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
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (Autor autor : gestorCatalogo.AutorList) {
                    modeloTabla.addRow(new Object[]{autor.getIdAutor(), autor.getNombre()});
                }
                break;
            case 2: // Editoriales
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (Editorial editorial : gestorCatalogo.EditorialList) {
                    modeloTabla.addRow(new Object[]{editorial.getIdEditorial(), editorial.getNombre()});
                }
                break;
            case 3: // Géneros
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (GeneroLibro genero : gestorCatalogo.GeneroLibroList) {
                    modeloTabla.addRow(new Object[]{genero.getIdGeneroLibro(), genero.getNombre()});
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
                    System.out.println("Autor registrado: " + nombre);
                    break;
                case 2:
                    gestorCatalogo.registrarEditorial(new Editorial(nombre));
                    System.out.println("Editorial registrada: " + nombre);
                    break;
                case 3:
                    gestorCatalogo.registrarGeneroLibro(new GeneroLibro(nombre));
                    System.out.println("Género registrado: " + nombre);
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
