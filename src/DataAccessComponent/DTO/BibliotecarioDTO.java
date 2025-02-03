package DataAccessComponent.DTO;

public class BibliotecarioDTO {
    private Integer idBibliotecario;    
    private String  Nombre;             
    private String  Apellido;           
    private String  Cedula;             
    private String  Telefono;           
    private String  CorreoElectronico;  
    private String  Usuario;            
    private String  Contrasenia ;       
    private String  Estado;             
    private String  FechaCreacion;      
    private String  FechaModificacion;  
    private Integer IdEstadoCivil;
    private Integer IdSexo;



    public BibliotecarioDTO(String nombre, String apellido, String cedula, String telefono,
    String correoElectronico, String usuario, String contrasenia, Integer idEstadoCivil, Integer idSexo ){
        Nombre = nombre;
        Apellido = apellido;
        Cedula = cedula;
        Telefono = telefono;
        CorreoElectronico = correoElectronico;
        Usuario = usuario;
        Contrasenia = contrasenia;
        IdEstadoCivil = idEstadoCivil;
        IdSexo = idSexo;
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

    
    public Integer getIdEstadoCivil() {
        return IdEstadoCivil;
    }


    public void setIdEstadoCivil(Integer idEstadoCivil) {
        IdEstadoCivil = idEstadoCivil;
    }


    public Integer getIdSexo() {
        return IdSexo;
    }


    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
    }


    public BibliotecarioDTO(){

    }
    
    
    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }


    public void setIdBibliotecario(Integer idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }


    public String getNombre() {
        return Nombre;
    }


    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public String getApellido() {
        return Apellido;
    }


    public void setApellido(String apellido) {
        Apellido = apellido;
    }


    public String getCedula() {
        return Cedula;
    }


    public void setCedula(String cedula) {
        Cedula = cedula;
    }


    public String getTelefono() {
        return Telefono;
    }


    public void setTelefono(String telefono) {
        Telefono = telefono;
    }


    public String getCorreoElectronico() {
        return CorreoElectronico;
    }


    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
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
