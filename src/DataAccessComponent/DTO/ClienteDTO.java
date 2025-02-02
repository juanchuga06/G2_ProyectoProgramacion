package DataAccessComponent.DTO;

public class ClienteDTO {
    private Integer IdCliente;
    private String  Nombre;
    private String  Apellido;
    private String  Cedula;
    private String  Telefono;
    private String  CorreoElectronico;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    private String  NombreEstadoCivil;
    private String  NombreSexo;
    private Integer IdEstadoCivil;
    private Integer IdSexo;

    public ClienteDTO(){ }

    public ClienteDTO(Integer idCliente, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, String estado, String fechaCreacion, String fechaModificacion,
            String nombreEstadoCivil, String nombreSexo) {
            setIdCliente(idCliente);
            setNombre(nombre);
            setApellido(apellido);
            setCedula(cedula);
            setTelefono(telefono);
            setCorreoElectronico(correoElectronico);
            setEstado(estado);
            setFechaCreacion(fechaCreacion);
            setFechaModificacion(fechaModificacion);
            setNombreEstadoCivil(nombreEstadoCivil);
            setNombreSexo(nombreSexo);
    }

    public ClienteDTO(String nombre, String apellido, String cedula, String telefono, String correoElectronico, Integer idEstadoCivil, Integer idSexo){
        Nombre = nombre;
        Apellido = apellido;
        Cedula = cedula;
        Telefono = telefono;
        CorreoElectronico = correoElectronico;
        IdEstadoCivil = idEstadoCivil;
        IdSexo = idSexo;
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
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

    public String getNombreEstadoCivil() {
        return NombreEstadoCivil;
    }

    public void setNombreEstadoCivil(String INombreEstadoCivil) {
        this.NombreEstadoCivil = INombreEstadoCivil;
    }

    public String getNombreSexo() {
        return NombreSexo;
    }

    public void setNombreSexo(String NombreSexo) {
        this.NombreSexo = NombreSexo;
    }

    public Integer getIdEstadoCivil() {
        return IdEstadoCivil;
    }

    public void setIdEstadoCivil(Integer IdEstadoCivil) {
        this.IdEstadoCivil = IdEstadoCivil;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer IdSexo) {
        this.IdSexo = IdSexo;
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdCliente             : "+ getIdCliente()
        + "\n Nombre                : "+ getNombre()
        + "\n Apellido              : "+ getApellido()
        + "\n Cedula                : "+ getCedula()
        + "\n Telefono              : "+ getTelefono()
        + "\n CorreoElectronico     : "+ getCorreoElectronico()
        + "\n Estado                : "+ getEstado()
        + "\n FechaCreacion         : "+ getFechaCreacion()
        + "\n FechaModificacion     : "+ getFechaModificacion()
        + "\n EstadoCivil           : "+ getNombreEstadoCivil()
        + "\n Sexo                  : "+ getNombreSexo() ;
    }
}
