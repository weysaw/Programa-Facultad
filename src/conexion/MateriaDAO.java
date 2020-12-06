package conexion;

import java.util.*;
import java.sql.*;

/**
 * Es la clase curso que se comunica con la base de datos
 *
 * @author Ornelas Munguía Axel Leonardo
 * @version 03.12.2020
 */
public class MateriaDAO extends ConexionBD {

    private static final String TABLA = "MATERIA";
    private static final String CLAVE_MATERIA = "claveMateria";
    private static final String NOM = "nom";
    private static final String PLAN_DE_ESTUDIO = "planDeEstudio";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLA;
    private static final String SQL_INSERT = "INSERT INTO " + TABLA + "(" + CLAVE_MATERIA
            + "," + NOM + ", " + PLAN_DE_ESTUDIO + " ) VALUES(?,?,?)";
    private static final String SQL_READ = "SELECT*FROM " + TABLA + " WHERE " + CLAVE_MATERIA + " = ?;";
    private static final String SQL_DELETE = "DELETE  FROM " + TABLA + " WHERE " + CLAVE_MATERIA + " = ?";
    private static final String SQL_UPDATE = "UPDATE "+ TABLA +" SET " + NOM + " = ? ,"+ PLAN_DE_ESTUDIO
            +"  = ? WHERE " + CLAVE_MATERIA + " = ?;" ;
    private static final String SQL_DELETE_ALL = "DELETE FROM " + TABLA;

    /**
     * Constructor de la clase
     */
    public MateriaDAO() {
        super();
    }

    /**
     * Devuelve un arreglo de los datos
     *
     * @return El arreglo de objetos
     * @throws Exception Devuelve error
     */
    public ArrayList<Materia> readAll() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se utilza para almacenar los objetos
        ArrayList<Materia> result = new ArrayList();
        //Manda al comando
        ps = conexion.prepareStatement(SQL_SELECT_ALL);
        //Ejecuta el comando y devuelve el resultado del comando
        rs = ps.executeQuery();
        //Recorre por todos los resultados
        while (rs.next()) {
            result.add(getObject(rs));
        }
        //Cierra las conexiones
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /**
     * Ingresa los datos a la tabla
     *
     * @param dto Es el objeto a ingresar
     * @throws Exception Devuelve un error
     */
    public void append(Materia dto) throws Exception {
        PreparedStatement ps = null;
        //Manda al comando
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setString(1, dto.getClaveMateria()+ "");
        ps.setString(2, dto.getNom());
        ps.setString(3, dto.getPlanDeEstudio());
        //Ejecuta el comando y acutaliza
        ps.executeUpdate();
        //Cierra la conexión
        cerrar(ps);
    }

    /**
     * Se encarga de actualizar cualquier dato
     *
     * @param dto Son los nuevos datos a actualizar
     * @throws Exception Devuelve un error
     */
    public boolean update(Materia dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_UPDATE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getNom());
        ps.setString(2, dto.getPlanDeEstudio());
        ps.setString(3, dto.getClaveMateria() + "");
        //Ejecuta el comando y actualiza
        datosModificados = ps.executeUpdate() > 0;
        //Cierra la conexión
        cerrar(ps);
        return datosModificados;
    }

    /**
     * Borra un dato de la tabla
     *
     * @param dto Es el dto que se borra
     * @throws Exception Devuelve error
     */
    public boolean delete(Materia dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getClaveMateria()+ "");
        //Ejecuta el comando y actualiza
        datosModificados = ps.executeUpdate() > 0;
        //Cierra la conexión
        cerrar(ps);
        return datosModificados;
    }

   /**
     * Borra un dato de la tabla
     *
     * @throws Exception Devuelve error
     */
    public void deleteAll() throws Exception {
        PreparedStatement ps = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE_ALL);
        //Ejecuta el comando y actualiza
        ps.executeUpdate();
        //Cierra la conexión
        cerrar(ps);
    }
    
    /**
     * Lee la información de un profesor en especifico
     *
     * @param dto Es el profesor a buscar
     * @return Devuelve el objeto dto si lo encuentra
     * @throws Exception Devuelve un error
     */
    public Materia read(Materia dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia result = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_READ);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getClaveMateria() + "");
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
     * Detecta el objeto enviado por el resultset y lo devuelve convertido a dto
     *
     * @param rs Es la salida de la consola
     * @return Devuelve el objeto dto
     * @throws Exception Devuelve un error
     */
    private Materia getObject(ResultSet rs) throws Exception {
        return new Materia(Integer.parseInt(rs.getString(CLAVE_MATERIA)), rs.getString(NOM),
                rs.getString(PLAN_DE_ESTUDIO));
    }
}
