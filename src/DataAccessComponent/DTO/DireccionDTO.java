package DataAccessComponent.DTO;

public class DireccionDTO {
    private Integer IdDireccion;
    private String  CallePrincipal;
    private String  CalleSecundaria;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    private Integer IdCliente;
    private Integer IdBibliotecario;

    public DireccionDTO(){ }

    public DireccionDTO(Integer idDireccion, String callePrincipal, String calleSecundaria, String estado,
            String fechaCreacion, String fechaModificacion, Integer idCliente, Integer idBibliotecario) {
        setIdDireccion(idDireccion);
        setCallePrincipal(callePrincipal);
        setCalleSecundaria(calleSecundaria);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
        setIdCliente(idCliente);
        setIdBibliotecario(idBibliotecario);
    }

    public DireccionDTO(Integer idDireccion, String callePrincipal, String calleSecundaria,
                        Integer idCliente, Integer idBibliotecario) {
        setIdDireccion(idDireccion);
        setCallePrincipal(callePrincipal);
        setCalleSecundaria(calleSecundaria);
        setIdCliente(idCliente);
        setIdBibliotecario(idBibliotecario);
    }


    public DireccionDTO(String callePrincipal, String calleSecundaria, Integer idCliente, Integer idBiblotecario){
        CallePrincipal = callePrincipal;
        CalleSecundaria = calleSecundaria;
        IdCliente = idCliente;
        IdBibliotecario = null;
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
        + "\n IdCliente             : "+ getIdCliente()
        + "\n IdBibliotecario       : "+ getIdBibliotecario() +"\n";
    }
}
