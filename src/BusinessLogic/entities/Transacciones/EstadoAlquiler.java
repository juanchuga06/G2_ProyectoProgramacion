package BusinessLogic.entities.Transacciones;

public class EstadoAlquiler {
    private Integer IdEstadoAlquiler;
    private String Nombre;

    public EstadoAlquiler() {
    }

    public EstadoAlquiler(Integer idEstadoAlquiler, String nombre) {
        setIdEstadoAlquiler(idEstadoAlquiler);
        setNombre(nombre);
    }

    public EstadoAlquiler(String nombre) {
        setNombre(nombre);
    }

    public Integer getIdEstadoAlquiler() {
        return IdEstadoAlquiler;
    }
    public void setIdEstadoAlquiler(Integer idEstadoAlquiler) {
        if(idEstadoAlquiler == null || idEstadoAlquiler <= 0)
            return;
        else
            this.IdEstadoAlquiler = idEstadoAlquiler;
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
