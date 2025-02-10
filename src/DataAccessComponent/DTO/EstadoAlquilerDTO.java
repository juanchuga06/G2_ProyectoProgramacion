package DataAccessComponent.DTO;

public class EstadoAlquilerDTO {
    private Integer     IdEstadoAlquiler;
    private String      Nombre;
    private String      Estado;
    private String      FechaCreacion;
    private String      FechaModificaion;

    public EstadoAlquilerDTO(){
    }

    public EstadoAlquilerDTO(Integer ideA, String nombre, String estado, String fechaCreacion, String fechaModificacion){
        setIdEstadoAlquiler(ideA);
        setNombre(nombre);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificaion(fechaModificacion);
    }
    public EstadoAlquilerDTO(String nombre){
        Nombre = nombre;
    }

    public Integer getIdEstadoAlquiler() {
        return IdEstadoAlquiler;
    }
    public void setIdEstadoAlquiler(Integer idEstadoAlquiler) {
        IdEstadoAlquiler = idEstadoAlquiler;
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
    public String getFechaModificaion() {
        return FechaModificaion;
    }
    public void setFechaModificaion(String fechaModificaion) {
        FechaModificaion = fechaModificaion;
    }

    @Override
    public String toString(){
        return getClass().getName()
        +"\n IdEstadoAlquiler           : " + getIdEstadoAlquiler()
        +"\n Nombre                     : " + getNombre()
        +"\n Estado                     : " + getEstado()
        +"\n FechaCreacion              : " + getFechaCreacion()
        +"\n FechaModificacion          : " + getFechaModificaion();

    }
}
