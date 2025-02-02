package BusinessLogic.entities.Libros;

public class Autor {
    private Integer IdAutor;
    private String Nombre;
    
    public Autor(Integer idAutor, String nombre) {
        setIdAutor(idAutor);
        setNombre(nombre);
    }

    public Autor(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdAutor() {
        return IdAutor;
    }
    public void setIdAutor(Integer idAutor) {
        if(idAutor == null || idAutor <= 0)
            return;
        else
            this.IdAutor = idAutor;
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
