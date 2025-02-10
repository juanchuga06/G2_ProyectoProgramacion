package BusinessLogicComponent.entities.Gestores;

import java.util.ArrayList;
import java.util.List;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Transacciones.Alquiler;
import BusinessLogicComponent.entities.Transacciones.EstadoAlquiler;
import DataAccessComponent.DAO.AlquilerDAO;
import DataAccessComponent.DAO.EstadoAlquilerDAO;
import DataAccessComponent.DTO.AlquilerDTO;
import DataAccessComponent.DTO.EstadoAlquilerDTO;

public class GestorDevoluciones {
    public List<Alquiler> DevolucionList;
    public List<EstadoAlquiler> EstadoAlquilerList;
    // Tengo que encontrar una manera de determinar el bibliotecario que 
    // entra al sistema para asignarlo aquí.

    private BLFactory<AlquilerDTO> AlquilerBL;
    private BLFactory<EstadoAlquilerDTO> EstadoAlquilerBL;

    public GestorBibliotecarios gestorBibliotecarios;
    public GestorClientes gestorClientes;
    public GestorLibros gestorLibros;

    public GestorDevoluciones(){
        this.DevolucionList = new ArrayList<>();
        this.EstadoAlquilerList = new ArrayList<>();

        this.gestorBibliotecarios = new GestorBibliotecarios();
        this.gestorClientes = new GestorClientes();
        this.gestorLibros = new GestorLibros();

        this.AlquilerBL = new BLFactory<>(AlquilerDAO::new);
        this.EstadoAlquilerBL = new BLFactory<>(EstadoAlquilerDAO::new);

        cargarDevoluciones();

    }

    public void cargarDevoluciones(){
        this.DevolucionList.clear();
        this.EstadoAlquilerList.clear();

        Alquiler devolucionaux;
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
            for(AlquilerDTO d: AlquilerBL.getAll()){
                if(d.getIdEstadoAlquiler() == 2){
                    devolucionaux = new Alquiler(d.getIdAlquiler(), d.getFechaAlquiler(), gestorLibros.getLibroByID(d.getIdLibro()), d.getFechaDevolucion(),
                                               gestorClientes.getClienteByID(d.getIdCliente()), gestorBibliotecarios.getBibliotecarioByID(d.getIdBibliotecario()),
                                               getEstAlquilerByID(2));
                    System.out.println(d.getIdLibro());
                    this.DevolucionList.add(devolucionaux);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al cargar las devoluciones");
        }
    }

    public boolean actualizarDevolucion(Alquiler alquiler){
        if(alquiler == null)
        return false;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), alquiler.getFechaDevolucion().toString(), alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 2));
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar el alquiler");
        }
        return false;
    }

    public boolean eliminarDevolucion(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            AlquilerBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar la devolucion");
        }
        return false;
    }
    
    public boolean marcarNoDevuelto(Alquiler alquiler){
        if(alquiler == null || alquiler.getEstadoAlquiler().getIdEstadoAlquiler() != 2)
            return false;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), null, alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 1));
            alquiler.getLibro().setNumeroEjemplares(alquiler.getLibro().getNumeroEjemplares() - 1);
            gestorLibros.actualizarLibro(alquiler.getLibro());
            return true;
        } catch (Exception e) {
            System.out.println("Error al marcar devuelto el alquiler");
        }
        return false;
    }

    public EstadoAlquiler getEstAlquilerByID(int id){
        EstadoAlquiler estaux = new EstadoAlquiler();
        for(EstadoAlquiler ea: this.EstadoAlquilerList){
            if(ea.getIdEstadoAlquiler().equals(id)){
                estaux = ea;
                break;
            }
        }
        return estaux;
    }


    public List<Alquiler> buscarDevolcionPorCodigoBarras(String codigoBarras){
        List<Alquiler> alquileresLibro = new ArrayList<>();
        for(Alquiler a: this.DevolucionList){
            if(a.getLibro().getCodigoBarras().equals(codigoBarras)){
                alquileresLibro.add(a);
            }
        }
        return alquileresLibro;
    }

}

