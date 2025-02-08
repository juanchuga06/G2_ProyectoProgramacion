package BusinessLogicComponent.entities.gestores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Libros.Autor;
import BusinessLogicComponent.entities.Libros.Editorial;
import BusinessLogicComponent.entities.Libros.GeneroLibro;
import BusinessLogicComponent.entities.Libros.Libro;
import BusinessLogicComponent.entities.Libros.Portada;
import BusinessLogicComponent.entities.Utilities.ImageUtilities;
import DataAccessComponent.DAO.AutorDAO;
import DataAccessComponent.DAO.EditorialDAO;
import DataAccessComponent.DAO.GeneroLibroDAO;
import DataAccessComponent.DAO.LibroDAO;
import DataAccessComponent.DAO.PortadaDAO;
import DataAccessComponent.DTO.AutorDTO;
import DataAccessComponent.DTO.EditorialDTO;
import DataAccessComponent.DTO.GeneroLibroDTO;
import DataAccessComponent.DTO.LibroDTO;
import DataAccessComponent.DTO.PortadaDTO;

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
        AutorList.clear();
        EditorialList.clear();
        GeneroLibroList.clear();
        LibroList.clear();
        
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
            LibroBL.add(new LibroDTO(libro.getTitulo(), libro.getFechaPublicacion(), libro.getNumeroEdicion(), libro.getNumeroEjemplares(), (new BigDecimal(libro.getPrecio().toString())),
                                     libro.getGeneroLibro().getIdGeneroLibro(), libro.getEditorial().getIdEditorial(),
                                     libro.getAutor().getIdAutor(), libro.getCodigoBarras(), libro.getCodigoISBN()));
        } catch(Exception e){
            System.out.println("Error al registrar el libro");
        }   
    }

    public void actualizarLibro(Libro libro){
        try{
            LibroBL.upd(new LibroDTO(libro.getIdLibro(),libro.getTitulo(), libro.getFechaPublicacion(), libro.getNumeroEdicion(), libro.getNumeroEjemplares(), (new BigDecimal(libro.getPrecio().toString())),
                                        libro.getGeneroLibro().getIdGeneroLibro(), libro.getEditorial().getIdEditorial(),
                                        libro.getAutor().getIdAutor(), libro.getCodigoBarras(), libro.getCodigoISBN()));

        } catch(Exception e){
            System.out.println("Error al actualizar el libro");
        }
    }  

    public boolean eliminarLibro(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            LibroBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar al Libro");
        }
        return false;
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

    public Portada obtenerPortada(Integer idLibro){
        PortadaDAO portadaDAO = new PortadaDAO();
        PortadaDTO pt;
        Portada portadaaux = null;
        try {
            pt = portadaDAO.readByLibro(idLibro);
            if(pt != null){
                portadaaux = new Portada(pt.getIdPortada(), ImageUtilities.bytesToImageIcon(pt.getPortada()), pt.getIdLibro());
                return portadaaux;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return portadaaux;
    }

    public void crearPortada(Portada portada){
        PortadaDAO portadaDAO = new PortadaDAO();
        try {
            portadaDAO.create(new PortadaDTO(ImageUtilities.imageIconToBytes(portada.getPortada(), "png"), portada.getIdLibro()));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void actualizarPortada(Portada portada){
        PortadaDAO portadaDAO = new PortadaDAO();
        try {
            portadaDAO.update(new PortadaDTO(portada.getIdPortada(), ImageUtilities.imageIconToBytes(portada.getPortada(), "png"), portada.getIdLibro()));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void eliminarPortada(Portada portada){
        PortadaDAO portadaDAO = new PortadaDAO();
        try {
            portadaDAO.update(new PortadaDTO(portada.getIdPortada(), ImageUtilities.imageIconToBytes(portada.getPortada(), "png"), portada.getIdLibro()));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }



    // El insertar el código de barras debe aniadirse un poco después de esto más 
    // arriba en la arquitectura del proyecto
    public void actualizarCodigoBarras(){}
}
