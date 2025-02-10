package DataAccessComponent.DAO;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.FacturaDTO;
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

public class FacturaDAO extends SQLiteDataHelper implements IDAO<FacturaDTO> {
     @Override
    public FacturaDTO readBy(Integer id) throws Exception {
        FacturaDTO fa = new FacturaDTO();
        String query =" SELECT f.IdFactura                                                    "
                    +" ,f.DireccionLocal                                                      "
                    +" ,f.TelefonoLocal                                                       "
                    +" ,f.CorreoElectronico                                                   "
                    +" ,f.FechaEmision                                                        "
                    +" ,f.NumeroFactura                                                       "
                    + ",f.FechaModificacion                                                   "
                    +" ,f.DetallesCompra                                                      "
                    +" ,f.Estado                                                              "
                    +" ,c.IdCliente                                                           "
                    +" ,v.IdVenta                                                             "
                    +" FROM Factura as f                                                      "
                    +" INNER JOIN Venta as v ON f.IdVenta = v.IdVenta  "
                    + "INNER JOIN Cliente as c ON v.IdCliente = c.IdCliente "
                    +" WHERE   f.Estado ='A'  AND  f.IdFactura=                                   "+ id.toString();
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                fa = new FacturaDTO(rs.getInt(1)
                                    ,rs.getString(2)
                                    ,rs.getString(3)
                                    ,rs.getString(4)
                                    ,rs.getString(5)
                                    ,rs.getString(6)
                                    ,rs.getString(7)
                                    ,rs.getString(8)
                                    ,rs.getString(9)
                                    ,rs.getInt(10)
                                    ,rs.getInt(11));
            }
        } 
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return fa;
    }

    @Override
    public List<FacturaDTO> readAll() throws Exception {
         List <FacturaDTO> lst = new ArrayList<>();
         String query =" SELECT f.IdFactura                                                       "
                        +" ,f.DireccionLocal                                                      "
                        +" ,f.TelefonoLocal                                                       "
                        +" ,f.CorreoElectronico                                                   "
                        +" ,f.FechaEmision                                                        "
                        +" ,f.NumeroFactura                                                       "
                         + ",f.FechaModificacion                                                  "
                        +" ,f.DetallesCompra                                                      "
                        +" ,f.Estado                                                      "
                        +" ,c.IdCliente                                                           "
                        +" ,v.IdVenta                                                             "
                        +" FROM Factura as f                                                       "
                        +" INNER JOIN Cliente as c ON v.IdCliente = c.IdCliente                   "
                        +" INNER JOIN Venta as v ON f.IdVenta= v.IdVenta                          "
                        +" WHERE   f.Estado ='A'                                                  ";
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery(query);
            while (rs.next()) {
                FacturaDTO fc = new FacturaDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getString(4)
                                ,rs.getString(5)
                                ,rs.getString(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getString(9)
                                ,rs.getInt(10)
                                ,rs.getInt(11));
                lst.add(fc);
            }
        }
        catch (SQLException e) {
        throw e; 
        }
        return lst;
    }

    @Override
    public boolean create(FacturaDTO entity) throws Exception {
       
        String query = " INSERT INTO Factura(DireccionLocal, TelefonoLocal, CorreoElectronico, NumeroFactura,"
        + "IdCliente, DetallesCompra, IdVenta)VALUES (?, ?, ?, ?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getDireccionLocal());
            pstmt.setString(2, entity.getTelefonoLocal());
            pstmt.setString(3, entity.getCorreoElectronico());
            pstmt.setString(4, entity.getNumeroFactura());
            pstmt.setInt(5, entity.getIdCliente());
            pstmt.setString(6, entity.getDetallesCompra());
            pstmt.setInt(7, entity.getIdVenta());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(FacturaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Factura SET DireccionLocal = ?, TelefonoLocal = ?, CorreoElectronico = ?, NumeroFactura = ?,  fechaModificacion = ?,IdCliente = ?, DetallesCompra = ?, IdVenta = ?,"
        +"WHERE IdFactura = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getDireccionLocal());
            pstmt.setString(2, entity.getTelefonoLocal());
            pstmt.setString(3, entity.getCorreoElectronico());
            pstmt.setString(4, entity.getNumeroFactura());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setInt(6, entity.getIdCliente());
            pstmt.setString(7, entity.getDetallesCompra());
            pstmt.setInt(8, entity.getIdVenta());
            pstmt.setInt(9, entity.getIdFactura());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Factura SET estado = ? WHERE IdFactura = ?";
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
        String query = "SELECT COUNT(*) AS TotalReg FROM Factura WHERE estado = 'A'";
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
