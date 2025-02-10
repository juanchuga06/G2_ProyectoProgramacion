package BusinessLogicComponent.entities.Transacciones;

import java.time.LocalDate;

import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Personas.Bibliotecario;
import BusinessLogicComponent.entities.Personas.Cliente;

public class Alquiler {
    private Integer IdAlquiler;
    private String FechaAlquiler;
    private String FechaDevolucion;
    private Libro Libro;
    private Cliente Cliente;
    private Bibliotecario Bibliotecario;
    private EstadoAlquiler EstadoAlquiler;

    public Alquiler(){}
    // Este constructor se utiliza para insertar un nuevo alquiler desde la base de datos, 
    // La creación de una devolucion se maneja a través de GestorAquiler

    public Alquiler(Integer idAlquiler, String fechaAlquiler, Libro libro,
            Cliente cliente, Bibliotecario bibliotecario, EstadoAlquiler estadoAlquiler) {
        setIdAlquiler(idAlquiler);
        setFechaAlquiler(fechaAlquiler);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
        setEstadoAlquiler(estadoAlquiler);
    }

    // este constructo es para actualizar un Alquiler
    public Alquiler(String fechaAlquiler, Libro libro, Cliente cliente, 
                    Bibliotecario bibliotecario, EstadoAlquiler estadoAlquiler) {
        setFechaAlquiler(fechaAlquiler);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
        setEstadoAlquiler(estadoAlquiler);
    }

    //Este constructor es para sacar las devoluciones de la base de datos
    public Alquiler(Integer idAlquiler, String fechaAlquiler, Libro libro, String fechaDevolucion,
            Cliente cliente, Bibliotecario bibliotecario, EstadoAlquiler estadoAlquiler) {
        setIdAlquiler(idAlquiler);
        setFechaAlquiler(fechaAlquiler);
        setFechaDevolucion(fechaDevolucion);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
        setEstadoAlquiler(estadoAlquiler);
    }

    // Este constructor sirve para actualizar una devolucion
    public Alquiler(String fechaAlquiler, Libro libro, String fechaDevolucion,
            Cliente cliente, Bibliotecario bibliotecario, EstadoAlquiler estadoAlquiler) {
        setFechaAlquiler(fechaAlquiler);
        setFechaDevolucion(fechaDevolucion);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
        setEstadoAlquiler(estadoAlquiler);
    }


    public Integer getIdAlquiler() {
        return IdAlquiler;
    }
    public void setIdAlquiler(Integer idAlquiler) {
        if(idAlquiler == null || idAlquiler <= 0)
            return;
        else
            IdAlquiler = idAlquiler;
    }
    public String getFechaAlquiler() {
        return FechaAlquiler;
    }
    public void setFechaAlquiler(String fechaAlquiler) {
        if(fechaAlquiler == null || fechaAlquiler.isEmpty())
            this.FechaAlquiler = LocalDate.now().toString();
        else
            this.FechaAlquiler = fechaAlquiler;
    }
    public String getFechaDevolucion() {
        return FechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        if(fechaDevolucion != null)
            this.FechaDevolucion = fechaDevolucion;
        else 
            this.FechaDevolucion = null;
    }
    public Libro getLibro() {
        return Libro;
    }
    public void setLibro(Libro libro) {
        if(libro == null)
            return;
        else
            this.Libro = libro;
    }
    public Cliente getCliente() {
        return Cliente;
    }
    public void setCliente(Cliente cliente) {
        if(cliente == null)
            return;
        else
            this.Cliente = cliente;
    }
    public Bibliotecario getBibliotecario() {
        return Bibliotecario;
    }
    public void setBibliotecario(Bibliotecario bibliotecario) {
        if(bibliotecario == null)
            return;
        else
            this.Bibliotecario = bibliotecario;
    }
    public EstadoAlquiler getEstadoAlquiler() {
        return EstadoAlquiler;
    }
    public void setEstadoAlquiler(EstadoAlquiler estadoAlquiler) {
        if(estadoAlquiler == null)
            return;
        else
            this.EstadoAlquiler = estadoAlquiler;
    }


}
