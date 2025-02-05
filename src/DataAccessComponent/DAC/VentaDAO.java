package DataAccessComponent.DAC;

import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.BibliotecarioDTO;
import DataAccessComponent.DTO.VentaDTO;
import DataAccessComponent.IDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO extends SQLiteDataHelper implements IDAO<VentaDTO> {
    @Override
    public VentaDTO readBy(Integer id) throws Exception {
        VentaDTO vt = new VentaDTO();
        String query =" SELECT v.IdVenta                                                      "
                    +" ,v.CantidadLibros                                                      "
                    +" ,v.TotalLibros                                                         "
                    +" ,v.Descuento                                                           "
                    +" ,v.TotalPagar                                                          "
                    +" ,v.Estado                                                              "
                    +" ,v.FechaVenta                                                          "
                    +" ,v.FechaModificacion                                                   "
                    +" ,l.IdLibro                                                             "
                    +" ,c.IdCliente                                                           "
                    +" ,b.IdBibliotecario                                                     "
                    +" FROM Venta as v                                                        "
                    +" INNER JOIN Libro as l ON v.IdLibro = l.IdLibro                         "
                    +" INNER JOIN Cliente as c ON v.IdCliente = c.IdCliente                   "
                    +" INNER JOIN Bibliotecario as b ON v.IdBibliotecario = b.IdBibliotecario "
                    +" WHERE   v.Estado ='A'  AND  IdVenta=                                   "+ id.toString();
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                vt = new VentaDTO(rs.getInt(1)
                                ,rs.getInt(2)
                                ,rs.getBigDecimal(3)
                                ,rs.getBoolean(4)
                                ,rs.getBigDecimal(5)
                                ,rs.getString(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getInt(9)
                                ,rs.getInt(10)
                                ,rs.getInt(11));
            }
        } 
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return vt;
    }

    @Override
    public List<VentaDTO> readAll() throws Exception {
         List <VentaDTO> lst = new ArrayList<>();
         String query =" SELECT v.IdVenta                                                      "
                    +" ,v.CantidadLibros                                                      "
                    +" ,v.TotalLibros                                                         "
                    +" ,v.Descuento                                                           "
                    +" ,v.TotalPagar                                                          "
                    +" ,v.Estado                                                              "
                    +" ,v.FechaVenta                                                          "
                    +" ,v.FechaModificacion                                                   "
                    +" ,l.IdLibro                                                             "
                    +" ,c.IdCliente                                                           "
                    +" ,b.IdBibliotecario                                                     "
                    +" FROM Venta as v                                                        "
                    +" INNER JOIN Libro as l ON v.IdLibro = l.IdLibro                         "
                    +" INNER JOIN Cliente as c ON v.IdCliente = c.IdCliente                   "
                    +" INNER JOIN Bibliotecario as b ON v.IdBibliotecario = b.IdBibliotecario "
                    +" WHERE   v.Estado ='A'                                    ";

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery(query);
            while (rs.next()) {
                VentaDTO vb = new VentaDTO(rs.getInt(1)
                                ,rs.getInt(2)
                                ,rs.getBigDecimal(3)
                                ,rs.getBoolean(4)
                                ,rs.getBigDecimal(5)
                                ,rs.getString(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getInt(9)
                                ,rs.getInt(10)
                                ,rs.getInt(11));
                lst.add(vb);
            }
        }
        catch (SQLException e) {
        throw e; 
        }
        return lst;
    }

    @Override
    public boolean create(VentaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " INSERT INTO Venta (CantidadLibros, TotalLibros, Descuento, TotalPagar, FechaVenta "
        + "IdLibro, IdCliente, IdBibliotecario) VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getCantidadLibros());
            pstmt.setBigDecimal(2, entity.getTotalLibros());
            pstmt.setBoolean(3, entity.getDescuento());
            pstmt.setBigDecimal(4, entity.getTotalPagar());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setInt(8, entity.getIdLibro());
            pstmt.setInt(9, entity.getIdCliente());
            pstmt.setInt(9, entity.getIdBibliotecario());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(VentaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Venta SET CantidadLibros = ?, TotalLibros = ?, Descuento = ?, TotalPagar = ?, FechaVenta = ?, fechaModificacion = ?, IdLibro = ?, IdCliente = ?,"
        +" IdBibliotecario = ? WHERE IdVenta = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getCantidadLibros());
            pstmt.setBigDecimal(2, entity.getTotalLibros());
            pstmt.setBoolean(3, entity.getDescuento());
            pstmt.setBigDecimal(4, entity.getTotalPagar());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setString(5, dtf.format(now).toString());
            pstmt.setInt(8, entity.getIdLibro());
            pstmt.setInt(9, entity.getIdCliente());
            pstmt.setInt(9, entity.getIdBibliotecario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Venta SET estado = ? WHERE IdVenta = ?";
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
        String query = "SELECT COUNT(*) AS TotalReg FROM Venta WHERE estado = 'A'";
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