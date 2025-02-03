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
import DataAccessComponent.DTO.EstadoAlquilerDTO;

public class EstadoAlquilerDAO extends SQLiteDataHelper implements IDAO<EstadoAlquilerDTO> {

    @Override
    public EstadoAlquilerDTO readBy(Integer id) throws Exception {
        EstadoAlquilerDTO oEA = new EstadoAlquilerDTO();
        String query = "SELECT IdEstadoAlquiler                      "  
                       + " ,Nombre                                   "  
                       + " ,Estado                                   "  
                       + " ,FechaCreacion                            "  
                       + " ,FechaModificacion                        "  
                       + " FROM EstadoAlquiler                       "  
                       + " WHERE Estado = 'A' AND IdEstadoAlquiler = " + id.toString();
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            oEA = new EstadoAlquilerDTO(
                                    rs.getInt(1), 
                                    rs.getString(2), 
                                    rs.getString(3), 
                                    rs.getString(4), 
                                    rs.getString(5));

        } catch (SQLException e) {
            throw e;
        }
        return oEA;
    }

    @Override
    public List<EstadoAlquilerDTO> readAll() throws Exception {
        List<EstadoAlquilerDTO> lst = new ArrayList<>();
        String query = "SELECT IdEstadoAlquiler                      "  
                       + " ,Nombre                                   "  
                       + " ,Estado                                   "  
                       + " ,FechaCreacion                            "  
                       + " ,FechaModificacion                        "  
                       + " FROM EstadoAlquiler                       "  
                       + " WHERE Estado = 'A'                        ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                EstadoAlquilerDTO s = new EstadoAlquilerDTO(
                    rs.getInt(1), 
                    rs.getString(2), 
                    rs.getString(3), 
                    rs.getString(4), 
                    rs.getString(5));

                    lst.add(s);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(EstadoAlquilerDTO entity) throws Exception {
        String query = "INSERT INTO EstadoAlquiler(Nombre) VALUES (?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
        
    }

    @Override
    public boolean update(EstadoAlquilerDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE EstadoAlquiler SET Nombre = ?, Estado = ?,, FechaModificacion = ? WHERE IdEstadoAlquiler = ?";

        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getEstado());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdEstadoAlquiler());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE EstadoAlquiler SET Estado = ? WHERE IdEstadoAlquiler = ?";
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
        String query = "SELECT COUNT(*) TotalReg FROM EstadoAlquiler"
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
