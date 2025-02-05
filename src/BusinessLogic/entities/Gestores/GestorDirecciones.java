package BusinessLogic.entities.Gestores;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.BLFactory;
import BusinessLogic.entities.Personas.Direccion;
import DataAccessComponent.DAO.DireccionDAO;
import DataAccessComponent.DTO.DireccionDTO;

public class GestorDirecciones {
    public GestorClientes gestorClientes;
    public List<Direccion> DireccionList;

     private BLFactory<DireccionDTO> DireccionBL;

    public GestorDirecciones(){
        this.DireccionList = new ArrayList<>();
        this.DireccionBL = new BLFactory<>(DireccionDAO::new);

        cargarDirecciones();
    }

    public void cargarDirecciones(){
        this.DireccionList.clear();
        Direccion direccionaux;

        try {
            for(DireccionDTO d: DireccionBL.getAll()){
                direccionaux = new Direccion(d.getIdDireccion(), d.getCallePrincipal(), d.getCalleSecundaria(), gestorClientes.getClienteByID(d.getIdCliente()));
                this.DireccionList.add(direccionaux);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las direcciones");
        }
    }

    public void registrarDireccion(Direccion direccion){
        if(direccion == null)
            return;
        try{
            DireccionBL.add(new DireccionDTO(direccion.getCallePrimaria(),direccion.getCalleSecundaria(),direccion.getCliente().getIdPersona(), null));
        } catch(Exception e){
            System.out.println("Error al registrar la direccion");
        }
    }

    public void actualizarDireccion(Direccion direccion){
        if(direccion == null)
        return;
        try{
            DireccionBL.add(new DireccionDTO(direccion.getIdDireccion(), direccion.getCallePrimaria(), direccion.getCalleSecundaria(), direccion.getCliente().getIdPersona(), null));
        } catch (Exception e){
            System.out.println("Error al actualizar la direccion");
        }
    }

    public void eliminarDireccion(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            DireccionBL.del(id);
        } catch (Exception e){
            System.out.println("Error al eliminar la direccion");
        }
    }

}
