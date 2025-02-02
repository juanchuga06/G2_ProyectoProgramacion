package DataAccessComponent.DTO;

public class DireccionDTO {
    private Integer IdDireccion;
    private String  CallePrincipal;
    private String  CalleSecundaria;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    private String  NombreCliente;
    private String  ApellidoCliente;
    private String  NombreBibliotecario;
    private String  ApellidoBibliotecario;
    private Integer IdCliente;
    private Integer IdBibliotecario;

    public DireccionDTO(){ }

    public DireccionDTO(Integer idDireccion, String callePrincipal, String calleSecundaria, String estado,
            String fechaCreacion, String fechaModificacion, String nombreCliente, String apellidoCliente,
            String nombreBibliotecario, String apellidoBibliotecario) {
        setIdDireccion(idDireccion);
        setCallePrincipal(callePrincipal);
        setCalleSecundaria(calleSecundaria);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
        setNombreCliente(nombreCliente);
        setApellidoCliente(apellidoCliente);
        setNombreBibliotecario(nombreBibliotecario);
        setApellidoBibliotecario(apellidoBibliotecario);
    }


public DireccionDTO(String callePrincipal, String calleSecundaria, Integer idCliente, Integer idBibliotecario){
    CallePrincipal = callePrincipal;
    CalleSecundaria = calleSecundaria;
    IdCliente = idCliente;
    IdBibliotecario = idBibliotecario;
}


    public Integer getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        IdDireccion = idDireccion;
    }

    public String getCallePrincipal() {
        return CallePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        CallePrincipal = callePrincipal;
    }

    public String getCalleSecundaria() {
        return CalleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        CalleSecundaria = calleSecundaria;
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

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return ApellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        ApellidoCliente = apellidoCliente;
    }

    public String getNombreBibliotecario() {
        return NombreBibliotecario;
    }

    public void setNombreBibliotecario(String nombreBibliotecario) {
        NombreBibliotecario = nombreBibliotecario;
    }

    public String getApellidoBibliotecario() {
        return ApellidoBibliotecario;
    }

    public void setApellidoBibliotecario(String apellidoBibliotecario) {
        ApellidoBibliotecario = apellidoBibliotecario;
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

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdDireccion           : "+ getIdDireccion()
        + "\n CallePrincipal        : "+ getCallePrincipal()
        + "\n CalleSecundaria       : "+ getCalleSecundaria()
        + "\n Estado                : "+ getEstado()
        + "\n FechaCreacion         : "+ getFechaCreacion()
        + "\n FechaModificacion     : "+ getFechaModificacion()
        + "\n NombreCliente         : "+ getNombreCliente()
        + "\n ApellidoCliente       : "+ getApellidoCliente()
        + "\n NombreBibliotecario   : "+ getNombreBibliotecario()
        + "\n ApellidoBibliotecario : "+ getApellidoBibliotecario() +"\n";
    }
}
