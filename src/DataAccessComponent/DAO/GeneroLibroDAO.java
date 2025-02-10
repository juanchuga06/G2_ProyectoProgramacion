package DataAccessComponent.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccessComponent.IDAO;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.GeneroLibroDTO;

public class GeneroLibroDAO extends SQLiteDataHelper implements IDAO<GeneroLibroDTO> {

    @Override
    public GeneroLibroDTO readBy(Integer id) throws Exception {
        GeneroLibroDTO oGT = new GeneroLibroDTO();
        String query = "SELECT IdGeneroLibro                    "
                        +" ,NombreGeneroLibro                   "
                        +" ,Estado                              "
                        +" ,FechaCreacion                       "
                        +" ,FechaModificacion                   "
                        +"FROM GeneroLibro                      "
                        +"WHERE Estado = 'A'AND IdGeneroLibro=  " + id.toString();
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oGT = new GeneroLibroDTO(
                rs.getInt(1), 
                rs.getString(2),
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5));
            }
        } catch (SQLException e) {
            throw e;
        }
        return oGT;
    }

    @Override
    public List<GeneroLibroDTO> readAll() throws Exception {
        List<GeneroLibroDTO> lst = new ArrayList<>();
        String query = "SELECT IdGeneroLibro                    "
                        +" ,NombreGeneroLibro                   "
                        +" ,Estado                              "
                        +" ,FechaCreacion                       "
                        +" ,FechaModificacion                   "
                        +"FROM GeneroLibro                      "
                        +"WHERE Estado = 'A'                    ";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                GeneroLibroDTO g = new GeneroLibroDTO(
                rs.getInt(1), 
                rs.getString(2),
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5));
                
                lst.add(g);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(GeneroLibroDTO entity) throws Exception {
        String query = "INSERT INTO GeneroLibro(NombreGeneroLibro) VALUES (?) ";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreGeneroLibro());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(GeneroLibroDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE GeneroLibro SET NombreGeneroLibro = ?, FechaModificacion = ? WHERE IdGeneroLibro = ? ";
        
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, entity.getNombreGeneroLibro());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdGeneroLibro());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE GeneroLibro SET Estado = ? WHERE IdGeneroLibro = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM GeneroLibro"
                     + "WHERE Estado = 'A' ";
            try {
                Connection conn = openConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
            
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
              throw e;
            }
         return 0;
    }
    
}
