package BusinessLogicComponent.entities.gestores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Transacciones.Venta;
import DataAccessComponent.DAO.VentaDAO;
import DataAccessComponent.DTO.VentaDTO;

public class GestorVentas {
    public static final double DESCUENTO = 0.75;
    public List<Venta> VentaList;
    
    private BLFactory<VentaDTO> VentaBL;
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






}


