package BusinessLogicComponent.entities.Libros;


public class Libro {
    private Integer IdLibro;
    private String Titulo;
    private Integer NumeroEdicion;
    private Integer NumeroEjemplares;
    private String FechaPublicacion;
    private Double Precio;
    private GeneroLibro GeneroLibro;
    private Editorial Editorial;
    private Autor Autor;
    private String CodigoBarras;
    private String CodigoISBN;

    public Libro() {
        }
    public Libro(Integer idLibro, String titulo, Integer numeroEdicion, Integer numeroEjemplares,
            String fechaPublicacion, Double precio, GeneroLibro generoLibro, Editorial editorial, Autor autor,
            String codigoBarras, String codigoISBN) {
        setIdLibro(idLibro);
        setTitulo(titulo);
        setNumeroEdicion(numeroEdicion);
        setNumeroEjemplares(numeroEjemplares);
        setFechaPublicacion(fechaPublicacion);
        setPrecio(precio);
        setGeneroLibro(generoLibro);
        setEditorial(editorial);
        setAutor(autor);
        setCodigoBarras(codigoBarras);
        setCodigoISBN(codigoISBN);
    }


    public Libro(String titulo, Integer numeroEdicion, Integer numeroEjemplares,
            String fechaPublicacion, Double precio, GeneroLibro generoLibro, Editorial editorial, Autor autor, String codigoISBN, String codigoBarras) {
        setTitulo(titulo);
        setNumeroEdicion(numeroEdicion);
        setNumeroEjemplares(numeroEjemplares);
        setFechaPublicacion(fechaPublicacion);
        setPrecio(precio);
        setGeneroLibro(generoLibro);
        setEditorial(editorial);
        setAutor(autor);
        setCodigoBarras(codigoBarras);
        setCodigoISBN(codigoISBN);
    }
    public Double getPrecio() {
        return Precio;
    }
    public void setPrecio(Double precio) {
        if(precio == null || precio < 0)
            return;
        else
            this.Precio = precio;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }
    public void setIdLibro(Integer idLibro) {
        if(idLibro == null || idLibro <= 0)
            return;
        else
            this.IdLibro = idLibro;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        if(titulo == null || titulo.isEmpty())
            this.Titulo = "TituloComun";
        else
            this.Titulo = titulo;
    }
    public Integer getNumeroEdicion() {
        return NumeroEdicion;
    }
    public void setNumeroEdicion(Integer numeroEdicion) {
        if(numeroEdicion == null || numeroEdicion <= 0)
            this.NumeroEdicion = 1;
        else
            this.NumeroEdicion = numeroEdicion;
    }
    public Integer getNumeroEjemplares() {
        return NumeroEjemplares;
    }
    public void setNumeroEjemplares(Integer numeroEjemplares) {
        if(numeroEjemplares == null || numeroEjemplares < 0)
            this.NumeroEjemplares = 0;
        else
            this.NumeroEjemplares = numeroEjemplares;
    }
    public String getFechaPublicacion() {
        return FechaPublicacion;
    }
    public void setFechaPublicacion(String fechaPublicacion) {
        if(fechaPublicacion == null || fechaPublicacion.isEmpty())
            this.FechaPublicacion = "9999";
        else
            this.FechaPublicacion = fechaPublicacion;
    }
    public GeneroLibro getGeneroLibro() {
        return GeneroLibro;
    }
    public void setGeneroLibro(GeneroLibro generoLibro) {
        if(generoLibro == null)
            return;
        else
            this.GeneroLibro = generoLibro;
    }
    public Editorial getEditorial() {
        return Editorial;
    }
    public void setEditorial(Editorial editorial) {
        if(editorial == null)
            return;
        else
            this.Editorial = editorial;
    }
    public Autor getAutor() {
        return Autor;
    }
    public void setAutor(Autor autor) {
        if(autor == null)
            return;
        else 
            this.Autor = autor;
    }
    public String getCodigoBarras() {
        return CodigoBarras;
    }
    public void setCodigoBarras(String codigoBarras) {
        if(codigoBarras == null || codigoBarras.isEmpty())
            return;
        else
            this.CodigoBarras = codigoBarras;
    }
    public String getCodigoISBN() {
        return CodigoISBN;
    }
    public void setCodigoISBN(String codigoISBN) {
        if(codigoISBN == null || codigoISBN.isEmpty())
            this.CodigoISBN = this.CodigoBarras;
        else
            this.CodigoISBN = codigoISBN;
    }
    @Override
    public String toString(){
            return getTitulo();
    }

}
