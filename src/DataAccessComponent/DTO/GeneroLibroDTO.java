package DataAccessComponent.DTO;

public class GeneroLibroDTO {
    private Integer  IdGeneroLibro;
    private String   NombreGeneroLibro;
    private String   Estado;
    private String   FechaCreacion;
    private String   FechaModificaion;

    public GeneroLibroDTO(){
    }

    public GeneroLibroDTO(Integer idGL, String nG, String e, String fC, String fM){
        setIdGeneroLibro(idGL);
        setNombreGeneroLibro(nG);
        setEstado(e);
        setFechaCreacion(fC);
        setFechaModificaion(fM);
    }

    public Integer getIdGeneroLibro() {
        return IdGeneroLibro;
    }
    public void setIdGeneroLibro(Integer idGeneroLibro) {
        IdGeneroLibro = idGeneroLibro;
    }
    public String getNombreGeneroLibro() {
        return NombreGeneroLibro;
    }
    public void setNombreGeneroLibro(String nombreGeneroLibro) {
        NombreGeneroLibro = nombreGeneroLibro;
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
        +"\n IdGeneroLibro              : " + getIdGeneroLibro()
        +"\n NombreGeneroLibro          : " + getNombreGeneroLibro()
        +"\n Estado                     : " + getEstado()
        +"\n FechaCreacion              : " + getFechaCreacion()
        +"\n FechaModificacion          : " + getFechaModificaion();

    }
}
