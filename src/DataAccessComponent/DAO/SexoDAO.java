package DataAccessComponent.DAO;

import DataAccessComponent.DTO.SexoDTO;
import DataAccessComponent.IDAO;
import DataAccessComponent.SQLiteDataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SexoDAO extends SQLiteDataHelper implements IDAO<SexoDTO>{
    @Override
    public SexoDTO readBy(Integer id) throws Exception {
        SexoDTO sd = new SexoDTO();
        String query =" SELECT IdSexo                           "
                     +" ,Nombre                                 "
                     +" ,Estado                                 "
                     +" ,FechaCreacion                          "
                     +" ,FechaModificacion                      "
                     +" FROM    Sexo                            "
                     +" WHERE   Sexo.Estado ='A' AND IdSexo =   " + id.toString();
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                sd =  new SexoDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return sd;
    }
    
    @Override
    public List<SexoDTO> readAll() throws Exception {
        List <SexoDTO> lst = new ArrayList<>();
        String query =" SELECT IdSexo               "
                    +" ,Nombre                     "
                    +" ,Estado                     "
                    +" ,FechaCreacion              "
                    +" ,FechaModificacion          "
                    +" FROM Sexo                   "
                    +" WHERE   Estado ='A'         ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SexoDTO s = new SexoDTO( rs.getInt(1)
                                        ,rs.getString(2)
                                        ,rs.getString(3)
                                        ,rs.getString(4)
                                        ,rs.getString(5));
                lst.add(s);
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }
    
    @Override
    public boolean create(SexoDTO entity) throws Exception {
        String query = " INSERT INTO Sexo(Nombre)VALUES (?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean update(SexoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Sexo SET  Nombre = ?, FechaModificacion = ? WHERE IdSexo = ?";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdSexo());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Sexo SET Estado = ? WHERE IdSexo = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }
    
    public Integer getMaxRow()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg FROM Sexo "
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
