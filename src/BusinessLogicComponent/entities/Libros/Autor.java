package BusinessLogicComponent.entities.Libros;

public class Autor {
    private Integer idAutor;
    private String nombre;
    
    public Autor() {
    }

    public Autor(Integer idAutor, String nombre) {
        setIdAutor(idAutor);
        setNombre(nombre);
    }

    public Autor(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(Integer idAutor) {
        if(idAutor == null || idAutor <= 0)
            return;
        else
            this.idAutor = idAutor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty())
            this.nombre = "NombreComun";
        else
            this.nombre = nombre;
    }
}
