package DataAccessComponent.DTO;

import java.math.BigDecimal;

public class LibroDTO {
    private Integer IdLibro;             
    private String Titulo;              
    private Integer NumeroEdicion;       
    private Integer NumeroEjemplares;    
    private String FechaPublicacion; 
    private BigDecimal  Precio;   
    private String Estado;              
    private String FechaCreacion;       
    private String FechaModificacion;  
    private Integer IdGeneroLibro;       
    private Integer IdEditorial;         
    private Integer IdAutor;            
    private String CodigoBarras;        
    private String CodigoISBN;

    public LibroDTO(){

    }

    
    public LibroDTO(Integer idLibro, String titulo, Integer numeroEdicion, Integer numeroEjemplares,
            String fechaPublicacion, BigDecimal precio, String estado, String fechaCreacion, String fechaModificacion,
            Integer idGeneroLibro, Integer idEditorial, Integer idAutor, String codigoBarras, String codigoISBN) {
        setIdLibro(idLibro);
        setTitulo(titulo);
        setNumeroEdicion(numeroEdicion);
        setNumeroEjemplares(numeroEjemplares);
        setFechaPublicacion(fechaPublicacion);
        setPrecio(precio);
        setEstado(estado);
        setFechaCreacion(fechaCreacion);
        setFechaModificacion(fechaModificacion);
        setIdGeneroLibro(idGeneroLibro);
        setIdEditorial(idEditorial);
        setIdAutor(idAutor);
        setCodigoBarras(codigoBarras);
        setCodigoISBN(codigoISBN);
    }

    public LibroDTO(String titulo, String fechaPublicacion, BigDecimal precio, Integer idGeneroLibro, Integer idEditorial, Integer idAutor, String codigoBarras, String codigoISBN){
        Titulo = titulo;
        FechaPublicacion = fechaPublicacion;
        Precio = precio;
        IdGeneroLibro = idGeneroLibro;
        IdEditorial = idEditorial;
        IdAutor = idAutor;
        CodigoBarras = codigoBarras;
        CodigoISBN = codigoISBN;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }


    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    public Integer getIdGeneroLibro() {
        return IdGeneroLibro;
    }


    public void setIdGeneroLibro(Integer idGeneroLibro) {
        IdGeneroLibro = idGeneroLibro;
    }


    public Integer getIdEditorial() {
        return IdEditorial;
    }


    public void setIdEditorial(Integer idEditorial) {
        IdEditorial = idEditorial;
    }


    public Integer getIdAutor() {
        return IdAutor;
    }


    public void setIdAutor(Integer idAutor) {
        IdAutor = idAutor;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getNumeroEdicion() {
        return NumeroEdicion;
    }

    public void setNumeroEdicion(Integer numeroEdicion) {
        NumeroEdicion = numeroEdicion;
    }

    public Integer getNumeroEjemplares() {
        return NumeroEjemplares;
    }

    public void setNumeroEjemplares(Integer numeroEjemplares) {
        NumeroEjemplares = numeroEjemplares;
    }

    public String getFechaPublicacion() {
        return FechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        FechaPublicacion = fechaPublicacion;
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

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
    }

    public String getCodigoISBN() {
        return CodigoISBN;
    }

    public void setCodigoISBN(String codigoISBN) {
        CodigoISBN = codigoISBN;
    }
    
    public String toString(){
        return getClass().getName() 
        + "\n IdLibro           :"+ getIdLibro          ()
        + "\n Titulo            :"+ getTitulo           ()
        + "\n NumeroEdicion     :"+ getNumeroEdicion    ()
        + "\n NumeroEjemplares  :"+ getNumeroEjemplares ()
        + "\n FechaPublicacion  :"+ getFechaPublicacion ()
        + "\n Precio            :"+ getPrecio           ()
        + "\n Estado            :"+ getEstado           ()
        + "\n FechaCreacion     :"+ getFechaCreacion    ()
        + "\n FechaModificacion :"+ getFechaModificacion()
        + "\n IdGeneroLibro     :"+ getIdGeneroLibro    ()
        + "\n IdEditorial       :"+ getIdEditorial      ()
        + "\n IdAutor           :"+ getIdAutor          ()
        + "\n CodigoBarras      :"+ getCodigoBarras     ()   
        + "\n CodigoISBN        :"+ getCodigoISBN       () + "\n";
        
    }
}
