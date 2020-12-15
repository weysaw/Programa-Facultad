package conexion;

import java.util.*;
import java.sql.*;

/**
 *
 * @author ray_p
 */
public class AuxDAO extends ConexionBD {

    private static final String TABLA = "AUXILIAR";
    private static final String CLAVE = "clave";
    private static final String TRIGGER = "activaTrigger";
    private static final String SEMESTRE = "semestre";
    private static final String SQL_READ = "SELECT*FROM " + TABLA + " WHERE " + CLAVE + " = ?;";
    private static final String SQL_UPDATE = "UPDATE " + TABLA + " SET "
            + TRIGGER + " = ?, " + SEMESTRE + " = ? WHERE " + CLAVE + " = ?;";

    public AuxDAO() {
        super();
    }

    /**
     * Lee la información de la tabla AUX
     *
     * @param dto Es el aux
     * @return Devuelve el objeto dto si lo encuentra
     * @throws Exception Devuelve un error
     */
    public AuxDTO read(String clave) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        AuxDTO result = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_READ);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, clave);
        //Ejecuta el comando y devuelve el resultado del comando
        rs = ps.executeQuery();
        //Recorre por todos los resultados
        if (rs.next()) {
            result = getObject(rs);
        }
        //Cierra las conexiones
        cerrar(ps);
        cerrar(rs);

        return result;
    }

    /**
     * Se encarga de actualizar cualquier dato
     *
     * @param dto Son los nuevos datos a actualizar
     * @throws Exception Devuelve un error
     */
    public boolean update(AuxDTO dto, String clave) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_UPDATE);
        //Les asigna los valores que deben tener los ?
        ps.setBoolean(1, dto.isTriggerActivo());
        ps.setString(2, dto.getSemestre());
        ps.setString(3, clave);
        //Ejecuta el comando y actualiza
        datosModificados = ps.executeUpdate() > 0;
        //Cierra la conexión
        cerrar(ps);
        return datosModificados;
    }
    
    /**
     * Detecta el objeto enviado por el resultset y lo devuelve convertido a dto
     *
     * @param rs Es la salida de la consola
     * @return Devuelve el objeto dto
     * @throws Exception Devuelve un error
     */
    private AuxDTO getObject(ResultSet rs) throws Exception {
        return new AuxDTO(rs.getBoolean(TRIGGER),rs.getString(SEMESTRE));
    }  

}
