package DataAccessComponent.DTO;

public class FacturaDTO {
    private Integer IdFactura;
    private String  DireccionLocal;
    private String  TelefonoLocal;
    private String  CorreoElectronico;
    private String  FechaEmicion;
    private String  NumeroFactura;
    private String  FechaModificacion;
    private String  DetallesCompra;
    private String  EstadoFactura;
    private Integer IdCliente;
    private Integer IdVenta;

   

    public FacturaDTO() { }
    
    public FacturaDTO(Integer idFactura, String direccionLocal, String telefonoLocal, String correoElectronivo, 
                    String fechaEmision, String numeroFactura, String fechaModificacion, String detallesCompra, 
                    String estadoFactura, Integer idCliente, Integer idVenta) {

        setIdFactura(idFactura);
        setDireccionLocal(direccionLocal);
        setTelefonoLocal(telefonoLocal);
        setCorreoElectronico(correoElectronivo);
        setFechaEmicion(fechaEmision);
        setNumeroFactura(numeroFactura);
        setFechaModificacion(fechaModificacion);
        setDetallesCompra(detallesCompra);
        setEstadoFactura(estadoFactura);
        setIdCliente(idCliente);
        setIdVenta(idVenta);
    }

     public FacturaDTO(String direccionLocal, String telefonoLocal, String correoElectronico, String numeroFactura, Integer idCliente, String detallesCompra, Integer idVenta){
        DireccionLocal = direccionLocal;
        TelefonoLocal = telefonoLocal;
        CorreoElectronico = correoElectronico;
        NumeroFactura = numeroFactura;
        IdCliente = idCliente;
        DetallesCompra = detallesCompra;
        IdVenta = idVenta;
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }

    public Integer getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(Integer idVenta) {
        IdVenta = idVenta;
    }
    
    public Integer getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(Integer idFactura) {
        IdFactura = idFactura;
    }

    public String getDireccionLocal() {
        return DireccionLocal;
    }

    public void setDireccionLocal(String direccionLocal) {
        DireccionLocal = direccionLocal;
    }

    public String getTelefonoLocal() {
        return TelefonoLocal;
    }

    public void setTelefonoLocal(String telefonoLocal) {
        TelefonoLocal = telefonoLocal;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getFechaEmicion() {
        return FechaEmicion;
    }

    public void setFechaEmicion(String fechaEmicion) {
        FechaEmicion = fechaEmicion;
    }

    public String getNumeroFactura() {
        return NumeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        NumeroFactura = numeroFactura;
    }

    public String getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    public String getDetallesCompra() {
        return DetallesCompra;
    }

    public void setDetallesCompra(String detallesCompra) {
        DetallesCompra = detallesCompra;
    }

    public String getEstadoFactura() {
        return EstadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        EstadoFactura = estadoFactura;
    }

    
    @Override
    public String toString(){
        return getClass().getName() 
        + "\n IdFactura           : "+ getIdFactura    ()
        + "\n DireccionLocal      : "+ getDireccionLocal           ()
        + "\n TelefonoLocal       : "+ getTelefonoLocal           ()
        + "\n CorreoElectronico   : "+ getCorreoElectronico    ()
        + "\n FechaEmicion        : "+ getFechaEmicion()
        + "\n NumeroFactura       : "+ getNumeroFactura()
        + "\n FechaModificacion   : "+ getFechaModificacion()
        + "\n DetallesCompra      : "+ getDetallesCompra()
        + "\n EstadoFactura       : "+ getEstadoFactura()
        + "\n IdCliente           : "+ getIdCliente()
        + "\n IdVenta             : "+ getIdVenta();
        
    }
    
}
