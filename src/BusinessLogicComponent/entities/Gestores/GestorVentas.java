package BusinessLogicComponent.entities.gestores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Transacciones.Venta;
import DataAccessComponent.DAO.VentaDAO;
import DataAccessComponent.DTO.FacturaDTO;
import DataAccessComponent.DTO.VentaDTO;

public class GestorVentas {
    public static final double DESCUENTO = 0.75;
    private static final String CARPETA_FACTURAS  = "\\out";

    // Agregar las constantes de contacto del negocio para insertarlas en la factura;
    public List<Venta> VentaList;
    
    private Integer idBibliotecario;
    private BLFactory<VentaDTO> VentaBL;
    private BLFactory<FacturaDTO> FacturaBL;
    private GestorBibliotecarios gestorBibliotecarios;
    private GestorClientes gestorClientes;
    private GestorLibros gestorLibros;

    public GestorVentas() {
        this.VentaList = new ArrayList<>();
        this.VentaBL = new BLFactory<>(VentaDAO::new);

        this.gestorBibliotecarios = new GestorBibliotecarios();
        this.gestorClientes = new GestorClientes();
        this.gestorLibros = new GestorLibros();
        cargarVentas();

    }

    public void cargarVentas(){
        this.VentaList.clear();

        Venta ventaux;

        try {
            for(VentaDTO v: VentaBL.getAll()){
                ventaux = new Venta(v.getIdLibro(), v.getCantidadLibros(), v.getTotalLibros().doubleValue(), v.getDescuento(), v.getTotalPagar().doubleValue(), 
                                    v.getFechaVenta(), gestorLibros.getLibroByID(v.getIdLibro()), gestorClientes.getClienteByID(v.getIdCliente()), 
                                    gestorBibliotecarios.getBibliotecarioByID(v.getIdBibliotecario()));
                this.VentaList.add(ventaux);
             }
            
            
        } catch (Exception e) {
            System.out.println("Error al cargar las ventas");
        }
    }

    public void registrarVenta(Venta venta){
        if(venta == null || venta.getLibro().getNumeroEjemplares() < 1){
            JOptionPane.showMessageDialog(null, "No existen copias suficientes del libro \n" + venta.getLibro().getTitulo(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(venta.getCantidadLibros() > venta.getLibro().getNumeroEjemplares()){
            JOptionPane.showMessageDialog(null, "Solo existen " + venta.getLibro().getNumeroEjemplares() + " del libro \n" + venta.getLibro().getTitulo(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        venta.setTotalLibros(venta.getLibro().getPrecio() * venta.getCantidadLibros());

        if(venta.getDescuento())
            venta.setTotalLibros(venta.getTotalLibros() * DESCUENTO);

        try{
            VentaBL.add(new VentaDTO(venta.getCantidadLibros(), new BigDecimal(venta.getTotalLibros()), venta.getDescuento(), new BigDecimal(venta.getTotalAPagar()),
                                     venta.getLibro().getIdLibro(), venta.getCliente().getIdPersona(), venta.getBibliotecario().getIdPersona()));
            gestorLibros.actualizarLibro(venta.getLibro());
            generarFactura(venta, false);
        } catch (Exception e) {
            System.out.println("Error al registrar la venta");
        }
    }


    public void actualizarVenta(Venta venta){
        if(venta == null || venta.getLibro().getNumeroEjemplares() < 1){
            JOptionPane.showMessageDialog(null, "No existen copias suficientes del libro \n" + venta.getLibro().getTitulo(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(venta.getCantidadLibros() > venta.getLibro().getNumeroEjemplares()){
            JOptionPane.showMessageDialog(null, "Solo existen " + venta.getLibro().getNumeroEjemplares() + " del libro \n" + venta.getLibro().getTitulo(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        venta.setTotalLibros(venta.getLibro().getPrecio() * venta.getCantidadLibros());

        if(venta.getDescuento())
            venta.setTotalLibros(venta.getTotalLibros() * DESCUENTO);

        try{
            VentaBL.upd(new VentaDTO(venta.getIdVenta(), venta.getCantidadLibros(), new BigDecimal(venta.getTotalLibros()), venta.getDescuento(), venta.getFechaVenta(), new BigDecimal(venta.getTotalAPagar()),
                                     venta.getLibro().getIdLibro(), venta.getCliente().getIdPersona(), venta.getBibliotecario().getIdPersona()));
            gestorLibros.actualizarLibro(venta.getLibro());
            generarFactura(venta, true);
        }  catch (Exception e) {
            System.out.println("Error al actualizar la venta");
        }
    }

    public boolean eliminarVenta(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            VentaBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar la venta");
        }
        return false;
    }

    public List<Venta> buscarAlquileresPorISBN(String codigoISBN){
        List<Venta> ventasLibro = new ArrayList<>();
        for(Venta v: this.VentaList){
            if(v.getLibro().getCodigoISBN().equals(codigoISBN)){
                ventasLibro.add(v);
            }
        }
        return ventasLibro;
    }

    public List<Venta> buscarAlquileresPorCodigoBarras(String codigoBarras){
        List<Venta> ventasLibro = new ArrayList<>();
        for(Venta v: this.VentaList){
            if(v.getLibro().getCodigoISBN().equals(codigoBarras)){
                ventasLibro.add(v);
            }
        }
        return ventasLibro;
    }

    public void generarFactura(Venta venta, boolean esActualizacion){
        if(venta.getIdVenta() == null || venta.getIdVenta() <= 0)
            return;

        File directorio = new File(CARPETA_FACTURAS);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea la carpeta y subcarpetas si no existen
        }

        String nombreArchivo = "factura_" + venta.getIdVenta() + ".txt";
        File archivo = new File(nombreArchivo);

        // Verifica si el archivo existe y solo lo sobrescribe si es una actualización
        if (archivo.exists() && !esActualizacion) {
            int contador = 1;
            while (archivo.exists()) {
                nombreArchivo = "factura_" + venta.getIdVenta() + "_" + contador + ".txt";
                archivo = new File(nombreArchivo);
                contador++;
            }
        }

        Double desc = 0.0;
        if(venta.getDescuento()){
            desc = venta.getTotalLibros() - venta.getTotalAPagar();
        }

        try (FileWriter writer = new FileWriter(archivo, false)) { // 'false' para sobrescribir si es actualización
            writer.write("----- FACTURA DE COMPRA -----\n");
            writer.write("-------- MR. BOOK -----------\n");
            writer.write("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("ID Venta: " + venta.getIdVenta() + "\n");
            writer.write("Cliente: " + venta.getCliente().getNombre() + " " + venta.getCliente().getApellido() + "\n");
            writer.write("Libro: " + venta.getLibro().getTitulo() + "\n");
            writer.write("Cantidad: " + venta.getCantidadLibros() + "\n");
            writer.write("Precio Unitario: $" + String.format("%.2f", venta.getLibro().getPrecio()) + "\n");
            writer.write("Subtotal: $" + String.format("%.2f", venta.getTotalLibros()) + "\n");
                        writer.write("Descuento: $" + String.format("%.2f", desc) + "\n");
            writer.write("Total: $" + String.format("%.2f",venta.getTotalAPagar()) + "\n");
            writer.write("----------------------------\n");

            System.out.println("Factura guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir la factura: " + e.getMessage());
        }

        // Implementar el guardado de la factura en la base de datos, debe ser distinto si la factura es una actualizacion
        // o es una nuevo factura
    }





}


