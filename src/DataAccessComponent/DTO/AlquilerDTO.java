package DataAccessComponent.DTO;

public class AlquilerDTO {
    private Integer IdAlquiler;
    private String  Estado;
    private String  FechaAlquiler;
    private String  FechaDevolucion;
    private String  FechaModificacion;
    
    private Integer  IdLibro;
    private Integer  IdCliente;
    private Integer  IdBibliotecario;
    private Integer  IdEstadoAlquiler;
    
    public AlquilerDTO(){ }
    
    public AlquilerDTO(Integer idA, String estado, String fechaAlquiler, String fechaDevolucion, String fechaModificacion,
                       Integer idLibro, Integer idCliente, Integer idBibliotecario, Integer idEstadoAlquiler){
        setIdAlquiler(idA);
        setEstado(estado);
        setFechaAlquiler(fechaAlquiler);
        setFechaDevolucion(fechaDevolucion);
        setFechaModificaion(fechaModificacion);
        setIdLibro(idLibro);
        setIdCliente(idCliente);
        setIdBibliotecario(idBibliotecario);
        setIdEstadoAlquiler(idEstadoAlquiler);
    }
    
    public AlquilerDTO(String fechaAlquiler, String fechaDevolucion, Integer idLibro, Integer idCliente, Integer idBibliotecario, Integer idEstadoAlquiler){
        FechaAlquiler = fechaAlquiler;
        FechaDevolucion = fechaDevolucion;
        IdLibro = idLibro;
        IdCliente = idCliente;
        IdBibliotecario = idBibliotecario;
        IdEstadoAlquiler = idEstadoAlquiler;
    }
    
    public Integer getIdAlquiler() {
        return IdAlquiler;
    }
    public void setIdAlquiler(Integer idAlquiler) {
        IdAlquiler = idAlquiler;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaAlquiler() {
        return FechaAlquiler;
    }
    public void setFechaAlquiler(String fechaAlquiler) {
        FechaAlquiler = fechaAlquiler;
    }
    public String getFechaDevolucion() {
        return FechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        FechaDevolucion = fechaDevolucion;
    }
    public String getFechaModificaion() {
        return FechaModificacion;
    }
    public void setFechaModificaion(String fechaModificaion) {
        FechaModificacion = fechaModificaion;
    }
    public Integer getIdLibro() {
        return IdLibro;
    }
    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }
    public Integer getIdCliente() {
        return IdCliente;
    }
    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }
    public Integer getIdBibliotecario() {
        return IdBibliotecario;
    }
    public void setIdBibliotecario(Integer idBibliotecario) {
        IdBibliotecario = idBibliotecario;
    }
    public Integer getIdEstadoAlquiler() {
        return IdEstadoAlquiler;
    }
    public void setIdEstadoAlquiler(Integer idEstadoAlquiler) {
        IdEstadoAlquiler = idEstadoAlquiler;
    }
    
    @Override
    public String toString(){
        return getClass().getName()
        +"\n IdAlquiler                     : " + getIdAlquiler()
        +"\n Estado                         : " + getEstado()
        +"\n FechaAlquiler                  : " + getFechaAlquiler()
        +"\n FechaDevolucion                : " + getFechaDevolucion()
        +"\n FechaModificacion              : " + getFechaModificaion()
        +"\n LibroTitulo                    : " + getIdLibro()
        +"\n ClienteNombre                  : " + getIdCliente()
        +"\n BibliotecarioNombre            : " + getIdBibliotecario()
        +"\n EstadoAlquilerNombre           : " + getIdEstadoAlquiler();
    }
    
    
}