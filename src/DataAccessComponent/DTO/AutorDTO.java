package DataAccessComponent.DTO;

public class AutorDTO {
    private Integer IdAutor;
    private String  NombreAutor;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;

    public AutorDTO(){ }

    public AutorDTO(String nombreAutor){
        NombreAutor = nombreAutor;
    }

    public AutorDTO(Integer idAutor, String nombreAutor, String estado, String fechaCreacion,
        String fechaModificacion) {
        setIdAutor(idAutor);
        setNombreAutor(nombreAutor);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
    }

    public AutorDTO(Integer idAutor, String nombreAutor) {
        setIdAutor(idAutor);
        setNombreAutor(nombreAutor);
    }

    public Integer getIdAutor() {
        return IdAutor;
    }

    public void setIdAutor(Integer IdAutor) {
        this.IdAutor = IdAutor;
    }

    public String getNombreAutor() {
        return NombreAutor;
    }

    public void setNombreAutor(String NombreAutor) {
        this.NombreAutor = NombreAutor;
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
        return getClass().getName                        ()
        + "\n IdAutor           : "+ getIdAutor          ()
        + "\n NombreAutor       : "+ getNombreAutor      ()
        + "\n Estado            : "+ getEstado           ()
        + "\n FechaCreacion     : "+ getFechaCreacion    ()
        + "\n FechaModificacion : "+ getFechaModificacion() + "\n";
    }

}
