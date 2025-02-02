package BusinessLogic.entities.Transacciones;

import java.time.LocalDate;

public class Factura {
    private Integer IdFactura;
    private String DireccionLocal;
    private String TelefonoLocal;
    private String CorreoElectronico;
    private String FechaEmision;
    private String NumeroFactura;
    private String DetallesCompra;
    private Venta Venta;

    public Factura(Integer idFactura, String direccionLocal, String telefonoLocal, String correoElectronico,
            String fechaEmision, String numeroFactura, String detallesCompra, Venta venta) {
        setIdFactura(idFactura);
        setDireccionLocal(direccionLocal);
        setTelefonoLocal(telefonoLocal);
        setCorreoElectronico(correoElectronico);
        setFechaEmision(fechaEmision);
        setNumeroFactura(numeroFactura);
        setDetallesCompra(detallesCompra);
        setVenta(venta);
    }

    public Factura(String direccionLocal, String telefonoLocal, String correoElectronico,
            String fechaEmision, String numeroFactura, String detallesCompra, Venta venta) {
        setDireccionLocal(direccionLocal);
        setTelefonoLocal(telefonoLocal);
        setCorreoElectronico(correoElectronico);
        setFechaEmision(fechaEmision);
        setNumeroFactura(numeroFactura);
        setDetallesCompra(detallesCompra);
        setVenta(venta);
    }


    public Integer getIdFactura() {
        return IdFactura;
    }
    public void setIdFactura(Integer idFactura) {
        if(idFactura == null || idFactura <= 0) 
            return;
        else
            this.IdFactura = idFactura;
    }
    public String getDireccionLocal() {
        return DireccionLocal;
    }
    public void setDireccionLocal(String direccionLocal) {
        if(direccionLocal == null || direccionLocal.isEmpty())
            this.DireccionLocal = "Av. Amazonas 123";
        else
            this.DireccionLocal = direccionLocal;
    }
    public String getTelefonoLocal() {
        return TelefonoLocal;
    }
    public void setTelefonoLocal(String telefonoLocal) {
        if(telefonoLocal == null || telefonoLocal.isEmpty())
            this.TelefonoLocal = "0998745632";
        else
            this.TelefonoLocal = telefonoLocal;
    }
    public String getCorreoElectronico() {
        return CorreoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        if(correoElectronico == null || correoElectronico.isEmpty())
            this.CorreoElectronico = "contacto@MrBookQuito.com";
        else
            this.CorreoElectronico = correoElectronico;
    }
    public String getFechaEmision() {
        return FechaEmision;
    }
    public void setFechaEmision(String fechaEmision) {
        if(fechaEmision == null || fechaEmision.isEmpty())
            this.FechaEmision = LocalDate.now().toString();
        else 
            this.FechaEmision = fechaEmision;
    }
    public String getNumeroFactura() {
        return NumeroFactura;
    }
    public void setNumeroFactura(String numeroFactura) {
        if(numeroFactura == null || numeroFactura.isEmpty())
            this.NumeroFactura = "9999999";
        else
            this.NumeroFactura = numeroFactura;
    }
    public String getDetallesCompra() {
        return DetallesCompra;
    }
    public void setDetallesCompra(String detallesCompra) {
        if(detallesCompra == null || detallesCompra.isEmpty())
            this.DetallesCompra = "Compra de libros";
        else
            this.DetallesCompra = detallesCompra;
    }
    public Venta getVenta() {
        return Venta;
    }
    public void setVenta(Venta venta) {
        if(venta == null)
            return;
        else
            this.Venta = venta;
    }






}
