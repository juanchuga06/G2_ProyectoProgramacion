package BusinessLogicComponent.entities.Gestores;

import java.util.ArrayList;
import java.util.List;

import BusinessLogicComponent.BLFactory;
import BusinessLogicComponent.entities.Libros.Autor;
import BusinessLogicComponent.entities.Libros.Editorial;
import BusinessLogicComponent.entities.Libros.GeneroLibro;
import DataAccessComponent.DAO.AutorDAO;
import DataAccessComponent.DAO.EditorialDAO;
import DataAccessComponent.DAO.GeneroLibroDAO;
import DataAccessComponent.DTO.AutorDTO;
import DataAccessComponent.DTO.EditorialDTO;
import DataAccessComponent.DTO.GeneroLibroDTO;

public class GestorCatalogoLibro {
    public List<Editorial> EditorialList;
    public List<GeneroLibro> GeneroLibroList;
    public List<Autor> AutorList;

    private BLFactory<EditorialDTO> EditorialBL;
    private BLFactory<GeneroLibroDTO> GeneroLibroBL;
    private BLFactory<AutorDTO> AutorBL;

    public GestorCatalogoLibro(Integer modo) {
        this.AutorList = new ArrayList<>();
        this.EditorialList = new ArrayList<>();
        this.GeneroLibroList = new ArrayList<>();

        this.AutorBL = new BLFactory<>(AutorDAO::new);
        this.EditorialBL = new BLFactory<>(EditorialDAO::new);
        this.GeneroLibroBL = new BLFactory<>(GeneroLibroDAO::new);


    
    }

    // Modo 1: Autores
    // Modo 2: Editoriales
    // Modo 3: Generos de Libro

    public void cargarCatalogoLibro(Integer modo){
        this.AutorList.clear();
        this.EditorialList.clear();
        this.GeneroLibroList.clear();

        Autor autoraux;
        Editorial editorialaux;
        GeneroLibro generoaux;

        switch (modo) {
            case 1:
                try {
                    for(AutorDTO a: AutorBL.getAll()){
                        autoraux = new Autor(a.getIdAutor(),a.getNombreAutor());
                        this.AutorList.add(autoraux);
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar los autores");
                }
                break;
            case 2:
            try {
                for(EditorialDTO e: EditorialBL.getAll()){
                    editorialaux = new Editorial(e.getIdEditorial(),e.getNombreEditorial());
                    this.EditorialList.add(editorialaux);
                }
            } catch (Exception e) {
                System.out.println("Error al cargar las editoriales");
            }
            break;
            case 3:
            try{
                for(GeneroLibroDTO g: GeneroLibroBL.getAll()){
                    generoaux = new GeneroLibro(g.getIdGeneroLibro(),g.getNombreGeneroLibro());
                    this.GeneroLibroList.add(generoaux);
                }
            } catch (Exception e) {
                System.out.println("Error al cargar los generos de libros");
            }
            break;
            default:
                break;
        }
    }


    public void registrarAutor(Autor autor){
        if(autor == null)
            return;
        try {
            AutorBL.add(new AutorDTO(autor.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el autor");
        }
    }

    public void registrarEditorial(Editorial editorial){
        if(editorial == null)
            return;
        try {
            EditorialBL.add(new EditorialDTO(editorial.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar la editorial");
        }
    }

    public void registrarGeneroLibro(GeneroLibro generoLibro){
        if(generoLibro == null)
            return;
        try {
            GeneroLibroBL.add(new GeneroLibroDTO(generoLibro.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el genero de libro");
        }
    }

    public void actualizarAutor(Autor autor){
        if(autor == null)
            return;
        try{
            AutorBL.upd(new AutorDTO(autor.getIdAutor(), autor.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al actualizar el autor");
        }
    }

    public void actualizarEditorial(Editorial editorial){
        if(editorial == null)
            return;
        try{
            EditorialBL.upd(new EditorialDTO(editorial.getIdEditorial(), editorial.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al actualizar la editorial");
        }
    }

    public void actualizarGeneroLibro(GeneroLibro generoLibro){
        if(generoLibro == null)
            return;
        try{
            GeneroLibroBL.upd(new GeneroLibroDTO(generoLibro.getIdGeneroLibro(), generoLibro.getNombre()));
        } catch (Exception e) {
                System.out.println("Error al actualizar el genero de libro");
        }
    }

    public void eliminarAutor(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            AutorBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar el autor");
        }
    }

    public void eliminarEditorial(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            EditorialBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar la editorial");
        }
    }

    public void eliminarGeneroLibro(Integer id) throws Exception{
        if(id == null || id <= 0)
            return;
        try{
            GeneroLibroBL.del(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar el genero de libro");
        }
    }

}
