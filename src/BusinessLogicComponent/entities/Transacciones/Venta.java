package BusinessLogicComponent.entities.Transacciones;

import java.time.LocalDate;

import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Personas.Bibliotecario;
import BusinessLogicComponent.entities.Personas.Cliente;

public class Venta {
    private Integer IdVenta;
    private Integer CantidadLibros;
    private Double TotalLibros;
    private Boolean Descuento;
    private Double TotalAPagar;
    private String FechaVenta;
    private Libro Libro;
    private Cliente Cliente;
    private Bibliotecario Bibliotecario;

    public Venta(Integer idVenta, Integer cantidadLibros, Double totalLibros, Boolean descuento, Double totalAPagar,
            String fechaVenta, Libro libro, Cliente cliente, Bibliotecario bibliotecario) {
        setIdVenta(idVenta);
        setCantidadLibros(cantidadLibros);
        setTotalLibros(totalLibros);
        setDescuento(descuento);
        setFechaVenta(fechaVenta);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
    }

    public Venta(Integer cantidadLibros, Double totalLibros, Boolean descuento, Double totalAPagar,
            String fechaVenta, Libro libro, Cliente cliente, Bibliotecario bibliotecario) {
        setCantidadLibros(cantidadLibros);
        setTotalLibros(totalLibros);
        setDescuento(descuento);
        setFechaVenta(fechaVenta);
        setLibro(libro);
        setCliente(cliente);
        setBibliotecario(bibliotecario);
    }


    public Integer getIdVenta() {
        return IdVenta;
    }
    public void setIdVenta(Integer idVenta) {
        if(idVenta == null || idVenta <= 0)
            return;
        else
            this.IdVenta = idVenta;
    }
    public Integer getCantidadLibros() {
        return CantidadLibros;
    }
    public void setCantidadLibros(Integer cantidadLibros) {
        if(cantidadLibros == null || cantidadLibros <= 0)
            this.CantidadLibros = 1;
        else
            this.CantidadLibros = cantidadLibros;
    }
    public Double getTotalLibros() {
        return TotalLibros;
    }
    public void setTotalLibros(Double totalLibros) {
        if(totalLibros == null || totalLibros <= 0)
            return;
        else
            this.TotalLibros = totalLibros;
    }
    public Boolean getDescuento() {
        return Descuento;
    }
    public void setDescuento(Boolean descuento) {
        if(descuento == null)
            this.Descuento = false;
        else
            this.Descuento = descuento;
    }
    public Double getTotalAPagar() {
        return TotalAPagar;
    }
    public void setTotalAPagar(Double totalAPagar) {
        if(totalAPagar == null || totalAPagar <= 0)
            return;
        else
            this.TotalAPagar = totalAPagar;
    }
    public String getFechaVenta() {
        return FechaVenta;
    }
    public void setFechaVenta(String fechaVenta) {
        if(fechaVenta == null)
            fechaVenta = LocalDate.now().toString();
        else
            this.FechaVenta = fechaVenta;
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
}
