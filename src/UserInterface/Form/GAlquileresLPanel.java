package UserInterface.Form;

import DataAccessComponent.DAO.AlquilerDAO;
import DataAccessComponent.DTO.AlquilerDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GAlquileresLPanel extends JPanel {
    private MainForm mainForm;  // Referencia al MainForm para manejar la navegación

    public GAlquileresLPanel(MainForm mainForm) {
        this.mainForm = mainForm;  // Inicializar la referencia al MainForm
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
        JLabel lblTitle = new JLabel("Gestión de Alquileres de Libros", SwingConstants.LEFT);
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
        String[] columnNames = {"ID Alquiler", "Libro", "Cliente", "Fecha Alquiler", "Fecha Devolución", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Obtener los alquileres de la base de datos
        try {
            AlquilerDAO alquilerDAO = new AlquilerDAO();
            List<AlquilerDTO> alquileres = alquilerDAO.readAll();

            // Llenar la tabla con los datos de los alquileres
            for (AlquilerDTO alquiler : alquileres) {
                Object[] row = {
                    alquiler.getIdAlquiler(),
                    alquiler.getIdLibro(),  // Aquí podrías obtener el título del libro usando LibroDAO
                    alquiler.getIdCliente(),  // Aquí podrías obtener el nombre del cliente usando ClienteDAO
                    alquiler.getFechaAlquiler(),
                    alquiler.getFechaDevolucion(),
                    alquiler.getEstado()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los alquileres: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        // Panel inferior para el botón de regresar
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // Alinear el botón a la derecha
        bottomPanel.setBackground(new Color(220, 230, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));  // Espaciado

        // Crear el botón de regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBackground(new Color(70, 130, 180));  // Color azul
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(e -> regresar());  // Acción al hacer clic en el botón

        // Agregar el botón al panel inferior
        bottomPanel.add(btnRegresar);

        // Agregar el panel inferior al panel principal
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Método para regresar al menú principal
    private void regresar() {
        if (mainForm != null) {
            mainForm.mostrarMenuPanel();  // Llama al método de MainForm para mostrar el menú principal
        }
    }
}