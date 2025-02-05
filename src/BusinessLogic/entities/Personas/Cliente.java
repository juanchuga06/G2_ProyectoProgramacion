package BusinessLogic.entities.Personas;

import java.util.List;

public class Cliente extends Persona{

    public Cliente(){}

    public Cliente(Integer idPersona, String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil, Sexo sexo) {
        super(idPersona, nombre, apellido, cedula, telefono, correoElectronico, estadoCivil, sexo);
    }

    public Cliente(String nombre, String apellido, String cedula, String telefono,
            String correoElectronico, EstadoCivil estadoCivil,Sexo sexo, List<Direccion> direcciones) {
        super(nombre, apellido, cedula, telefono, correoElectronico, estadoCivil, sexo, direcciones);
    }

}
