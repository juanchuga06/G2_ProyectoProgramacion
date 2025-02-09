package BusinessLogicComponent.entities.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Transacciones.Alquiler;
import BusinessLogicComponent.entities.Transacciones.EstadoAlquiler;
import DataAccessComponent.DAO.AlquilerDAO;
import DataAccessComponent.DAO.EstadoAlquilerDAO;
import DataAccessComponent.DTO.AlquilerDTO;
import DataAccessComponent.DTO.EstadoAlquilerDTO;

public class GestorAlquileres {
    public List<Alquiler> AlquilerList;
    public List<EstadoAlquiler> EstadoAlquilerList;

    // private Integer idBibliotecario;
    // Tengo que encontrar una manera de determinar el bibliotecario que 
    // entra al sistema para asignarlo aquí.

    private BLFactory<AlquilerDTO> AlquilerBL;
    private BLFactory<EstadoAlquilerDTO> EstadoAlquilerBL;

    public GestorBibliotecarios gestorBibliotecarios;
    public GestorClientes gestorClientes;
    public GestorLibros gestorLibros;

    public GestorAlquileres(){
        this.AlquilerList = new ArrayList<>();
        this.EstadoAlquilerList = new ArrayList<>();

        this.gestorBibliotecarios = new GestorBibliotecarios();
        this.gestorClientes = new GestorClientes();
        this.gestorLibros = new GestorLibros();

        this.AlquilerBL = new BLFactory<>(AlquilerDAO::new);
        this.EstadoAlquilerBL = new BLFactory<>(EstadoAlquilerDAO::new);

        cargarAlquileres();

    }

    public void cargarAlquileres(){
        this.AlquilerList.clear();
        this.EstadoAlquilerList.clear();

        Alquiler alquileraux;
        EstadoAlquiler estaux;
        try {
            for(EstadoAlquilerDTO e: EstadoAlquilerBL.getAll()){
                estaux = new EstadoAlquiler(e.getIdEstadoAlquiler(),e.getNombre());
                this.EstadoAlquilerList.add(estaux);
            }
            
        } catch (Exception e) {
            System.out.println("Error al cargar los EstadosAlquiler");
        }

        try {
            for(AlquilerDTO a: AlquilerBL.getAll()){
                if(a.getIdEstadoAlquiler() == 1){
                    alquileraux = new Alquiler(a.getIdAlquiler(), a.getFechaAlquiler(), gestorLibros.getLibroByID(a.getIdLibro()),
                                               gestorClientes.getClienteByID(a.getIdCliente()), gestorBibliotecarios.getBibliotecarioByID(a.getIdBibliotecario()),
                                               getEstAlquilerByID(1));
                    this.AlquilerList.add(alquileraux);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al cargar los Alquileres");
        }
    }

    public void registrarAlquiler(Alquiler alquiler){
        if(alquiler == null || alquiler.getLibro().getNumeroEjemplares() < 1){
            JOptionPane.showMessageDialog(null, "No existen copias suficientes del libro \n" + alquiler.getLibro().getTitulo(), "Error", JOptionPane.ERROR_MESSAGE);    
            return;
        }
        try{
            AlquilerBL.add(new AlquilerDTO(null, null, alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 1));
            alquiler.getLibro().setNumeroEjemplares(alquiler.getLibro().getNumeroEjemplares() - 1);
            gestorLibros.actualizarLibro(alquiler.getLibro());
        } catch (Exception e) {
            System.out.println("Error al registrar el alquiler");
        }
    }

    public void actualizarAlquiler(Alquiler alquiler){
        if(alquiler == null)
        return;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), null, alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 1));
            gestorLibros.actualizarLibro(alquiler.getLibro());
        } catch (Exception e) {
            System.out.println("Error al actualizar el alquiler");
        }
    }

    public boolean eliminarAlquiler(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            AlquilerBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el alquiler");
        }
        return false;
    }
    
    public void marcarDevuelto(Alquiler alquiler){
        if(alquiler == null || alquiler.getEstadoAlquiler().getIdEstadoAlquiler() != 1)
        return;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), LocalDate.now().toString(), alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 2));
            alquiler.getLibro().setNumeroEjemplares(alquiler.getLibro().getNumeroEjemplares() + 1);
            gestorLibros.actualizarLibro(alquiler.getLibro());
        } catch (Exception e) {
            System.out.println("Error al marcar devuelto el alquiler");
        }
    }

    public void marcarInvalido(Alquiler alquiler){
        if(alquiler == null)
        return;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), null, alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 3));
        } catch (Exception e) {
            System.out.println("Error al marcar devuelto el alquiler");
        }
    }

    public EstadoAlquiler getEstAlquilerByID(Integer id){
        EstadoAlquiler estaux = new EstadoAlquiler();
        for(EstadoAlquiler ea: this.EstadoAlquilerList){
            if(ea.getIdEstadoAlquiler().equals(id)){
                estaux = ea;
                break;
            }
        }
        return estaux;
    }

    public List<Alquiler> buscarAlquileresPorISBN(String codigoISBN){
        List<Alquiler> alquileresLibro = new ArrayList<>();
        for(Alquiler a: this.AlquilerList){
            if(a.getLibro().getCodigoISBN().equals(codigoISBN)){
                alquileresLibro.add(a);
            }
        }
        return alquileresLibro;
    }

    public List<Alquiler> buscarAlquileresPorCodigoBarras(String codigoBarras){
        List<Alquiler> alquileresLibro = new ArrayList<>();
        for(Alquiler a: this.AlquilerList){
            if(a.getLibro().getCodigoBarras().equals(codigoBarras)){
                alquileresLibro.add(a);
            }
        }
        return alquileresLibro;
    }

}
