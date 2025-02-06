package DataAccessComponent.DAO;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.EditorialDTO;
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

public class EditorialDAO extends SQLiteDataHelper implements IDAO<EditorialDTO> {
    @Override
    public EditorialDTO readBy(Integer id) throws Exception {
        EditorialDTO ed = new EditorialDTO();
        String query =" SELECT IdEditorial                       "
                     +" ,NombreEditorial                         "
                     +" ,Estado                                  "
                     +" ,FechaCreacion                           "
                     +" ,FechaModificacion                       "
                     +" FROM Editorial                          "
                     +" WHERE   Editorial.Estado ='A'  AND IdEditorial =   " + id.toString() ;
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                ed = new EditorialDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return ed;
    }

    @Override
    public List<EditorialDTO> readAll() throws Exception {
        List <EditorialDTO> lst = new ArrayList<>();
        String query =" SELECT IdEditorial      "
                     +" ,NombreEditorial        "
                     +" ,Estado                 "
                     +" ,FechaCreacion          "
                     +" ,FechaModificacion      "
                     +" FROM Editorial          "
                     +" WHERE   Estado ='A'     ";

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery(query);
            while (rs.next()) {
             EditorialDTO ef = new EditorialDTO( rs.getInt(1)
                                    ,rs.getString(2)
                                    ,rs.getString(3)
                                    ,rs.getString(4)
                                    ,rs.getString(5));
                lst.add(ef);
            }
        }
        catch (SQLException e) {
        throw e; 
        }
        return lst;
    }

    @Override
    public boolean create(EditorialDTO entity) throws Exception {
        String query = " INSERT INTO Editorial(NombreEditorial)VALUES (?) ";
        try {
            Connection  conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEditorial());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(EditorialDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Editorial SET NombreEditorial = ?, FechaModificacion = ? WHERE IdEditorial = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreEditorial());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdEditorial());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Editorial SET Estado = ? WHERE IdEditorial = ?";
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

    public Integer getMaxRow()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg FROM Editorial "
                     +" WHERE   Estado ='A' ";
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return 0;
    }

}
