package BusinessLogic.entities.Gestores;


import java.util.ArrayList;
import java.util.List;
import BusinessLogic.entities.Personas.Sexo;
import DataAccessComponent.DAO.EstadoCivilDAO;
import DataAccessComponent.DAO.SexoDAO;
import DataAccessComponent.DTO.EstadoCivilDTO;
import DataAccessComponent.DTO.SexoDTO;
import BusinessLogic.BLFactory;
import BusinessLogic.entities.Personas.EstadoCivil;

public class GestorCatalogoPersona {
    public List<Sexo> SexoList;
    public List<EstadoCivil> EstadoCivilList;

    private BLFactory<SexoDTO> SexoBl;
    private BLFactory<EstadoCivilDTO> EstadoCivilBL;

    public GestorCatalogoPersona(Integer modo){
        this.SexoList = new ArrayList<>();
        this.EstadoCivilList = new ArrayList<>();

        this.SexoBl = new BLFactory<>(SexoDAO::new);
        this.EstadoCivilBL = new BLFactory<>(EstadoCivilDAO::new);

        cargarCatalogoPersona(modo);
    }

    // Modo 1: Sexo
    // Modo 2: EstadoCivil
    public void cargarCatalogoPersona(Integer modo){
        this.EstadoCivilList.clear();
        this.SexoList.clear();

        EstadoCivil estadocivilaux;
        Sexo sexoaux;

        switch (modo) {
            case 1:
                try {
                    for(SexoDTO s: SexoBl.getAll()){
                        sexoaux = new Sexo(s.getIdSexo(),s.getNombre());
                        this.SexoList.add(sexoaux);
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error al cargar los sexos");
                }
                break;
            case 2:
                try {
                    for(EstadoCivilDTO ec: EstadoCivilBL.getAll()){
                        estadocivilaux = new EstadoCivil(ec.getIdEstadoCivil(),ec.getNombre());
                        this.EstadoCivilList.add(estadocivilaux);
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar los estados Civiles");
                }
                break;
            default:
                break;
        }
    }

    public void registrarSexo(Sexo sexo){
        if(sexo == null)
            return;
        try{
            SexoBl.add(new SexoDTO(sexo.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el sexo");
        }
    }

    public void registrarEstadoCivil(EstadoCivil estadoCivil){
        if(estadoCivil == null)
            return;
        try{
            EstadoCivilBL.add(new EstadoCivilDTO(estadoCivil.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el estadoCivil");
        }
    }

    public void actualizarSexo(Sexo sexo){
        if(sexo == null || (sexo.getIdSexo() >= 1 && sexo.getIdSexo() <= 2))
            return;
        try{
            SexoBl.upd(new SexoDTO(sexo.getIdSexo(), sexo.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el sexo");
        }
    }

    public void actualizarEstadoCivil(EstadoCivil estadoCivil){
        if(estadoCivil == null || (estadoCivil.getIdEstadoCivil() >= 1 && estadoCivil.getIdEstadoCivil() <= 5))
            return;
        try{
            EstadoCivilBL.upd(new EstadoCivilDTO(estadoCivil.getIdEstadoCivil(), estadoCivil.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el estadoCivil");
        }
    }

    public void eliminarSexo(Integer id) throws Exception{
        if((id == null || (id>= 1 && id <= 2) || id <= 0 || id > SexoBl.getMax())){
            return;
        }
        try{
            SexoBl.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar el sexo");
        }
    }

    public void eliminarEstadoCivil(Integer id) throws Exception{
        if((id == null || (id >= 1 && id <= 5) || id <= 0 || id > EstadoCivilBL.getMax())){
            return;
        }
        try{
            EstadoCivilBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar el sexo");
        }
    }
}




