package DataAccessComponent.DAO;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.BibliotecarioDTO;
import DataAccessComponent.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO extends SQLiteDataHelper implements IDAO<BibliotecarioDTO> {
    @Override
    public BibliotecarioDTO readBy(Integer id) throws Exception {
        BibliotecarioDTO bb = new BibliotecarioDTO();
        String query =" SELECT b.idBibliotecario                 "
                     +" ,b.Nombre                                "
                     +" ,b.Apellido                              "
                     +" ,b.Cedula                                "
                     +" ,b.Telefono                              "
                     +" ,b.CorreoElectronico                     "
                     +" ,b.Usuario                               "
                     +" ,b.Contrasenia                           "
                     +" ,b.Estado                                "
                     +" ,b.FechaCreacion                         "
                     +" ,b.FechaModificacion                     "
                     +" ,ec.IdEstadoCivil                        "
                     +" ,s.IdSexo                                "
                     +" FROM Bibliotecario as b                  "
                     +" INNER JOIN EstadoCivil as ec ON b.IdEstadoCivil = ec.IdEstadoCivil"
                     +" INNER JOIN Sexo as s ON b.IdSexo = s.IdSexo       "
                     +" WHERE   b.Estado ='A'  AND IdBibliotecario =   " + id.toString() ;
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                bb = new BibliotecarioDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5)
                                ,rs.getString(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getString(9)
                                ,rs.getString(10)
                                ,rs.getString(11)
                                ,rs.getInt(12)
                                ,rs.getInt(13));
            }
        } 
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return bb;
    }

    @Override
    public List<BibliotecarioDTO> readAll() throws Exception {
         List <BibliotecarioDTO> lst = new ArrayList<>();
         String query ="SELECT  b.idBibliotecario                 "
                      +" ,b.Nombre                                "
                      +" ,b.Apellido                              "
                      +" ,b.Cedula                                "
                      +" ,b.Telefono                              "
                      +" ,b.CorreoElectronico                     "
                      +" ,b.Usuario                               "
                      +" ,b.Contrasenia                           "
                      +" ,b.Estado                                "
                      +" ,b.FechaCreacion                         "
                      +" ,b.FechaModificacion                     "
                      +" ,ec.IdEstadoCivil                        "
                      +" ,s.IdSexo                                "
                      +" FROM Bibliotecario as b                  "
                      +" INNER JOIN EstadoCivil as ec ON b.IdEstadoCivil = ec.IdEstadoCivil"
                      +" INNER JOIN Sexo as s ON b.IdSexo = s.IdSexo  "
                      +" WHERE   b.Estado ='A'";

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery(query);
            while (rs.next()) {
                BibliotecarioDTO bb = new BibliotecarioDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5)
                                ,rs.getString(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getString(9)
                                ,rs.getString(10)
                                ,rs.getString(11)
                                ,rs.getInt(12)
                                ,rs.getInt(13));
                lst.add(bb);
            }
        }
        catch (SQLException e) {
        throw e; 
        }
        return lst;
    }

    @Override
    public boolean create(BibliotecarioDTO entity) throws Exception {
        String query = " INSERT INTO Bibliotecario(Nombre, Apellido, Cedula, Telefono, CorreoElectronico, Usuario,"
        + "Contrasenia, IdEstadoCivil, IdSexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getCedula());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getCorreoElectronico());
            pstmt.setString(6, entity.getUsuario());
            pstmt.setString(7, entity.getContrasenia());
            pstmt.setInt(8, entity.getIdEstadoCivil());
            pstmt.setInt(9, entity.getIdSexo());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(BibliotecarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Bibliotecario SET nombre = ?, apellido = ?, cedula = ?, telefono = ?, correoElectronico = ?, usuario = ?, Contrasenia = ?, fechaModificacion = ?,"
        +" IdEstadoCivil = ?, IdSexo = ? WHERE idBibliotecario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getCedula());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getCorreoElectronico());
            pstmt.setString(6, entity.getUsuario());
            pstmt.setString(7, entity.getContrasenia());
            pstmt.setString(10, dtf.format(now).toString());
            pstmt.setInt(11, entity.getIdEstadoCivil());
            pstmt.setInt(12, entity.getIdSexo());
            pstmt.setInt(13, entity.getIdBibliotecario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Bibliotecario SET estado = ? WHERE idBibliotecario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) AS TotalReg FROM Bibliotecario WHERE estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()){
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }
    
}
