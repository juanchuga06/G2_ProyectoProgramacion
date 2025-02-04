import DataAccessComponent.DAO.ClienteDAO;
import DataAccessComponent.DAO.EstadoCivilDAO;
import DataAccessComponent.DTO.ClienteDTO;
import DataAccessComponent.DTO.EstadoCivilDTO;

public class App {
    public static void main(String[] args) throws Exception {
        // try {
        //     SexoDAO sDao = new SexoDAO();
        //     for (SexoDTO s :  sDao.readAll())
        //         System.out.println(s.toString());
        //     System.out.println("-------------");
        //     System.out.println(sDao.readBy(2));
        //     System.out.println(sDao.readBy(3));
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        // try {
        //     AutorDAO aDao = new AutorDAO();
        //     for (AutorDTO a :  aDao.readAll())
        //         System.out.println(a.toString());
        //     System.out.println("-------------");
        //     System.out.println(aDao.readBy(2));
        //     System.out.println(aDao.readBy(7));
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        // try {
        //     DireccionDAO dDao = new DireccionDAO();
        //     for (DireccionDTO d :  dDao.readAll())
        //         System.out.println(d.toString());
        //     System.out.println("-------------");
        //     System.out.println(dDao.readBy(3));
        //     System.out.println(dDao.readBy(6));
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        // try {
        //     ClienteDAO dDao = new ClienteDAO();
        //     for (ClienteDTO d :  dDao.readAll())
        //         System.out.println(d.toString());
        //     System.out.println("-------------");
        //     System.out.println(dDao.readBy(2));
        //     System.out.println(dDao.readBy(10));
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

           try {
            EstadoCivilDAO sDao = new EstadoCivilDAO();
            EstadoCivilDTO sd = new EstadoCivilDTO();
           
            sDao.delete(6);
        //sDao.create(5);
            for (EstadoCivilDTO s :  sDao.readAll())
                System.out.println(s.toString());
            System.out.println("-------------");
            System.out.println(sDao.readBy(2));
            System.out.println(sDao.readBy(4));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
