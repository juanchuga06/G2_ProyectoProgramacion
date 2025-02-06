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

    private GestorBibliotecarios gestorBibliotecarios;
    private GestorClientes gestorClientes;
    private GestorLibros gestorLibros;

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
                if(getEstAlquilerByID(d.getIdAlquiler()).getIdEstadoAlquiler() == 2){
                    devolucionaux = new Alquiler(d.getIdAlquiler(), d.getFechaAlquiler(), gestorLibros.getLibroByID(d.getIdLibro()),
                                               gestorClientes.getClienteByID(d.getIdCliente()), gestorBibliotecarios.getBibliotecarioByID(d.getIdBibliotecario()),
                                               getEstAlquilerByID(d.getIdAlquiler()));
                    this.DevolucionList.add(devolucionaux);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al cargar las devoluciones");
        }
    }

    public void actualizarDevolucion(Alquiler alquiler){
        if(alquiler == null)
        return;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), alquiler.getFechaDevolucion().toString(), alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 2));
        } catch (Exception e) {
            System.out.println("Error al actualizar el alquiler");
        }
    }

    public void eliminarDevolucion(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            AlquilerBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar la devolucion");
        }
    }
    
    public void marcarNoDevuelto(Alquiler alquiler){
        if(alquiler == null || alquiler.getEstadoAlquiler().getIdEstadoAlquiler() != 2)
        return;
        try{
            AlquilerBL.upd(new AlquilerDTO(alquiler.getIdAlquiler(), alquiler.getFechaAlquiler(), null, alquiler.getLibro().getIdLibro(), 
                                           alquiler.getCliente().getIdPersona(), alquiler.getBibliotecario().getIdPersona(), 1));
            alquiler.getLibro().setNumeroEjemplares(alquiler.getLibro().getNumeroEjemplares() - 1);
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
            alquiler.getLibro().setNumeroEjemplares(alquiler.getLibro().getNumeroEjemplares() - 1);
            gestorLibros.actualizarLibro(alquiler.getLibro());
        } catch (Exception e) {
            System.out.println("Error al marcar devuelto el alquiler");
        }
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

    public List<Alquiler> buscarDevolcionPorISBN(String codigoISBN){
        List<Alquiler> alquileresLibro = new ArrayList<>();
        for(Alquiler a: this.DevolucionList){
            if(a.getLibro().getCodigoISBN().equals(codigoISBN)){
                alquileresLibro.add(a);
            }
        }
        return alquileresLibro;
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

