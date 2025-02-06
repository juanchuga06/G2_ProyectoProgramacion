package BusinessLogicComponent.entities.Libros;

public class GeneroLibro {
    private Integer IdGeneroLibro;
    private String Nombre;
    
    public GeneroLibro() {
    }

    public GeneroLibro(Integer idGeneroLibro, String nombre) {
        setIdGeneroLibro(idGeneroLibro);
        setNombre(nombre);
    }

    public GeneroLibro(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdGeneroLibro() {
        return IdGeneroLibro;
    }
    public void setIdGeneroLibro(Integer idGeneroLibro) {
        if(idGeneroLibro == null || idGeneroLibro <= 0)
            return;
        else
            this.IdGeneroLibro = idGeneroLibro;
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
