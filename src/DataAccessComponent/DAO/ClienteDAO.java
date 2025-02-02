package DataAccessComponent.DAO;

import DataAccessComponent.DTO.ClienteDTO;
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

public class ClienteDAO extends SQLiteDataHelper implements IDAO<ClienteDTO> {
    @Override
    public ClienteDTO readBy(Integer id) throws Exception {
        ClienteDTO cl = new ClienteDTO();
        String query ="SELECT   c.IdCliente                                          "
                    +" ,c.Nombre                                                     "
                    +" ,c.Apellido                                                   "
                    +" ,c.Cedula                                                     "
                    +" ,c.Telefono                                                   "
                    +" ,c.CorreoElectronico                                          "
                    +" ,c.Estado                                                     "
                    +" ,c.FechaCreacion                                              "
                    +" ,c.FechaModificacion                                          "
                    +" ,ec.Nombre                                                    "
                    +" ,s.Nombre                                                     "
                    +" FROM Cliente           as c                                   "
                    +" LEFT JOIN EstadoCivil  as ec  ON c.IdEstadoCivil = ec.IdEstadoCivil"
                    +" LEFT JOIN Sexo         as s   ON c.IdSexo = s.IdSexo"
                    +" WHERE c.Estado = 'A'    AND IdCliente=                        "  + id.toString();

        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                cl = new ClienteDTO(  rs.getInt(1)
                                        , rs.getString(2)
                                        , rs.getString(3)
                                        , rs.getString(4)
                                        , rs.getString(5)
                                        , rs.getString(6)
                                        , rs.getString(7)
                                        , rs.getString(8)
                                        , rs.getString(9)
                                        , rs.getString(10)
                                        , rs.getString(11));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return cl;
    }
    
    @Override
    public List<ClienteDTO> readAll() throws Exception{
        List<ClienteDTO> lst = new ArrayList<>();
        String query = " SELECt c.IdCliente                                                   "
                    +" ,c.Nombre                                                              "
                    +" ,c.Apellido                                                            "
                    +" ,c.Cedula                                                              "
                    +" ,c.Telefono                                                            "
                    +" ,c.CorreoElectronico                                                   "
                    +" ,c.Estado                                                              "
                    +" ,c.FechaCreacion                                                       "
                    +" ,c.FechaModificacion                                                   "
                    +" ,ec.Nombre                                                              "
                    +" ,s.Nombre                                                               "
                    +" FROM Cliente             as c                                          "
                    +" INNER JOIN EstadoCivil   as ec   ON c.IdEstadoCivil = ec.IdEstadoCivil "
                    +" INNER JOIN Sexo          as s    ON c.IdSexo = s.IdSexo                "
                    +" WHERE c.Estado = 'A'                                                   ";

        try{
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                ClienteDTO c = new ClienteDTO(  rs.getInt(1)
                                                , rs.getString(2)
                                                , rs.getString(3)
                                                , rs.getString(4)
                                                , rs.getString(5)
                                                , rs.getString(6)
                                                , rs.getString(7)
                                                , rs.getString(8)
                                                , rs.getString(9)
                                                , rs.getString(10)
                                                , rs.getString(11));
                lst.add(c);
            }
        } catch (SQLException e) {
                throw e;
        }
        return lst;
    }
    
    @Override
    public boolean create(ClienteDTO entity) throws Exception {
        String query = " INSERT INTO Cliente(Nombre, Apellido, Cedula, Telefono, CorreoElectronico, IdEstadoCivil, IdSexo) VALUES (?, ?, ?, ?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getCedula());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getCorreoElectronico());
            pstmt.setInt(6, entity.getIdEstadoCivil());
            pstmt.setInt(7, entity.getIdSexo());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean update(ClienteDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Cliente SET Nombre =?, Apellido =?, Cedula =?, Telefono =?, CorreoElectronico =?, IdEstadoCivil =?, IdSexo =?, FechaModificacion = ? WHERE IdCliente = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getCedula());
            pstmt.setString(4, entity.getTelefono());
            pstmt.setString(5, entity.getCorreoElectronico());
            pstmt.setInt(6, entity.getIdEstadoCivil());
            pstmt.setInt(7, entity.getIdSexo());
            pstmt.setString(8,dtf.format(now).toString());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Cliente SET Estado = ? WHERE IdCliente = ?";
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
        String query =" SELECT COUNT(*) TotalReg FROM Cliente "
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
