import DataAccessComponent.DAC.BibliotecarioDAO;
import DataAccessComponent.DAC.EditorialDAO;
import DataAccessComponent.DAC.EstadoCivilDAO;
import DataAccessComponent.DAC.LibroDAO;
import DataAccessComponent.DTO.LibroDTO;
import DataAccessComponent.DTO.BibliotecarioDTO;
import DataAccessComponent.DTO.EditorialDTO;
import DataAccessComponent.DTO.EstadoCivilDTO;

public class App {
    public static void main(String[] args) throws Exception {
         
        try {
            LibroDAO sDao = new LibroDAO();
            // EstadoCivilDTO sd = new EstadoCivilDTO();
           
        //sDao.create(5);
            for (LibroDTO s :  sDao.readAll())
                System.out.println(s.toString());
            System.out.println("-------------");
            System.out.println(sDao.readBy(2));
            System.out.println(sDao.readBy(4));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
       
    }
}
