package DataAccessComponent.DAO;
import DataAccessComponent.SQLiteDataHelper;
import DataAccessComponent.DTO.LibroDTO;
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
public class LibroDAO extends SQLiteDataHelper implements IDAO<LibroDTO>{

    @Override
    public LibroDTO readBy(Integer id) throws Exception {
        LibroDTO l = new LibroDTO();
        String query =" SELECT l.IdLibro       "
                     +" , l.Titulo             "
                     +" , l.NumeroEdicion      "
                     +" , l.NumeroEjemplares   "
                     +" , l.FechaPublicacion   "
                     +" , l.Precio             "
                     +" , l.Estado             "
                     +" , l.FechaCreacion      "
                     +" , l.FechaModificacion  "
                     +" , gl.IdGeneroLibro     "
                     +" , e.IdEditorial        "
                     +" , a.IdAutor            "
                     +" , l.CodigoBarras       "
                     +" , l.CodigoISBN         "
                     +" FROM Libro as l        "
                     +" INNER JOIN GeneroLibro  as  gl ON l.IdGeneroLibro = gl.IdGeneroLibro"
                     +" INNER JOIN Editorial    as  e  ON l.IdEditorial = e.IdEditorial"
                     +" INNER JOIN Autor        as  a  ON l.IdAutor = a.IdAutor"
                     +" WHERE   l.Estado ='A'  AND IdLibro =   " + id.toString() ;
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                l = new LibroDTO(rs.getInt(1)
                                ,rs.getString(2)
                                ,rs.getInt(3)
                                ,rs.getInt(4)
                                ,rs.getString(5)
                                ,rs.getBigDecimal(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getString(9)
                                ,rs.getInt(10)
                                ,rs.getInt(11)
                                ,rs.getInt(12)
                                ,rs.getString(13)
                                ,rs.getString(14));
            } 
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

    @Override
    public List<LibroDTO> readAll() throws Exception {
        List <LibroDTO> lst = new ArrayList<>();
        String query =" SELECT l.IdLibro       "
                     +" , l.titulo             "
                     +" , l.numeroEdicion      "
                     +" , l.numeroEjemplares   "
                     +" , l.fechaPublicacion   "
                     +" , l.Precio             "
                     +" , l.estado             "
                     +" , l.fechaCreacion      "
                     +" , l.fechaModificacion  "
                     +" , gl.IdGeneroLibro     "
                     +" , e.IdEditorial        "
                     +" , a.IdAutor            "
                     +" , l.codigoBarras       "
                     +" , l.codigoISBN         "
                     +" FROM Libro as l        "
                     +"    INNER JOIN GeneroLibro  as  gl ON l.IdGeneroLibro = gl.IdGeneroLibro"
                     +"    INNER JOIN Editorial    as  e  ON l.IdEditorial = e.IdEditorial"
                     +"    INNER JOIN Autor        as  a  ON l.IdAutor = a.IdAutor"
                     +" WHERE   l.Estado ='A'" ;

       try {
           Connection conn = openConnection();
           Statement  stmt = conn.createStatement();
           ResultSet  rs   = stmt.executeQuery(query);
           while (rs.next()) {
            LibroDTO l = new LibroDTO(rs.getInt(1)
                                 ,rs.getString(2)
                                ,rs.getInt(3)
                                ,rs.getInt(4)
                                ,rs.getString(5)
                                ,rs.getBigDecimal(6)
                                ,rs.getString(7)
                                ,rs.getString(8)
                                ,rs.getString(9)
                                ,rs.getInt(10)
                                ,rs.getInt(11)
                                ,rs.getInt(12)
                                ,rs.getString(13)
                                ,rs.getString(14));
            lst.add(l);
           }
       }
       catch (SQLException e) {
       throw e; 
       }
       return lst;
    }

    @Override
    public boolean create(LibroDTO entity) throws Exception {
        String query = " INSERT INTO Libro(Titulo, NumeroEdicion, NumeroEjemplares, FechaPublicacion, Precio, IdGeneroLibro,  IdEditorial,  IdAutor, CodigoBarras, CodigoISBN)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getTitulo());
            pstmt.setInt(2, entity.getNumeroEdicion());
            pstmt.setInt(3, entity.getNumeroEjemplares());
            pstmt.setString(4, entity.getFechaPublicacion());
            pstmt.setBigDecimal(5, entity.getPrecio());
            pstmt.setInt(6, entity.getIdGeneroLibro());
            pstmt.setInt(7, entity.getIdEditorial());
            pstmt.setInt(8, entity.getIdAutor());
            pstmt.setString(9, entity.getCodigoBarras());
            pstmt.setString(10, entity.getCodigoISBN());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(LibroDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Libro SET titulo = ?, numeroEdicion = ?, numeroEjemplares = ?, fechaPublicacion = ?, Precio = ? fechaModificacion = ?, IdGeneroLibro = ?, IdEditorial = ?, IdAutor = ?,"
        +" codigoBarras = ?, codigoISBN = ? WHERE idLibro = ?";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getTitulo());
            pstmt.setInt(2, entity.getNumeroEdicion());
            pstmt.setInt(3, entity.getNumeroEjemplares());
            pstmt.setString(4, entity.getFechaPublicacion());
            pstmt.setBigDecimal(5, entity.getPrecio());
            pstmt.setString(6, dtf.format(now));
            pstmt.setInt(7, entity.getIdGeneroLibro());
            pstmt.setInt(8, entity.getIdEditorial());
            pstmt.setInt(9, entity.getIdAutor());
            pstmt.setString(10, entity.getCodigoBarras());
            pstmt.setString(11, entity.getCodigoISBN());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Libro SET estado = ? WHERE idLibro = ?";
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
        String query = "SELECT COUNT(*) AS TotalReg FROM Libro WHERE estado = 'A'";
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
