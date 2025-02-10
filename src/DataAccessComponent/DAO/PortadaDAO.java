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
import DataAccessComponent.DTO.PortadaDTO;

public class PortadaDAO extends SQLiteDataHelper implements IDAO<PortadaDTO>{

    @Override
    public PortadaDTO readBy(Integer id) throws Exception {
        PortadaDTO pt = new PortadaDTO();
        String query =" SELECT IdPortada                            "
                     +" ,Portada                                    "
                     +" ,IdLibro                                    "
                     +" ,Estado                                     "
                     +" ,FechaCreacion                              "
                     +" ,FechaModificacion                          "
                     +" FROM    Portada                             "
                     +" WHERE   Portada.Estado ='A' AND IdPortada = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                pt =  new PortadaDTO(rs.getInt(1)
                                ,rs.getBytes(2)
                                ,rs.getInt(3)
                                ,rs.getString(4)
                                ,rs.getString(5)
                                ,rs.getString(6));
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return pt;
    }
    

    public PortadaDTO readByLibro(Integer id) throws Exception {
        PortadaDTO pt = null;
        String query =" SELECT IdPortada                            "
                     +" ,Portada                                    "
                     +" ,IdLibro                                    "
                     +" ,Estado                                     "
                     +" ,FechaCreacion                              "
                     +" ,FechaModificacion                          "
                     +" FROM    Portada                             "
                     +" WHERE   Portada.Estado ='A' AND IdLibro = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                pt =  new PortadaDTO(rs.getInt(1)
                                ,rs.getBytes(2)
                                ,rs.getInt(3)
                                ,rs.getString(4)
                                ,rs.getString(5)
                                ,rs.getString(6));
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return pt;
    }

    @Override
    public List<PortadaDTO> readAll() throws Exception {
        List<PortadaDTO> lst = new ArrayList<>();
        String query =" SELECT IdPortada                            "
                     +" ,Portada                                    "
                     +" ,Estado                                     "
                     +" ,FechaCreacion                              "
                     +" ,FechaModificacion                          "
                     +" FROM    Portada                             "
                     +" WHERE   Portada.Estado ='A'                 ";

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                PortadaDTO pt =  new PortadaDTO(rs.getInt(1)
                                               ,rs.getBytes(2)
                                               ,rs.getInt(3)
                                               ,rs.getString(4)
                                               ,rs.getString(5)
                                               ,rs.getString(6));
                lst.add(pt);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(PortadaDTO entity) throws Exception {
        String query = " INSERT INTO Portada(Portada, IdLibro)VALUES (?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setBytes(1, entity.getPortada());
            pstmt.setInt(2, entity.getIdLibro());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public boolean update(PortadaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Portada SET Portada = ?, IdLibro = ?, FechaModificacion = ? WHERE IdPortada = ?";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setBytes(1, entity.getPortada());
            pstmt.setInt(2, entity.getIdLibro());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdPortada());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Portada SET Estado = ? WHERE IdPortada = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query =" SELECT COUNT(*) TotalReg FROM Portada "
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
