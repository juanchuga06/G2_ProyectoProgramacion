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

import javax.swing.table.DefaultTableModel;

public class GestorCatalogoLibro {
    private DefaultTableModel modeloTabla = new DefaultTableModel();
    public List<Editorial> EditorialList;
    public List<GeneroLibro> GeneroLibroList;
    public List<Autor> AutorList;
    

    private BLFactory<EditorialDTO> EditorialBL;
    private BLFactory<GeneroLibroDTO> GeneroLibroBL;
    private BLFactory<AutorDTO> AutorBL;

    public GestorCatalogoLibro(Integer modo) {
        System.out.println("GestorCatalogoLibro creado con modo: " + modo);
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

    public void cargarCatalogoLibro(Integer modo) throws Exception{
         this.AutorList = new ArrayList<>();
         for (AutorDTO autorDTO : AutorBL.getAll()) {
             this.AutorList.add(new Autor(autorDTO.getIdAutor(), autorDTO.getNombreAutor()));
         }
        this.EditorialList = new ArrayList<>();
        for (EditorialDTO editorialDTO : EditorialBL.getAll()) {
            this.EditorialList.add(new Editorial(editorialDTO.getIdEditorial(), editorialDTO.getNombreEditorial()));
        }
        this.GeneroLibroList = new ArrayList<>();
        for (GeneroLibroDTO generoLibroDTO : GeneroLibroBL.getAll()) {
            this.GeneroLibroList.add(new GeneroLibro(generoLibroDTO.getIdGeneroLibro(), generoLibroDTO.getNombreGeneroLibro()));
        }


        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);
        
        
    
        switch (modo) {
            case 1: // Autores
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (Autor autor : this.AutorList) {
                    System.out.println("Autor: " + autor.getIdAutor() + " - " + autor.getNombre());
                    modeloTabla.addRow(new Object[]{autor.getIdAutor(), autor.getNombre()});
                }
                break;
            case 2: // Editoriales
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (Editorial editorial : this.EditorialList) {
                    System.out.println("Editorial: " + editorial.getIdEditorial() + " - " + editorial.getNombre());
                    modeloTabla.addRow(new Object[]{editorial.getIdEditorial(), editorial.getNombre()});
                }
                break;
            case 3: // Géneros
                modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre"});
                for (GeneroLibro genero : this.GeneroLibroList) {
                    System.out.println("Género: " + genero.getIdGeneroLibro() + " - " + genero.getNombre());
                    modeloTabla.addRow(new Object[]{genero.getIdGeneroLibro(), genero.getNombre()});
                }
                break;
        }
        
        modeloTabla.fireTableDataChanged(); // Asegurar que la tabla se actualiza
    }


    public void registrarAutor(Autor autor) {
        if (autor == null) return;
        try {
            AutorBL.add(new AutorDTO(autor.getNombre()));
        } catch (Exception e) {
            System.out.println("Error al registrar el autor: " + e.getMessage());
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

    public boolean eliminarAutor(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            AutorBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el autor");
        }
        return false;
    }

    public boolean eliminarEditorial(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            EditorialBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar la editorial");
        }
        return false;
    }

    public boolean eliminarGeneroLibro(Integer id) throws Exception{
        if(id == null || id <= 0)
            return false;
        try{
            GeneroLibroBL.del(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el genero de libro");
        }
        return false;
    }

}
