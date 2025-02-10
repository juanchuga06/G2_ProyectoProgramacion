package BusinessLogicComponent.entities.Personas;

public class Sexo {
    private Integer IdSexo;
    private String Nombre;
    
    public Sexo() {}

    public Sexo(Integer idSexo, String nombre) {
        setIdSexo(idSexo);
        setNombre(nombre);
    }

    public Sexo(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdSexo() {
        return IdSexo;
    }
    public void setIdSexo(Integer idSexo) {
        if(idSexo == null || idSexo <= 0)
            return;
        else
            this.IdSexo = idSexo;
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
}
