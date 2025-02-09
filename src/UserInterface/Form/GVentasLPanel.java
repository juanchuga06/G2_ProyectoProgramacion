package UserInterface.Form;

import DataAccessComponent.DAO.LibroDAO;
import DataAccessComponent.DTO.LibroDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GVentasLPanel extends JPanel {
    public GVentasLPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 240));

        // Panel superior para el icono y el título
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(220, 230, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Espaciado

        // Cargar la imagen del icono
        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterface/Resource/MRBOOKLG.PNG"));
        Image image = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);  // Redimensionar la imagen
        ImageIcon resizedIcon = new ImageIcon(image);

        // Crear un JLabel con la imagen
        JLabel lblIcon = new JLabel(resizedIcon);
        lblIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));  // Espaciado a la derecha del icono
        topPanel.add(lblIcon, BorderLayout.WEST);

        // Título del panel
        JLabel lblTitle = new JLabel("Gestión de Ventas de Libros", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));  // Espaciado a la izquierda del título
        topPanel.add(lblTitle, BorderLayout.CENTER);

        // Agregar el panel superior al panel principal
        add(topPanel, BorderLayout.NORTH);

        // Panel central para la tabla
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(220, 230, 240));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));  // Espaciado

        // Crear la tabla y el modelo de tabla
        String[] columnNames = {"Título", "Copias", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Obtener los libros de la base de datos
        try {
            LibroDAO libroDAO = new LibroDAO();
            List<LibroDTO> libros = libroDAO.readAll();

            // Llenar la tabla con los datos de los libros
            for (LibroDTO libro : libros) {
                Object[] row = {
                    libro.getTitulo(),
                    libro.getNumeroEjemplares(),
                    libro.getEstado()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Crear la tabla con el modelo
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);  // Altura de las filas
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));  // Estilo del encabezado

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel central al panel principal
        add(centerPanel, BorderLayout.CENTER);
    }
}