package UserInterfaceComponent.Form.Alquiler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import BusinessLogicComponent.entities.gestores.GestorAlquileres;
import BusinessLogicComponent.entities.Transacciones.Alquiler;
import UserInterfaceComponent.CustomerControl.BiblioButton;
import UserInterfaceComponent.CustomerControl.PatTextBox;
import UserInterfaceComponent.Form.MainForm;
public class AlquilerPanel extends JFrame{
    private JPanel                 alquilerPanel, topPanel;
    private JButton                addAlquilerBtn;
    private JTextField             searchField;
    private JScrollPane            scrollPane;
    private JLabel                 titleLabel;
    private JLabel                 searchLbl;
    private ArrayList<AlquilerForm>alquilerForms;
    public GestorAlquileres        gestorAlquileres;
    private boolean buscando = false;

    public AlquilerPanel() {
        alquilerForms = new ArrayList<>();
        gestorAlquileres = new GestorAlquileres();

        setTitle("Gestión de Alquileres");
        setSize(1300, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        inicializarTopPanel();

        scrollPane = new JScrollPane(alquilerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(topPanel, BorderLayout.NORTH);  
        add(scrollPane, BorderLayout.CENTER); 
        setVisible(true);

        mostrarAlquileres();
    }
    
    public static void main(String[] args) {
        new AlquilerPanel();
    }

    private void mostrarAlquileres() {
        alquilerPanel.removeAll();
        alquilerForms.clear();
        int id = 0;
        for (Alquiler a : gestorAlquileres.AlquilerList) {
            AlquilerForm alquilerForm = new AlquilerForm(this, a, id);
            alquilerPanel.add(alquilerForm);
            alquilerForms.add(alquilerForm);
            id++;
        }
        alquilerPanel.revalidate();
        alquilerPanel.repaint();
    }

    protected void recargarAlquileres() {
        searchField.setText("");
        if(buscando)
           buscando = false;
        actualizarBoton(false);
        gestorAlquileres.cargarAlquileres();
        mostrarAlquileres();
    }

    private void agregarNuevoAlquiler() {
        AlquilerForm nuevoAlquiler = new AlquilerForm(this, null);
        alquilerForms.add(0, nuevoAlquiler);
        alquilerPanel.add(nuevoAlquiler, 0);
        alquilerPanel.revalidate();
        alquilerPanel.repaint();
    }

    private void inicializarTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        titleLabel = new JLabel("Gestionar Alquileres", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton volverBtn = new BiblioButton("Volver", Color.DARK_GRAY, Color.WHITE);
        volverBtn.setAlignmentX(CENTER_ALIGNMENT);
        headerPanel.add(volverBtn);

        volverBtn.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainForm mf = new MainForm();
                mf.setVisible(true);
                dispose();
            }
        });

        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon); 
        headerPanel.add(logoLabel);

        searchLbl = new JLabel("Buscar alquiler por codigo de barras:");
        headerPanel.add(searchLbl);

        searchField = new PatTextBox();
        searchField.setToolTipText("Buscar alquiler...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buscarAlquiler(searchField.getText());
                    } else if(searchField.getText().isBlank()|| e.getKeyCode() == KeyEvent.VK_ENTER) {
                        recargarAlquileres();
                    }
                    
                }
            });
        headerPanel.add(searchField);
 
        addAlquilerBtn = new JButton();
        actualizarBoton(buscando);
        headerPanel.add(addAlquilerBtn);

        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        
        alquilerPanel = new JPanel();
        alquilerPanel.setLayout(new BoxLayout(alquilerPanel, BoxLayout.Y_AXIS));
    }

    private void buscarAlquiler(String criterio) {

        if (criterio.isBlank()) {
            recargarAlquileres();
            return;
        }

        alquilerPanel.removeAll();

        for(Alquiler a: gestorAlquileres.buscarAlquileresPorCodigoBarras(criterio)){
                alquilerPanel.add(new AlquilerForm(this, a, gestorAlquileres.AlquilerList.indexOf(a)));
        }
        
        if (true) {
            this.buscando = true;
            actualizarBoton(buscando);
            topPanel.revalidate();
            topPanel.repaint();
        }

        alquilerPanel.revalidate();
        alquilerPanel.repaint();
    }

    private void actualizarBoton(boolean enBusqueda) {
        for (var al : addAlquilerBtn.getActionListeners()) {
            addAlquilerBtn.removeActionListener(al);
        }
        
        if (enBusqueda) {
            addAlquilerBtn.setText("Cancelar");
            addAlquilerBtn.addActionListener(e -> recargarAlquileres());
            configurarTeclaEscape(enBusqueda);
            
        } else {
            addAlquilerBtn.setText("Agregar Alquiler");
            addAlquilerBtn.addActionListener(e -> agregarNuevoAlquiler());
        }

        // Luego solo se customiza el boton resultante
        addAlquilerBtn.setPreferredSize(new Dimension(200, 40));
        addAlquilerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addAlquilerBtn.setBackground(Color.BLACK);
        addAlquilerBtn.setForeground(Color.WHITE);
        addAlquilerBtn.setFocusPainted(false);
        addAlquilerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    private void configurarTeclaEscape(Boolean enBusqueda) {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "accionEscape");
        actionMap.put("accionEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enBusqueda) {
                    recargarAlquileres(); // Llamar a la función que necesites
                }
            }
        });
    }

}