package DataAccessComponent.DAC;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.EstadoCivilDTO;
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

public class EstadoCivilDAO extends SQLiteDataHelper implements IDAO<EstadoCivilDTO> {

    @Override
    public EstadoCivilDTO readBy(Integer id) throws Exception {
        EstadoCivilDTO ec = new EstadoCivilDTO();
        String query =" SELECT idEstadoCivil                     "
                     +" ,Nombre                                  "
                     +" ,Estado                                  "
                     +" ,FechaCreacion                           "
                     +" ,FechaModificacion                       "
                     +" FROM EstadoCivil                          "
                     +" WHERE   EstadoCivil.Estado ='A'  AND idEstadoCivil =   " + id.toString() ;
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                ec = new EstadoCivilDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return ec;
    }

    @Override
    public List<EstadoCivilDTO> readAll() throws Exception {
        List <EstadoCivilDTO> lst = new ArrayList<>();
        String query =" SELECT idEstadoCivil     "
                     +" ,Nombre                 "
                     +" ,Estado                 "
                     +" ,FechaCreacion          "
                     +" ,FechaModificacion      "
                     +" FROM EstadoCivil          "
                     +" WHERE   Estado ='A'     ";

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery(query);
            while (rs.next()) {
                EstadoCivilDTO ev = new EstadoCivilDTO( rs.getInt(1)
                                    ,rs.getString(2)
                                    ,rs.getString(3)
                                    ,rs.getString(4)
                                    ,rs.getString(5));
                lst.add(ev);
            }
        }
        catch (SQLException e) {
        throw e;
        }
        return lst;
    }

    @Override
    public boolean create(EstadoCivilDTO entity) throws Exception {
        String query = " INSERT INTO EstadoCivil (Nombre) VALUES (?)";
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
    public boolean update(EstadoCivilDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE EstadoCivil SET Nombre = ?, FechaModificaion = ? WHERE IdEstadoCivil = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdEstadoCivil());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE EstadoCivil SET Estado = ? WHERE IdEstadoCivil = ?";
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
        String query =" SELECT COUNT(*) TotalReg FROM EstadoCivil "
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
