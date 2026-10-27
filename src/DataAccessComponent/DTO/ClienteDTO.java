package DataAccessComponent.DTO;

public class ClienteDTO extends PersonaDTO {
    private Integer IdCliente;

    public ClienteDTO(){ }

    public ClienteDTO(Integer idCliente, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, String estado, String fechaCreacion, String fechaModificacion,
            Integer IdEstadoCivil, Integer IdSexo) {
            setIdCliente(idCliente);
            setNombre(nombre);
            setApellido(apellido);
            setCedula(cedula);
            setTelefono(telefono);
            setCorreoElectronico(correoElectronico);
            setEstado(estado);
            setFechaCreacion(fechaCreacion);
            setFechaModificacion(fechaModificacion);
            setIdEstadoCivil(IdEstadoCivil);
            setIdSexo(IdSexo);
    }

    public ClienteDTO(Integer idCliente, String nombre, String apellido, String cedula, String telefono,
                        String correoElectronico, Integer IdEstadoCivil, Integer IdSexo, String FechaModificacion) {
            setIdCliente(idCliente);
            setNombre(nombre);
            setApellido(apellido);
            setCedula(cedula);
            setTelefono(telefono);
            setCorreoElectronico(correoElectronico);
            setIdEstadoCivil(IdEstadoCivil);
            setIdSexo(IdSexo);
            setFechaModificacion(FechaModificacion);
}

    public ClienteDTO(String nombre, String apellido, String cedula, String telefono, String correoElectronico, Integer idEstadoCivil, Integer idSexo){
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        setIdEstadoCivil(idEstadoCivil);
        setIdSexo(idSexo);
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer IdCliente) {
        this.IdCliente = IdCliente;
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
        + "\n EstadoCivil           : "+ getIdEstadoCivil()
        + "\n Sexo                  : "+ getIdSexo() + "\n";
    }
}