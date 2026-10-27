package DataAccessComponent.DTO;

public class BibliotecarioDTO extends PersonaDTO {
    private Integer idBibliotecario;    
    private String  Usuario;            
    private String  Contrasenia ;       

    public BibliotecarioDTO(String nombre, String apellido, String cedula, String telefono,
    String correoElectronico, String usuario, String contrasenia, Integer idEstadoCivil, Integer idSexo ){
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        Usuario = usuario;
        Contrasenia = contrasenia;
        setIdEstadoCivil(idEstadoCivil);
        setIdSexo(idSexo);
    }

    public BibliotecarioDTO(Integer idB, String nombre, String apellido, String cedula, String telefono,
    String correoElectronico, String usuario, String contrasenia, Integer idEstadoCivil, Integer idSexo ){
        idBibliotecario = idB;
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        Usuario = usuario;
        Contrasenia = contrasenia;
        setIdEstadoCivil(idEstadoCivil);
        setIdSexo(idSexo);
    }

    
    public BibliotecarioDTO(Integer idBibliotecario, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, String usuario, String contrasenia, String estado, String fechaCreacion,
            String fechaModificacion, Integer nombreEstadoCivil, Integer nombreSexo) {

                
        setIdBibliotecario(idBibliotecario);
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        setUsuario(usuario);
        setContrasenia(contrasenia);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
        setIdEstadoCivil(nombreEstadoCivil);
        setIdSexo(nombreSexo);
    }

    

    public BibliotecarioDTO(){

    }
    
    
    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Integer idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    
    public String toString(){
        return getClass().getName() 
        + "\n IdBibliotecario     :"+ getIdBibliotecario  ()
        + "\n Nombre              :"+ getNombre           ()
        + "\n Apellido            :"+ getApellido         ()
        + "\n Cedula              :"+ getCedula           ()
        + "\n Telefono            :"+ getTelefono         ()
        + "\n CorreoElectronico   :"+ getCorreoElectronico()
        + "\n Usuario             :"+ getUsuario          ()
        + "\n Contrasenia         :"+ getContrasenia      ()
        + "\n Estado              :"+ getEstado           ()
        + "\n FechaCreacion       :"+ getFechaCreacion    ()
        + "\n FechaModificacion   :"+ getFechaModificacion()
        + "\n EstadoCivil         :"+ getIdEstadoCivil    ()
        + "\n Sexo                :"+ getIdSexo           () + "\n";

    }
}