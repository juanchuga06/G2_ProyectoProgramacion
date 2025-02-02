package BusinessLogic.entities.Personas;
import java.util.List;

public abstract class Persona {
    private Integer IdPersona;
    private String Nombre;
    private String Apellido;
    private String Cedula;
    private String Telefono;
    private String CorreoElectronico;
    private EstadoCivil EstadoCivil;
    private Sexo Sexo;
    public List<Direccion> Direcciones;


    public Persona(Integer idPersona, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil,Sexo sexo, List<Direccion> direcciones) {
        setIdPersona(idPersona);
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        setSexo(sexo);
        setDirecciones(direcciones);
    }

    public Persona(String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil,Sexo sexo, List<Direccion> direcciones) {
        setNombre(nombre);
        setApellido(apellido);
        setCedula(cedula);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
        setSexo(sexo);
        setDirecciones(direcciones);
    }
    public Integer getIdPersona() {
        return IdPersona;
    }
    public void setIdPersona(Integer idPersona) {
        if(idPersona == null || idPersona <= 0)
            return;
        else
            this.IdPersona = idPersona;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty())
            this.Nombre = "NombreComun";
        else
            this.Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        if(Apellido == null || Apellido.isEmpty())
            this.Apellido = "ApellidoComun";
        else
            this.Apellido = apellido;
    }
    public String getCedula() {
        return Cedula;
    }
    public void setCedula(String cedula) {
        if(cedula == null || cedula.isEmpty())
            return;
        else
            this.Cedula = cedula;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        if(telefono == null || telefono.isEmpty())
            this.Telefono = "9999999999";
        else
            this.Telefono = telefono;
    }
    public String getCorreoElectronico() {
        return CorreoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        if(correoElectronico == null || correoElectronico.isEmpty())
            return;
        else
            this.CorreoElectronico = correoElectronico;
    }
    public EstadoCivil getEstadoCivil() {
        return EstadoCivil;
    }
    public void setEstadoCivil(EstadoCivil estadoCivil) {
        if(estadoCivil == null)
            return;
        else
            this.EstadoCivil = estadoCivil;
    }
    public Sexo getSexo() {
        return Sexo;
    }
    public void setSexo(Sexo sexo) {
        if(sexo == null)
            return;
        else
            this.Sexo = sexo;
    }
    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    //Para hacer esta parte debo tomar los registros de direcciones DAO que
    // tengan la id del Cliente, transformarlas a la claseDireccion
    //  y añadirla en la lista propia del cliente
    public void setDirecciones(List<Direccion> direcciones) {}
    
    
}
