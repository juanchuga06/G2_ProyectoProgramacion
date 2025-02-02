package BusinessLogic.entities.Personas;

import java.util.List;

public class Bibliotecario extends Persona{
    private String Usuario;
    private String Contrasena;

    public Bibliotecario(Integer idPersona, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil,Sexo sexo, List<Direccion> direcciones, 
            String usuario, String contrasena) {
        super(idPersona, nombre, apellido, cedula, telefono, correoElectronico, estadoCivil, sexo, direcciones);
        setUsuario(usuario);
        setContrasena(contrasena);
    }

    public Bibliotecario(String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil,Sexo sexo, List<Direccion> direcciones, 
            String usuario, String contrasena) {
        super(nombre, apellido, cedula, telefono, correoElectronico, estadoCivil, sexo, direcciones);
        setUsuario(usuario);
        setContrasena(contrasena);
    }

    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        if(usuario == null || usuario.isEmpty())
            return;
        else
            this.Usuario = usuario;
    }
    public String getContrasena() {
        return Contrasena;
    }
    public void setContrasena(String contrasena) {
        if(contrasena == null || contrasena.isEmpty())
            return;
        else
            this.Contrasena = contrasena;
    }

}
