package DataAccessComponent.DTO;

public class EditorialDTO{
    private Integer IdEditorial;
    private String  NombreEditorial;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    

    public EditorialDTO() { }

    public EditorialDTO(String nombreEditorial){
        NombreEditorial = nombreEditorial;
    };
    
    public EditorialDTO(Integer idEditorial, String nombreEditorial, String estado, String fechaCreacion, String fechaModificacion) {
       setIdEditorial(idEditorial);
       setNombreEditorial(nombreEditorial);
       setEstado(estado);
       setFechaCreacion(fechaCreacion);
       setFechaModificacion(fechaModificacion);
    }
    
    public Integer getIdEditorial() {
        return IdEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        IdEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return NombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        NombreEditorial = nombreEditorial;
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
        + "\n IdEditorial         :"+ getIdEditorial      ()
        + "\n NombreEditorial     :"+ getNombreEditorial  ()
        + "\n Estado              :"+ getEstado           ()
        + "\n FechaCreacion       :"+ getFechaCreacion    ()
        + "\n FechaModificacion   :"+ getFechaModificacion();
        
    }
    
}