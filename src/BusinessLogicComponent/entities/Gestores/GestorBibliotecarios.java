package BusinessLogicComponent.entities.Gestores;

import java.util.ArrayList;
import java.util.List;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Personas.*;
import DataAccessComponent.DAO.BibliotecarioDAO;
import DataAccessComponent.DAO.DireccionDAO;
import DataAccessComponent.DAO.EstadoCivilDAO;
import DataAccessComponent.DAO.SexoDAO;
import DataAccessComponent.DTO.BibliotecarioDTO;
import DataAccessComponent.DTO.DireccionDTO;
import DataAccessComponent.DTO.EstadoCivilDTO;
import DataAccessComponent.DTO.SexoDTO;

public class GestorBibliotecarios {
    public List<Bibliotecario> BibliotecarioList;
    public List<Sexo> SexoList;
    public List <EstadoCivil> EstadoCivilList;
    public List <Direccion> DireccionList;

    private BLFactory<BibliotecarioDTO> BibliotecarioBL;
    private BLFactory<SexoDTO> SexoBl;
    private BLFactory<EstadoCivilDTO> EstadoCivilBL;
    private BLFactory<DireccionDTO> DireccionBL;


    public GestorBibliotecarios(){
        this.BibliotecarioList = new ArrayList<>();
        this.SexoList = new ArrayList<>();
        this.EstadoCivilList = new ArrayList<>();
        this.DireccionList = new ArrayList<>();

        this.BibliotecarioBL = new BLFactory<>(BibliotecarioDAO::new);
        this.SexoBl = new BLFactory<>(SexoDAO::new);
        this.EstadoCivilBL = new BLFactory<>(EstadoCivilDAO::new);
        this.DireccionBL = new BLFactory<>(DireccionDAO::new);

        cargarBibliotecarios();
    }

    public void cargarBibliotecarios(){
        this.BibliotecarioList.clear();
        this.DireccionList.clear();
        this.EstadoCivilList.clear();
        this.SexoList.clear();

        Bibliotecario bibliotecarioaux;
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
                for(BibliotecarioDTO b: BibliotecarioBL.getAll()){
                    bibliotecarioaux = new Bibliotecario(b.getIdBibliotecario(), b.getNombre(), b.getApellido(), b.getCedula(), 
                                                         b.getTelefono(), b.getCorreoElectronico(), getECByID(b.getIdEstadoCivil()), 
                                                         getSexoByID(b.getIdSexo()), b.getUsuario(), b.getContrasenia());
                    this.BibliotecarioList.add(bibliotecarioaux);
                }
            } catch (Exception e) {
                System.out.println("Error al cargar los bibliotecarios");
            }
            try {
                for(DireccionDTO d: DireccionBL.getAll()){
                    direccionaux = new Direccion(d.getIdDireccion(), d.getCallePrincipal(), d.getCalleSecundaria(), getBibliotecarioByID(d.getIdBibliotecario()));
                    this.DireccionList.add(direccionaux);
                }

                // Se añaden las direcciones a cada cliente
                for(Bibliotecario b: BibliotecarioList){
                    for(Direccion d: DireccionList){
                        if(d.getBibliotecario().getIdPersona() == b.getIdPersona()){
                            b.Direcciones.add(d);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar las direcciones");
            }
    }

    public void registrarBibliotecario(Bibliotecario bibliotecario){
        if(bibliotecario == null)
            return;
        try {
                BibliotecarioBL.add(new BibliotecarioDTO(bibliotecario.getNombre(), bibliotecario.getApellido(), 
                                             bibliotecario.getCedula(), bibliotecario.getTelefono(), 
                                             bibliotecario.getCorreoElectronico(), bibliotecario.getUsuario(), bibliotecario.getContrasena(),
                                             bibliotecario.getEstadoCivil().getIdEstadoCivil(), bibliotecario.getSexo().getIdSexo()));
            } catch (Exception e) {
                System.out.println("Error al registrar al bibliotecario");
        }
    }

    public void actualizarCliente(Bibliotecario bibliotecario){
        if(bibliotecario == null)
            return;
        try {
                BibliotecarioBL.add(new BibliotecarioDTO(bibliotecario.getIdPersona(), bibliotecario.getNombre(), bibliotecario.getApellido(), 
                                            bibliotecario.getCedula(), bibliotecario.getTelefono(), bibliotecario.getCorreoElectronico(), 
                                            bibliotecario.getUsuario(), bibliotecario.getContrasena(),bibliotecario.getEstadoCivil().getIdEstadoCivil(),
                                            bibliotecario.getSexo().getIdSexo()));
            } catch (Exception e) {
                System.out.println("Error al actualizar al bibliotecario");
        }
    }

    public void eliminarBibliotecario(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            BibliotecarioBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar al bibliotecario");
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

    public Bibliotecario getBibliotecarioByID(Integer ID){
        Bibliotecario baux = new Bibliotecario();
        for(Bibliotecario b: BibliotecarioList){
            if(b.getIdPersona().equals(ID)){
                baux = b;
                break;
            }
        }
        return baux;
    }

}

