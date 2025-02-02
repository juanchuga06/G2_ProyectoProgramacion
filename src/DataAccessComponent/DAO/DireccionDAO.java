package DataAccessComponent.DAO;

import DataAccessComponent.DTO.DireccionDTO;
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

public class DireccionDAO extends SQLiteDataHelper implements IDAO<DireccionDTO>{
    @Override
    public DireccionDTO readBy(Integer id) throws Exception {
        DireccionDTO oS = new DireccionDTO();
        String query ="SELECT   d.IdDireccion                                                "
                    +" ,d.CallePrincipal                                                     "
                    +" ,d.CalleSecundaria                                                    "
                    +" ,d.Estado                                                             "
                    +" ,d.FechaCreacion                                                      "
                    +" ,d.FechaModificacion                                                  "
                    +" ,c.Nombre                                                             "
                    +" ,c.Apellido                                                           "
                    +" ,b.Nombre                                                             "
                    +" ,b.Apellido                                                           "
                    +" FROM Direccion as d                                                   "
                    +" LEFT JOIN Cliente as c ON d.IdCliente = c.IdCliente                   "
                    +" LEFT JOIN Bibliotecario as b ON d.IdBibliotecario = b.IdBibliotecario "
                    +" WHERE d.Estado = 'A'    AND IdDireccion=                              "  + id.toString();


        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new DireccionDTO(rs.getInt(1)
                                    ,rs.getString(2)
                                    ,rs.getString(3)
                                    ,rs.getString(4)
                                    ,rs.getString(5)
                                    ,rs.getString(6)
                                    ,rs.getString(7)
                                    ,rs.getString(8)
                                    ,rs.getString(9)
                                    ,rs.getString(10));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return oS;
    }
    
    @Override
    public List<DireccionDTO> readAll() throws Exception{
        List<DireccionDTO> lst = new ArrayList<>();
        String query = "SELECT IdDireccion                                                     "
                    +"  ,d.CallePrincipal                                                     "
                    +"  ,d.CalleSecundaria                                                    "
                    +"  ,d.Estado                                                             "
                    +"  ,d.FechaCreacion                                                      "
                    +"  ,d.FechaModificacion                                                  "
                    +"  ,c.Nombre                                                             "
                    +"  ,c.Apellido                                                             "
                    +"  ,b.Nombre                                                             "
                    +"  ,b.Apellido                                                             "
                    +" FROM Direccion AS d                                                    "
                    +" LEFT JOIN Cliente AS c ON d.IdCliente = c.IdCliente                    "
                    +" LEFT JOIN Bibliotecario AS b ON d.IdBibliotecario = b.IdBibliotecario  "
                    +" WHERE d.Estado = 'A';                                                  ";

        try{
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                DireccionDTO d = new DireccionDTO(  rs.getInt(1)
                                                , rs.getString(2)
                                                , rs.getString(3)
                                                , rs.getString(4)
                                                , rs.getString(5)
                                                , rs.getString(6)
                                                , rs.getString(7)
                                                , rs.getString(8)
                                                , rs.getString(9)
                                                ,rs.getString(10));
                lst.add(d);
            }
        } catch (SQLException e) {
                throw e;
        }
        return lst;
    }
    
    @Override
    public boolean create(DireccionDTO entity) throws Exception {
        String query = " INSERT INTO Direccion(CallePrincipal, CalleSecundaria, IdCliente, IdBibliotecario)VALUES (?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getCallePrincipal());
            pstmt.setString(2, entity.getCalleSecundaria());
            pstmt.setInt(3, entity.getIdCliente());
            pstmt.setInt(4, entity.getIdBibliotecario());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean update(DireccionDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Direccion SET CallePrincipal = ?, CalleSecundaria = ?, FechaModifica = ? WHERE IdDireccion = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getCallePrincipal());
            pstmt.setString(2, entity.getCalleSecundaria());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(4, entity.getIdDireccion());

            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Direccion SET Estado = ? WHERE IdDireccion = ?";
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
        String query =" SELECT COUNT(*) TotalReg FROM Direccion "
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
