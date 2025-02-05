import DataAccessComponent.DAC.BibliotecarioDAO;
import DataAccessComponent.DAC.EditorialDAO;
import DataAccessComponent.DAC.EstadoCivilDAO;
import DataAccessComponent.DAC.FacturaDAO;
import DataAccessComponent.DAC.LibroDAO;
import DataAccessComponent.DAC.VentaDAO;
import DataAccessComponent.DTO.LibroDTO;
import DataAccessComponent.DTO.VentaDTO;
import DataAccessComponent.DTO.BibliotecarioDTO;
import DataAccessComponent.DTO.EditorialDTO;
import DataAccessComponent.DTO.EstadoCivilDTO;
import DataAccessComponent.DTO.FacturaDTO;

public class App {
    public static void main(String[] args) throws Exception {
         
        try {
            FacturaDAO sDao = new FacturaDAO();
            // EstadoCivilDTO sd = new EstadoCivilDTO();
           
        //sDao.create(5);
            for (FacturaDTO s :  sDao.readAll())
                System.out.println(s.toString());
            System.out.println("-------------");
            System.out.println(sDao.readBy(2));
            System.out.println(sDao.readBy(4));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
       
    }
}
