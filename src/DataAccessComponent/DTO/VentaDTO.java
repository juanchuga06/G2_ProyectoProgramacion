package DataAccessComponent.DTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class VentaDTO {
    private Integer IdVenta;
    private Integer CantidadLibros;
    private BigDecimal TotalLibros;
    private Boolean Descuento;
    private BigDecimal TotalPagar;
    private String  Estado;
    private String  FechaVenta;
    private String  FechaModificacion;
    private Integer IdLibro;
    private Integer IdCliente;
    private Integer IdBibliotecario;

    public VentaDTO() { }
    
    public VentaDTO(Integer idVenta, Integer cantidadLibros, BigDecimal totalLibros, Boolean descuento,
    BigDecimal totalPagar, String estado, String fechaVenta, String fechaModificacion, Integer idLibro,
            Integer idCliente, Integer idBibliotecario) {
            setIdVenta(idVenta);
            setCantidadLibros(cantidadLibros);
            setTotalLibros(totalLibros);
            setDescuento(descuento);
            setTotalPagar(totalPagar);
            setEstado(estado);
            setFechaVenta(fechaVenta);
            setFechaModificacion(fechaModificacion);
            setIdLibro(idLibro);
            setIdCliente(idCliente);
            setIdBibliotecario(idBibliotecario);
    }

    public VentaDTO(Integer cantidadLibros, BigDecimal totalLibros, Boolean descuento, BigDecimal totalPagar,
    Integer idLibro, Integer idCliente, Integer idBibliotecario) {
        CantidadLibros = cantidadLibros;
        TotalLibros = totalLibros;
        Descuento = descuento;
        TotalPagar = totalPagar;
        IdLibro = idLibro;
        IdCliente = idCliente;
        IdBibliotecario = idBibliotecario;
    }

    public Integer getIdVenta() {
        return IdVenta;
    }
    public void setIdVenta(Integer idVenta) {
        IdVenta = idVenta;
    }
    public Integer getCantidadLibros() {
        return CantidadLibros;
    }
    public void setCantidadLibros(Integer cantidadLibros) {
        CantidadLibros = cantidadLibros;
    }
    public BigDecimal getTotalLibros() {
        return TotalLibros;
    }
    public void setTotalLibros(BigDecimal totalLibros) {
        TotalLibros = totalLibros;
    }
    public Boolean getDescuento() {
        return Descuento;
    }
    public void setDescuento(Boolean descuento) {
        Descuento = descuento;
    }
    public BigDecimal getTotalPagar() {
        return TotalPagar;
    }
    public void setTotalPagar(BigDecimal totalPagar) {
        TotalPagar = totalPagar;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado (String estado) {
        Estado = estado;
    }
    public String getFechaVenta() {
        return FechaVenta;
    }
    public void setFechaVenta(String fechaVenta) {
        FechaVenta = fechaVenta;
    }
    public String getFechaModificacion() {
        return FechaModificacion;
    }
    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }
    public Integer getIdLibro() {
        return IdLibro;
    }
    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }
    public Integer getIdCliente() {
        return IdCliente;
    }
    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }
    public Integer getIdBibliotecario() {
        return IdBibliotecario;
    }
    public void setIdBibliotecario(Integer idBibliotecario) {
        IdBibliotecario = idBibliotecario;
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdVenta               :" + getIdVenta()
        + "\n CantidadLibros        :" + getCantidadLibros()
        + "\n TotalLibros           :" + getTotalLibros()
        + "\n Descuento             :" + getDescuento()
        + "\n TotalPagar            :" + getTotalPagar()
        + "\n Estado               :" + getEstado()
        + "\n FechaVenta            :" + getFechaVenta()
        + "\n FechaModificacion     :" + getFechaModificacion()
        + "\n IdLibro               :" + getIdLibro()
        + "\n IdCliente             :" + getIdCliente()
        + "\n IdBibliotecario       :" + getIdBibliotecario();
    }

}
