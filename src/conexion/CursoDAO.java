package conexion;

import java.util.*;
import java.sql.*;

/**
 * Es la clase curso que se comunica con la base de datos
 *
 * @author Ornelas Munguía Axel Leonardo
 * @version 03.12.2020
 */
public class CursoDAO extends ConexionBD {

    private static final String TABLA = "CURSO";
    private static final String NUM_EMPLEADO = "numEmpleado";
    private static final String CLAVE_MATERIA = "claveMateria";
    private static final String GRUPO = "grupo";
    private static final String TIPO = "tipo";
    private static final String HRS_TC = "hrsTC";
    private static final String HRS_ASIG = "hrsAsig";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLA;
    private static final String SQL_INSERT = "INSERT INTO " + TABLA + "(" + NUM_EMPLEADO + "," + CLAVE_MATERIA + ","
            + GRUPO + ", " + TIPO + ", " + HRS_TC + ", " + HRS_ASIG + ") VALUES(?,?,?,?,?,?);";
    private static final String SQL_READ = "SELECT*FROM " + TABLA + " WHERE " + NUM_EMPLEADO + " = ? AND "
            + CLAVE_MATERIA + "= ? AND " + GRUPO + "= ? AND " + TIPO + "= ?;";
    private static final String SQL_DELETE = "DELETE FROM " + TABLA + " WHERE " + NUM_EMPLEADO + "=? AND "
            + CLAVE_MATERIA + "=? AND " + GRUPO + "=? AND " + TIPO + "=?;";
    private static final String SQL_UPDATE = "UPDATE " + TABLA + " SET " + HRS_TC + " = ?, "
            + HRS_ASIG + " = ? WHERE " + NUM_EMPLEADO + " = ? AND "
            + CLAVE_MATERIA + "= ? AND " + GRUPO + "= ? AND " + TIPO + "= ?;";
    private static final String SQL_DELETE_ALL = "DELETE FROM " + TABLA;

    /**
     * Constructor de la clase
     */
    public CursoDAO() {
        super();
    }

    /**
     * Devuelve un arreglo de los datos
     *
     * @return El arreglo de objetos
     * @throws Exception Devuelve error
     */
    public ArrayList<Curso> readAll() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se utilza para almacenar los objetos
        ArrayList<Curso> result = new ArrayList();
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
    public void append(Curso dto) throws Exception {
        PreparedStatement ps = null;
        //Manda al comando
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setString(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setString(3, dto.getGrupo());
        ps.setString(4, dto.getTipo());
        ps.setInt(5, dto.getHrsTC());
        ps.setInt(6, dto.getHrsAsig());
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
    public boolean update(Curso dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_UPDATE);
        //Les asigna los valores que deben tener los ?
        ps.setInt(1, dto.getHrsTC());
        ps.setInt(2, dto.getHrsAsig());
        ps.setString(3, dto.getProfesor().getNumEmpleado());
        ps.setInt(4, dto.getMateria().getClaveMateria());
        ps.setString(5, dto.getGrupo());
        ps.setString(6, dto.getTipo());
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
    public boolean delete(Curso dto) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setString(3, dto.getGrupo());
        ps.setString(4, dto.getTipo());
        //Ejecuta el comando y actualiza
        datosModificados = ps.executeUpdate() > 0;
        //Cierra la conexión
        cerrar(ps);
        return datosModificados;
    }

    /**
     * Borra un dato de la tabla
     *
     * @param numEmpleado Número del empleado que da el curso a eliminar
     * @param claveMateria Clave de la materia que se imparte en el curso a eliminar
     * @param grupo Grupo del curso a eliminar
     * @param tipo Tipo del curso a eliminar
     * @throws Exception Devuelve error
     */
    public boolean delete(String numEmpleado, int claveMateria, String grupo, String tipo) throws Exception {
        PreparedStatement ps = null;
        boolean datosModificados;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, numEmpleado);
        ps.setInt(2, claveMateria);
        ps.setString(3, grupo);
        ps.setString(4, tipo);
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
    public Curso read(Curso dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Curso result = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_READ);
        //Les asigna los valores que deben tener los ?
        ps.setString(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setString(3, dto.getGrupo());
        ps.setString(4, dto.getTipo());
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
     * Lee la información de un profesor en especifico
     *
     * @param dto Es el profesor a buscar
     * @return Devuelve el objeto dto si lo encuentra
     * @throws Exception Devuelve un error
     */
    public String devolverSuma(String campo) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        //Manda el comando
        ps = conexion.prepareStatement("SELECT SUM(" + campo + ") AS SUMA FROM " + TABLA);
        //Ejecuta el comando y devuelve el resultado del comando
        rs = ps.executeQuery();
        //Recorre por todos los resultados
        if (rs.next()) {
            result = rs.getString("SUMA");
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
    private Curso getObject(ResultSet rs) throws Exception {
        //Se obtienen los datos de la tabla
        String grupo = rs.getString(GRUPO);
        String tipo = rs.getString(TIPO);
        int hrsTc = Integer.parseInt(rs.getString(HRS_TC));
        int hrsAsig = Integer.parseInt(rs.getString(HRS_ASIG));
        String numEmpleado = rs.getString(NUM_EMPLEADO);
        int claveMateria = Integer.parseInt(rs.getString(CLAVE_MATERIA));
        ProfesorDAO profesorDAO = new ProfesorDAO();
        MateriaDAO materiaDAO = new MateriaDAO();
        
        profesorDAO.setConexion(conexion);
        materiaDAO.setConexion(conexion);
        
        //Se busca el profesor con el numero de empleado indicado
        Profesor profesor = profesorDAO.read(new Profesor(numEmpleado, null, false));
        //Se busca la materia con la clave de la materia indicado
        Materia materia = materiaDAO.read(new Materia(claveMateria, null));
        
        return new Curso(profesor, materia, grupo, tipo, hrsTc, hrsAsig);
    }
}
