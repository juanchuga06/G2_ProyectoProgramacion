package DataAccessComponent.DTO;

public class SexoDTO  {
    private Integer IdSexo;
    private String  Nombre;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;

    public SexoDTO(){ }

    public SexoDTO(String nombre){
        Nombre = nombre;
    }
    
    public SexoDTO(Integer idSexo, String nombre, String estado, String fechaCreacion, String fechaModificacion) {
        setIdSexo(idSexo);
        setNombre(nombre);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
    }

    public SexoDTO(Integer idSexo, String nombre) {
        setIdSexo(idSexo);
        setNombre(nombre);
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer IdSexo) {
        this.IdSexo = IdSexo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(String FechaModificacion) {
        this.FechaModificacion = FechaModificacion;
    }

    
    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdSexo            : "+ getIdSexo           ()
        + "\n Nombre            : "+ getNombre           ()
        + "\n Estado            : "+ getEstado           ()
        + "\n FechaCreacion     : "+ getFechaCreacion    ()
        + "\n FechaModificacion : "+ getFechaModificacion() +"\n";
    }
}
