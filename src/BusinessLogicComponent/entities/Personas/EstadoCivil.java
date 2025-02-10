package BusinessLogicComponent.entities.Personas;

public class EstadoCivil {
    private Integer IdEstadoCivil;
    private String Nombre;
    
    
    public EstadoCivil() {}

    public EstadoCivil(Integer idEstadoCivil, String nombre) {
        setIdEstadoCivil(idEstadoCivil);
        setNombre(nombre);
    }

    public EstadoCivil(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdEstadoCivil() {
        return IdEstadoCivil;
    }
    public void setIdEstadoCivil(Integer idEstadoCivil) {
        if(idEstadoCivil == null || idEstadoCivil <= 0)
            return;
        else
            this.IdEstadoCivil = idEstadoCivil;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty())
            return;
        else
            this.Nombre = nombre;
    }
}
