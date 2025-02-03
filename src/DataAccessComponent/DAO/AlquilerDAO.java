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
import DataAccessComponent.DTO.AlquilerDTO;

public class AlquilerDAO extends SQLiteDataHelper implements IDAO<AlquilerDTO> {

    @Override
    public AlquilerDTO readBy(Integer id) throws Exception {
        AlquilerDTO oA = new AlquilerDTO();
        String query = " SELECT  a.IdAlquiler                                                                        "
                        +" ,a.Estado                                                                                 "
                        +" ,a.FechaAlquiler                                                                          "
                        +" ,a.FechaDevolucion                                                                        "
                        +" ,a.FechaModificacion                                                                      "
                        +" ,l.IdLibro                                                                                "
                        +" ,c.IdCliente                                                                              "
                        +" ,b.IdBibliotecario                                                                        "
                        +" ,ea.IdEstadoAlquiler                                                                      "
                        +" FROM Alquiler as a                                                                        "
                        +" INNER JOIN Libro as l ON a.IdLibro = l.IdLibro                                            "
                        +" INNER JOIN Cliente as c ON a.IdCliente = c.IdCliente                                      "
                        +" INNER JOIN Bibliotecario as b ON a.IdBibliotecario = b.IdBibliotecario                    "
                        +" INNER JOIN EstadoAlquiler as ea ON a.IdEstadoAlquiler = ea.IdEstadoAlquiler               "
                        +" WHERE a.Estado = 'A' AND IdAlquiler =                                                     "  +  id.toString();
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oA = new AlquilerDTO(
                    rs.getInt(1),
                     rs.getString(2), 
                     rs.getString(3), 
                     rs.getString(4), 
                     rs.getString(5), 
                     rs.getInt(6), 
                     rs.getInt(7), 
                     rs.getInt(8), 
                     rs.getInt(9));
            }
        } catch (SQLException e) {
            throw e;
        }
        return oA;
    }

    @Override
    public List<AlquilerDTO> readAll() throws Exception {
        List<AlquilerDTO> lst = new ArrayList<>();
        String query = "SELECT  a.IdAlquiler                                                                        "
                        +" ,a.Estado                                                                                "
                        +" ,a.FechaAlquiler                                                                         "
                        +" ,a.FechaDevolucion                                                                       "
                        +" ,a.FechaModificacion                                                                     "
                        +" ,l.IdLibro                                                                               "
                        +" ,c.IdCliente                                                                             "
                        +" ,b.IdBibliotecario                                                                       "
                        +" ,ea.IdEstadoAlquiler                                                                     "
                        +" FROM Alquiler as a                                                                       "
                        +" INNER JOIN Libro as l ON a.IdLibro = l.IdLibro                                           "
                        +" INNER JOIN Cliente as c ON a.IdCliente = c.IdCliente                                     "
                        +" INNER JOIN Bibliotecario as b ON a.IdBibliotecario = b.IdBibliotecario                   "
                        +" INNER JOIN EstadoAlquiler as ea ON a.IdEstadoAlquiler = ea.IdEstadoAlquiler              "
                        +" WHERE a.Estado = 'A'                                                                     ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                AlquilerDTO a = new AlquilerDTO(
                    rs.getInt(1),
                     rs.getString(2), 
                     rs.getString(3), 
                     rs.getString(4), 
                     rs.getString(5), 
                     rs.getInt(6), 
                     rs.getInt(7), 
                     rs.getInt(8), 
                     rs.getInt(9));
                lst.add(a);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(AlquilerDTO entity) throws Exception {
        String query = "INSERT INTO Alquiler(FechaAlquiler, FechaDevolucion, IdLibro, IdCliente, IdBibliotecario, IdEstadoAlquiler) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, entity.getFechaAlquiler());
            pstmt.setString(2, entity.getFechaDevolucion());
            pstmt.setInt(3, entity.getIdLibro());
            pstmt.setInt(4, entity.getIdCliente());
            pstmt.setInt(5, entity.getIdBibliotecario());
            pstmt.setInt(6, entity.getIdEstadoAlquiler());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(AlquilerDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Alquiler SET FechaAlquiler = ?, FechaDevolucion = ?, FechaModificacion = ?, IdLibro = ?, IdCliente = ?, IdBibliotecario = ?, IdEstadoAlquiler = ? WHERE IdAlquiler = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, entity.getFechaAlquiler());
            pstmt.setString(2, entity.getFechaDevolucion());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdLibro());
            pstmt.setInt(5, entity.getIdCliente());
            pstmt.setInt(6,entity.getIdBibliotecario());
            pstmt.setInt(7, entity.getIdEstadoAlquiler());
            pstmt.setInt(8, entity.getIdAlquiler());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Alquiler SET Estado = ? WHERE IdAlquiler = ?";
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
        String query = "SELECT COUNT(*) TotalReg FROM Alquiler "
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
