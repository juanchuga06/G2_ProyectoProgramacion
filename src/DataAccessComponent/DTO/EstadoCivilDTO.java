package DataAccessComponent.DTO;

public class EstadoCivilDTO {
    private Integer IdEstadoCivil;
    private String  Nombre;
    private String  Estado ;
    private String  FechaCreacion;
    private String  FechaModificacion;

    public EstadoCivilDTO(){ }
    
    public EstadoCivilDTO(String nombre){
        Nombre = nombre;
    }

    public EstadoCivilDTO(Integer idEstadoCivil, String nombre, String estado, String fechaCreacion, String fechaModificacion) {
                setIdEstadoCivil(idEstadoCivil);
                setNombre(nombre);
                setEstado(estado);
                setFechaCreacion(fechaCreacion);
                setFechaModificacion(fechaModificacion);
        }
            
    public Integer getIdEstadoCivil() {
        return IdEstadoCivil;
    }
    
    public void setIdEstadoCivil(Integer idEstadoCivil) {
        IdEstadoCivil = idEstadoCivil;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    @Override
    public String toString(){
        return getClass().getName() 
        + "\n IdEstadoCivil       : "+ getIdEstadoCivil    ()
        + "\n Nombre              : "+ getNombre           ()
        + "\n Estado              : "+ getEstado           ()
        + "\n FechaCreacion       : "+ getFechaCreacion    ()
        + "\n FechaModificacion   : "+ getFechaModificacion();
        
    }
}
