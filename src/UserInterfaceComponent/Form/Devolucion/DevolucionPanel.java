package UserInterfaceComponent.Form.Devolucion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
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

import BusinessLogicComponent.entities.gestores.GestorDevoluciones;
import BusinessLogicComponent.entities.Transacciones.Alquiler;

import UserInterfaceComponent.CustomerControl.PatTextBox;
public class DevolucionPanel extends JFrame{
    private JPanel                 devolucionPanel, topPanel, headerPanel;
    private JButton                addAlquilerBtn;
    private JTextField             searchField;
    private JScrollPane            scrollPane;
    private JLabel                 titleLabel;
    private JLabel                 searchLbl;
    private ArrayList<DevolucionForm>devolucionForms;
    public GestorDevoluciones        gestorDevoluciones;

    public DevolucionPanel() {
        devolucionForms = new ArrayList<>();
        gestorDevoluciones = new GestorDevoluciones();

        setTitle("Gestión de Devoluciones");
        setSize(1300, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        inicializarTopPanel();

        scrollPane = new JScrollPane(devolucionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(topPanel, BorderLayout.NORTH);  
        add(scrollPane, BorderLayout.CENTER); 
        setVisible(true);

        mostrarDevoluciones();
    }
    
    public static void main(String[] args) {
        new DevolucionPanel();
    }

    private void mostrarDevoluciones() {
        devolucionPanel.removeAll();
        devolucionForms.clear();
        int id = 0;
        for (Alquiler d : gestorDevoluciones.DevolucionList) {
            DevolucionForm devolucionForm = new DevolucionForm(this, d, id);
            devolucionPanel.add(devolucionForm);
            devolucionForms.add(devolucionForm);
            id++;
        }
        devolucionPanel.revalidate();
        devolucionPanel.repaint();
    }

    protected void recargarDevoluciones() {
        searchField.setText("");
        gestorDevoluciones.cargarDevoluciones();
        mostrarDevoluciones();
    }


    private void inicializarTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        titleLabel = new JLabel("Gestionar Devoluciones", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ImageIcon icon = new ImageIcon(getClass().getResource("/UserInterfaceComponent/Resource/MRBOOKLG.png"));
        Image backgroundImage = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(backgroundImage);
        JLabel logoLabel = new JLabel(scaledIcon); 
        headerPanel.add(logoLabel);

        searchLbl = new JLabel("Buscar devolución por codigo de barras:");
        headerPanel.add(searchLbl);

        searchField = new PatTextBox();
        searchField.setToolTipText("Buscar devolución...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buscarAlquiler(searchField.getText());
                    } else if(searchField.getText().isBlank()|| e.getKeyCode() == KeyEvent.VK_ENTER) {
                        recargarDevoluciones();
                    }
                    
                }
            });
        headerPanel.add(searchField);
 
        addAlquilerBtn = new JButton();
        for (var al : addAlquilerBtn.getActionListeners()) {
            addAlquilerBtn.removeActionListener(al);
        }
        
        addAlquilerBtn.setText("Cancelar");
        addAlquilerBtn.addActionListener(e -> recargarDevoluciones());
        configurarTeclaEscape(true);
        addAlquilerBtn.setPreferredSize(new Dimension(200, 40));
        addAlquilerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addAlquilerBtn.setBackground(Color.BLACK);
        addAlquilerBtn.setForeground(Color.WHITE);
        addAlquilerBtn.setFocusPainted(false);
        addAlquilerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerPanel.add(addAlquilerBtn);


        topPanel.add(headerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        
        devolucionPanel = new JPanel();
        devolucionPanel.setLayout(new BoxLayout(devolucionPanel, BoxLayout.Y_AXIS));
    }

    private void buscarAlquiler(String criterio) {

        if (criterio.isBlank()) {
            recargarDevoluciones();
            return;
        }

        devolucionPanel.removeAll();

        for(Alquiler d: gestorDevoluciones.buscarDevolcionPorCodigoBarras(criterio)){
                devolucionPanel.add(new DevolucionForm(this, d, gestorDevoluciones.DevolucionList.indexOf(d)));
        }
        

        devolucionPanel.revalidate();
        devolucionPanel.repaint();
    }


    private void configurarTeclaEscape(Boolean enBusqueda) {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "accionEscape");
        actionMap.put("accionEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enBusqueda) {
                    recargarDevoluciones(); // Llamar a la función que necesites
                }
            }
        });
    }

}