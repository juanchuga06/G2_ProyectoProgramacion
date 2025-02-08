package DataAccessComponent.DTO;

public class PortadaDTO {
    private Integer IdPortada;
    private byte[] Portada;
    private Integer IdLibro;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    
    
    public PortadaDTO(Integer idPortada, byte[] portada, Integer idLibro, String estado, String fechaCreacion,
    String fechaModificacion) {
        setIdPortada(idPortada);
        setPortada(portada);
        setIdLibro(idLibro);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
    }

    public PortadaDTO(Integer idPortada, byte[] portada, Integer idLibro) {
        setIdPortada(idPortada);
        setPortada(portada);
        setIdLibro(idLibro);
    }

    public PortadaDTO(byte[] portada, Integer idLibro) {
        setPortada(portada);
        setIdLibro(idLibro);
    }

    public PortadaDTO() {
    }
    
    public Integer getIdPortada() {
        return IdPortada;
    }
    public void setIdPortada(Integer idPortada) {
        IdPortada = idPortada;
    }
    public byte[] getPortada() {
        return Portada;
    }
    public void setPortada(byte[] portada) {
        Portada = portada;
    }
    public Integer getIdLibro() {
        return IdLibro;
    }
    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModificacion() {
        return FechaModificacion;
    }
    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + IdPortada + " " + IdLibro + " " + Portada.toString();
    }
}
