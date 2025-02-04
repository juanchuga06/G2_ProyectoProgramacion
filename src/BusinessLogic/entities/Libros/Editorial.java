package BusinessLogic.entities.Libros;

public class Editorial {
    private Integer IdEditorial;
    private String Nombre;
    
    public Editorial() {
    }

    public Editorial(Integer idEditorial, String nombre) {
        setIdEditorial(idEditorial);
        setNombre(nombre);
    }

    public Editorial(String nombre) {
        setNombre(nombre);
    }
    
    public Integer getIdEditorial() {
        return IdEditorial;
    }
    public void setIdEditorial(Integer idEditorial) {
        if(idEditorial == null || idEditorial <= 0)
            return;
        else
            this.IdEditorial = idEditorial;
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
