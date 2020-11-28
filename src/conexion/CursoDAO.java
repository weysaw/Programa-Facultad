package conexion;

import java.util.*;
import java.sql.*;

/**
 * Es la clase curso que se comunica con la base de datos
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 27.11.2020
 */
public class CursoDAO extends ConexionBD {
    
    private static final String CLAVE = "Clave";
    private static final String NOMBRE = "Nombre";
    private static final String SQL_SELECT_ALL = "SELECT * FROM alumno";
    private static final String SQL_INSERT = "INSERT INTO alumno("+CLAVE+","+NOMBRE+") VALUES(?,?)";
    private static final String SQL_READ = "SELECT*FROM alumno WHERE " + CLAVE + " = ?;";
    private static final String SQL_DELETE = "DELETE  FROM alumno WHERE " + CLAVE + " = ?";
    private static final String SQL_UPDATE = "UPDATE alumno SET " + NOMBRE + " = ? WHERE " + CLAVE + " = ?" ;

    public CursoDAO() {
        super();
    }
    /**
     * Devuelve un arreglo de los datos
     * 
     * @return
     * @throws Exception 
     */
    public ArrayList<CursoDTO> readAll() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CursoDTO> result = new ArrayList();
        ps = conexion.prepareStatement(SQL_SELECT_ALL);
        rs = ps.executeQuery();
        while (rs.next()) 
            result.add(getObject(rs));     
        cerrar(ps);
        cerrar(rs);
        return result;
    }
    
    public void append(CursoDTO dto) throws Exception {
        PreparedStatement ps = null;
        
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setString(1, dto.getClave() + "");
        ps.setString(2, dto.getNombre());
        
        ps.executeUpdate();
        cerrar(ps);
    }
    
    public void update(CursoDTO dto) throws Exception {
        PreparedStatement ps = null;
        
        ps = conexion.prepareStatement(SQL_UPDATE);
        ps.setString(1, dto.getClave()+"");
        ps.setString(2, dto.getNombre());
        ps.executeUpdate();
    }
    
    public void delete(CursoDTO dto) throws Exception {
        PreparedStatement ps = null;
        
        ps = conexion.prepareStatement(SQL_DELETE);
        ps.setString(1, dto.getClave() + "");
        ps.executeUpdate();
        cerrar(ps);
    }
    
    public CursoDTO read(CursoDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CursoDTO result = null;
        
        ps = conexion.prepareStatement(SQL_READ);
        ps.setString(1, dto.getClave() + "");
        rs = ps.executeQuery();
        if (rs.next()) {
            result = getObject(rs);
        }
        cerrar(ps);
        cerrar(rs);
        
        return result;
    }
    
    private CursoDTO getObject(ResultSet rs) throws Exception {
        return new CursoDTO(Integer.parseInt(rs.getString(CLAVE)), rs.getString(NOMBRE));
    }
 }
