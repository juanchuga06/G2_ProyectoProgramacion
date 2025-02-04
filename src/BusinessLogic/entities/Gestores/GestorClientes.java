package BusinessLogic.entities.Gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.BLFactory;
import BusinessLogic.entities.Personas.*;
import DataAccessComponent.DAO.ClienteDAO;
import DataAccessComponent.DAO.DireccionDAO;
import DataAccessComponent.DAO.EstadoCivilDAO;
import DataAccessComponent.DAO.SexoDAO;
import DataAccessComponent.DTO.ClienteDTO;
import DataAccessComponent.DTO.DireccionDTO;
import DataAccessComponent.DTO.EstadoCivilDTO;
import DataAccessComponent.DTO.SexoDTO;

public class GestorClientes {
    public List<Cliente> ClienteList;
    public List<Sexo> SexoList;
    public List <EstadoCivil> EstadoCivilList;
    public List <Direccion> DireccionList;

    private BLFactory<ClienteDTO> ClienteBL;
    private BLFactory<SexoDTO> SexoBl;
    private BLFactory<EstadoCivilDTO> EstadoCivilBL;
    private BLFactory<DireccionDTO> DireccionBL;


    public GestorClientes(){
        this.ClienteList = new ArrayList<>();
        this.SexoList = new ArrayList<>();
        this.EstadoCivilList = new ArrayList<>();
        this.DireccionList = new ArrayList<>();

        this.ClienteBL = new BLFactory<>(ClienteDAO::new);
        this.SexoBl = new BLFactory<>(SexoDAO::new);
        this.EstadoCivilBL = new BLFactory<>(EstadoCivilDAO::new);
        this.DireccionBL = new BLFactory<>(DireccionDAO::new);

        cargarClientes();
    }

    public void cargarClientes(){
        this.ClienteList.clear();
        this.DireccionList.clear();
        this.EstadoCivilList.clear();
        this.SexoList.clear();

        Cliente clienteaux;
        Direccion direccionaux;
        EstadoCivil estadocivilaux;
        Sexo sexoaux;
            try {
                for(SexoDTO s: SexoBl.getAll()){
                    sexoaux = new Sexo(s.getIdSexo(),s.getNombre());
                    this.SexoList.add(sexoaux);
                }
                
            } catch (Exception e) {
                System.out.println("Error al cargar los sexos");
            }
            try {
                for(EstadoCivilDTO ec: EstadoCivilBL.getAll()){
                    estadocivilaux = new EstadoCivil(ec.getIdEstadoCivil(),ec.getNombre());
                    this.EstadoCivilList.add(estadocivilaux);
                }
            } catch (Exception e) {
                System.out.println("Error al cargar los estados Civiles");
            }
            try {
                for(ClienteDTO c: ClienteBL.getAll()){
                    clienteaux = new Cliente(c.getIdCliente(), c.getNombre(), c.getApellido(), c.getCedula(), 
                                             c.getTelefono(), c.getCorreoElectronico(), 
                                             getECByID(c.getIdEstadoCivil()), getSexoByID(c.getIdSexo()));
                    this.ClienteList.add(clienteaux);
                }
            } catch (Exception e) {
                System.out.println("Error al cargar los clientes");
            }
            try {
                for(DireccionDTO d: DireccionBL.getAll()){
                    direccionaux = new Direccion(d.getIdDireccion(), d.getCallePrincipal(), d.getCalleSecundaria(), getClienteByID(d.getIdCliente()));
                    this.DireccionList.add(direccionaux);
                }

                // Se añaden las direcciones a cada cliente
                for(Cliente c: ClienteList){
                    for(Direccion d: DireccionList){
                        if(d.getCliente().getIdPersona() == c.getIdPersona()){
                            c.Direcciones.add(d);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar las direcciones");
            }
    }

    public void registrarCliente(Cliente cliente){
        if(cliente == null)
            return;
        try {
                ClienteBL.add(new ClienteDTO(cliente.getNombre(), cliente.getApellido(), 
                                             cliente.getCedula(), cliente.getTelefono(), 
                                             cliente.getCorreoElectronico(), cliente.getEstadoCivil().getIdEstadoCivil(), 
                                             cliente.getSexo().getIdSexo()));
            } catch (Exception e) {
                System.out.println("Error al registrar al cliente");
        }
    }

    public void actualizarCliente(Cliente cliente){
        if(cliente == null)
            return;
        try{
            ClienteBL.upd(new ClienteDTO(cliente.getIdPersona(), cliente.getNombre(), cliente.getApellido(), 
                                         cliente.getCedula(), cliente.getTelefono(), cliente.getCorreoElectronico(), 
                                         cliente.getEstadoCivil().getIdEstadoCivil(), cliente.getSexo().getIdSexo(), 
                                         LocalDate.now().toString()));   
        } catch (Exception e) {
            System.out.println("Error al registrar al cliente");
        }
    }

    public void eliminarCliente(Integer id){
        if(id == null || id > ClienteList.size() || id <= 0)
            return;
        try{
            ClienteBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar al cliente");
        }
    }
    
    public EstadoCivil getECByID(Integer id){
        EstadoCivil eaux = new EstadoCivil();
        for(EstadoCivil ec: EstadoCivilList){
            if(ec.getIdEstadoCivil().equals(id)){
                eaux = ec;
                break;
            }
        }
        return eaux;
    }

    public Sexo getSexoByID(Integer id){
        Sexo saux = new Sexo();
        for(Sexo s: SexoList){
            if(s.getIdSexo().equals(id)){
                saux = s;
                break;
            }
        }
        return saux;
    }

    public Cliente getClienteByID(Integer ID){
        Cliente claux = new Cliente();
        for(Cliente c: ClienteList){
            if(c.getIdPersona().equals(ID)){
                claux = c;
                break;
            }
        }
        return claux;
    }

}
