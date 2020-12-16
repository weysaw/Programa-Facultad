package conexion;

import java.util.*;
import java.sql.*;

/**
 * Es la profesor que se comunica con la base de datos
 *
 * @author Ornelas Munguía Axel Leonardo
 * @version 11.12.2020
 */
public class ProfesorDAO extends ConexionBD {

    private static final String TABLA = "PROFESOR";
    private static final String NUM_EMPLEADO = "numEmpleado";
    private static final String NOM = "nom";
    private static final String ES_TIEMPO_COMPLETO = "esTiempoCompleto";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLA + ";";
    private static final String SQL_SELECT_ALL_ORDERED = "SELECT * FROM " + TABLA+" ORDER BY "+ NOM +";";
    private static final String SQL_INSERT = "INSERT INTO " + TABLA + "(" + NUM_EMPLEADO + "," + NOM + ","
            + ES_TIEMPO_COMPLETO +") VALUES(?,?,?);";
    private static final String SQL_READ = "SELECT*FROM " + TABLA + " WHERE " + NUM_EMPLEADO + " = ?;";
    private static final String SQL_DELETE = "DELETE  FROM " + TABLA + " WHERE " + NUM_EMPLEADO + " = ?;";
    private static final String SQL_UPDATE = "UPDATE " + TABLA + " SET " 
            + NOM + " = ?, " + ES_TIEMPO_COMPLETO +" = ? WHERE " + NUM_EMPLEADO + " = ?;"; 
    private static final String SQL_DELETE_ALL = "DELETE FROM " + TABLA;
    /**
     * Constructor de la clase
     */
    public ProfesorDAO() {
        super();
    }

    /**
     * Devuelve un arreglo de los datos
     *
     * @return El arreglo de objetos
     * @throws Exception Devuelve error
     */
    public ArrayList<Profesor> readAll() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se utilza para almacenar los objetos
        ArrayList<Profesor> result = new ArrayList();
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
     * Devuelve un arreglo de los datos ordenados por nombre
     *
     * @return El arreglo de objetos
     * @throws Exception Devuelve error
     */
    public ArrayList<Profesor> readAllOrdered() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se utilza para almacenar los objetos
        ArrayList<Profesor> result = new ArrayList();
        //Manda al comando
        ps = conexion.prepareStatement(SQL_SELECT_ALL_ORDERED);
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
    public void append(Profesor dto) throws Exception {
        PreparedStatement ps = null;
        //Manda al comando
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setString(1, dto.getNumEmpleado());
        ps.setString(2, dto.getNom());
        ps.setBoolean(3, dto.isEsTiempoCompleto());
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
    public boolean update(Profesor dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_UPDATE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getNom());
        ps.setBoolean(2, dto.isEsTiempoCompleto());
        ps.setString(3, dto.getNumEmpleado());
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
    public boolean delete(Profesor dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getNumEmpleado());
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
    public Profesor read(Profesor dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profesor result = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_READ);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getNumEmpleado());
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
    private Profesor getObject(ResultSet rs) throws Exception {
        return new Profesor(rs.getString(NUM_EMPLEADO), rs.getString(NOM), rs.getBoolean(ES_TIEMPO_COMPLETO));
    }    
}
