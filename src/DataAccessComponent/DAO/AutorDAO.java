package DataAccessComponent.DAO;

import DataAccessComponent.DTO.AutorDTO;
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

public class AutorDAO extends SQLiteDataHelper implements IDAO<AutorDTO> {
    @Override
    public AutorDTO readBy(Integer id) throws Exception {
        AutorDTO au = new AutorDTO();
        String query =" SELECT IdAutor                           "
                     +" ,NombreAutor                             "
                     +" ,Estado                                  "
                     +" ,FechaCreacion                           "
                     +" ,FechaModificacion                       "
                     +" FROM    Autor                            "
                     +" WHERE   Autor.Estado ='A' AND IdAutor =  " + id.toString() ;

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                au = new AutorDTO( rs.getInt(1)
                                   ,rs.getString(2)
                                   ,rs.getString(3)
                                   ,rs.getString(4)
                                   ,rs.getString(5));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return au;
    }
    
    @Override
    public List<AutorDTO> readAll() throws Exception {
        List <AutorDTO> lst = new ArrayList<>();
        String query =" SELECT IdAutor       "
                     +" ,NombreAutor         "
                     +" ,Estado              "
                     +" ,FechaCreacion       "
                     +" ,FechaModificacion   "
                     +" FROM    Autor        "
                     +" WHERE   Estado ='A'  ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                AutorDTO a = new AutorDTO( rs.getInt(1)
                                         ,rs.getString(2)
                                         ,rs.getString(3)
                                         ,rs.getString(4)
                                         ,rs.getString(5));
                lst.add(a);
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }
    
    @Override
    public boolean create(AutorDTO entity) throws Exception {
        String query = " INSERT INTO Autor(NombreAutor)VALUES (?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreAutor());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean update(AutorDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Autor SET NombreAutor = ?, FechaModificacion = ? WHERE IdAutor = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreAutor());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdAutor());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Autor SET Estado = ? WHERE IdAutor = ?";
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
        String query =" SELECT COUNT(*) TotalReg FROM Autor "
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
