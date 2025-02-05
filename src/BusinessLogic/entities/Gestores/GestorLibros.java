package BusinessLogic.entities.Gestores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.BLFactory;
import BusinessLogic.entities.Libros.Autor;
import BusinessLogic.entities.Libros.Editorial;
import BusinessLogic.entities.Libros.GeneroLibro;
import BusinessLogic.entities.Libros.Libro;
import DataAccessComponent.DAO.AutorDAO;
import DataAccessComponent.DAO.EditorialDAO;
import DataAccessComponent.DAO.GeneroLibroDAO;
import DataAccessComponent.DAO.LibroDAO;
import DataAccessComponent.DTO.AutorDTO;
import DataAccessComponent.DTO.EditorialDTO;
import DataAccessComponent.DTO.GeneroLibroDTO;
import DataAccessComponent.DTO.LibroDTO;

public class GestorLibros {
    public List<Editorial> EditorialList;
    public List<GeneroLibro> GeneroLibroList;
    public List<Autor> AutorList;
    public List<Libro> LibroList;

    private BLFactory<EditorialDTO> EditorialBL;
    private BLFactory<GeneroLibroDTO> GeneroLibroBL;
    private BLFactory<AutorDTO> AutorBL;
    private BLFactory<LibroDTO> LibroBL;

    public GestorLibros() {
        this.AutorList = new ArrayList<>();
        this.EditorialList = new ArrayList<>();
        this.GeneroLibroList = new ArrayList<>();
        this.LibroList = new ArrayList<>();

        this.AutorBL = new BLFactory<>(AutorDAO::new);
        this.EditorialBL = new BLFactory<>(EditorialDAO::new);
        this.GeneroLibroBL = new BLFactory<>(GeneroLibroDAO::new);
        this.LibroBL = new BLFactory<>(LibroDAO::new);

        cargarLibros();
    }

    public void cargarLibros(){
        Autor autoraux;
        Editorial editorialaux;
        GeneroLibro generoaux;

        try {
            for(AutorDTO a: AutorBL.getAll()){
                autoraux = new Autor(a.getIdAutor(),a.getNombreAutor());
                this.AutorList.add(autoraux);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los autores");
        }

        try {
            for(EditorialDTO e: EditorialBL.getAll()){
                editorialaux = new Editorial(e.getIdEditorial(),e.getNombreEditorial());
                this.EditorialList.add(editorialaux);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las editoriales");
        }
        
        try{
            for(GeneroLibroDTO g: GeneroLibroBL.getAll()){
                generoaux = new GeneroLibro(g.getIdGeneroLibro(),g.getNombreGeneroLibro());
                this.GeneroLibroList.add(generoaux);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los generos de libros");
        }

        try{
            for(LibroDTO l: LibroBL.getAll()){
                Libro libroaux = new Libro(l.getIdLibro(), l.getTitulo(), l.getNumeroEdicion(), l.getNumeroEjemplares(),
                                           l.getFechaPublicacion(), l.getPrecio().doubleValue(), getGeneroByID(l.getIdGeneroLibro()), 
                                           getEditorialByID(l.getIdEditorial()), getAutorByID(l.getIdAutor()), 
                                           l.getCodigoBarras(), l.getCodigoISBN());
                this.LibroList.add(libroaux);
                }
        } catch (Exception e) {
            System.out.println("Error al cargar los libros");
        }
    }
    
    public void registrarLibro(Libro libro){
        if(libro == null)
            return;
        try{
            LibroBL.add(new LibroDTO(libro.getTitulo(), libro.getFechaPublicacion(), (new BigDecimal(libro.getPrecio().toString())),
                                     libro.getGeneroLibro().getIdGeneroLibro(), libro.getEditorial().getIdEditorial(),
                                     libro.getAutor().getIdAutor(), libro.getCodigoISBN(), libro.getCodigoBarras()));
        } catch(Exception e){
            System.out.println("Error al registrar el libro");
        }   
    }

    public void actualizarLibro(Libro libro){
        try{
            LibroBL.upd(new LibroDTO(libro.getTitulo(), libro.getFechaPublicacion(), (new BigDecimal(libro.getPrecio().toString())),
                                        libro.getGeneroLibro().getIdGeneroLibro(), libro.getEditorial().getIdEditorial(),
                                        libro.getAutor().getIdAutor(), libro.getCodigoISBN(), libro.getCodigoBarras()));

        } catch(Exception e){
            System.out.println("Error al actualizar el libro");
        }
    }  

    public void eliminarLibro(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            LibroBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar al Libro");
        }
    }

    private Autor getAutorByID(Integer ID){
        Autor autoraux = new Autor();
        for(Autor a: this.AutorList){
            if(a.getIdAutor().equals(ID)){
                autoraux = a;
                break;
            }
        }
        return autoraux;
    }

    private GeneroLibro getGeneroByID(Integer ID){
        GeneroLibro generoaux = new GeneroLibro();
        for(GeneroLibro g: this.GeneroLibroList){
            if(g.getIdGeneroLibro().equals(ID)){
                generoaux = g;
                break;
            }
        }
        return generoaux;
    }

    private Editorial getEditorialByID(Integer ID){
        Editorial editorialaux = new Editorial();
        for(Editorial e: this.EditorialList){
            if(e.getIdEditorial().equals(ID)){
                editorialaux = e;
                break;
            }
        }        
        return editorialaux;
    }

    public Libro BuscarLibroPorISBN(String ISBN){
        Libro libroaux = new Libro();
        for(Libro l: this.LibroList){
            if(l.getCodigoISBN().equals(ISBN)){
                libroaux = l;
                break;
            }
        }
        return libroaux;
    }

    public Libro BuscarLibroPorCodigoBarras(String codigoBarras){
        Libro libroaux = new Libro();
        for(Libro l: this.LibroList){
            if(l.getCodigoBarras().equals(codigoBarras)){
                libroaux = l;
                break;
            }
        }
        return libroaux;
    }

    public Libro getLibroByID(Integer id){
        Libro libroaux = new Libro();
        for(Libro l: this.LibroList){
            if(l.getIdLibro().equals(id)){
                libroaux = l;
                break;
            }
        }
        return libroaux;
    }
    // El insertar el código de barras debe aniadirse un poco después de esto más 
    // arriba en la arquitectura del proyecto
    public void actualizarCodigoBarras(){}
}
