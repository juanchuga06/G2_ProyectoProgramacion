package DataAccessComponent.DTO;

public class AlquilerDTO {
    private Integer IdAlquiler;
    private String  Estado;
    private String  FechaAlquiler;
    private String  FechaDevolucion;
    private String  FechaModificacion;
    private String  LibroTitulo;
    private String  ClienteNombre;
    private String  BibliotecarioNombre;
    private String  EstadoAlquilerNombre;
    
    private Integer  IdLibro;
    private Integer  IdCliente;
    private Integer  IdBibliotecario;
    private Integer  IdEstadoAlquiler;
    
    public AlquilerDTO(){ }
    
    public AlquilerDTO(Integer idA, String estado, String fechaAlquiler, String fechaDevolucion, String fechaModificacion,
                       String libroTitulo, String clienteNombre, String bibliotecarioNombre, String estadoAlquilerNombre){
        setIdAlquiler(idA);
        setEstado(estado);
        setFechaAlquiler(fechaAlquiler);
        setFechaDevolucion(fechaDevolucion);
        setFechaModificaion(fechaModificacion);
        setLibroTitulo(libroTitulo);
        setClienteNombre(clienteNombre);
        setBibliotecarioNombre(bibliotecarioNombre);
        setEstadoAlquilerNombre(estadoAlquilerNombre);
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
    public String getLibroTitulo() {
        return LibroTitulo;
    }
    
    public void setLibroTitulo(String libroTitulo) {
        LibroTitulo = libroTitulo;
    }
    
    public String getClienteNombre() {
        return ClienteNombre;
    }
    
    public void setClienteNombre(String clienteNombre) {
        ClienteNombre = clienteNombre;
    }
    
    public String getBibliotecarioNombre() {
        return BibliotecarioNombre;
    }
    
    public void setBibliotecarioNombre(String bibliotecarioNombre) {
        BibliotecarioNombre = bibliotecarioNombre;
    }
    
    public String getEstadoAlquilerNombre() {
        return EstadoAlquilerNombre;
    }
    
    public void setEstadoAlquilerNombre(String estadoAlquilerNombre) {
        EstadoAlquilerNombre = estadoAlquilerNombre;
    }
    
    @Override
    public String toString(){
        return getClass().getName()
        +"\n IdAlquiler                     : " + getIdAlquiler()
        +"\n Estado                         : " + getEstado()
        +"\n FechaAlquiler                  : " + getFechaAlquiler()
        +"\n FechaDevolucion                : " + getFechaDevolucion()
        +"\n FechaModificacion              : " + getFechaModificaion()
        +"\n LibroTitulo                    : " + getLibroTitulo()
        +"\n ClienteNombre                  : " + getClienteNombre()
        +"\n BibliotecarioNombre            : " + getBibliotecarioNombre()
        +"\n EstadoAlquilerNombre           : " + getEstadoAlquilerNombre();
    }
    
    
}