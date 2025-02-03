import javax.swing.SwingUtilities;

import DataAccessComponent.DAO.AlquilerDAO;
import DataAccessComponent.DAO.EstadoAlquilerDAO;
import DataAccessComponent.DTO.AlquilerDTO;
import DataAccessComponent.DTO.EstadoAlquilerDTO;
import UserInterface.Form.LoginFrame;

public class App {
    public static void main(String[] args) throws Exception {
        //SwingUtilities.invokeLater(LoginFrame :: new);

        //  try {
        //      AlquilerDAO oA = new AlquilerDAO();
        //      AlquilerDTO oA1 = new AlquilerDTO();
        //      oA1.setFechaAlquiler("12/04/1542");
        //      oA1.setFechaDevolucion("20/04/1542");
        //      oA1.setIdLibro(2);
        //      oA1.setIdCliente(3) ;
        //      oA1.setIdBibliotecario(2) ;
        //      oA1.setIdEstadoAlquiler(1);
        //      oA1.setIdAlquiler(9);
        //      
        //     //  oA.create(oA1);
        //     //  oA.delete(21);
        //     //  oA.delete(22);
          //    for (AlquilerDTO a :  oA.readAll())
          //       System.out.println(a.toString());
          //    System.out.println("-------------");
          //    System.out.println(oA.readBy(2));
          //    System.out.println(oA.readBy(7));
          //} catch (Exception e) {
          //    System.out.println(e.toString());
          //}


        


         try {
            EstadoAlquilerDAO eA = new EstadoAlquilerDAO();
            EstadoAlquilerDTO eA1 = new EstadoAlquilerDTO();
            // eA1.setNombre("jaja");
             //eA1.setEstado("A");
             //eA1.setFechaCreacion("12/04/7777");
             //eA1.setFechaModificaion("10/12/3333");
             //eA1.setIdEstadoAlquiler(4);
             //eA.update(eA1);
            //eA.create(eA1);
            eA.delete(3);


            for(EstadoAlquilerDTO e : eA.readAll())
            System.out.println(e.toString());
            System.out.println("-----------------");
            System.out.println(eA.readBy(2));
            System.out.println(eA.readBy(7));
         } catch (Exception e) {
            System.err.println(e.toString());
         }



















    }
}