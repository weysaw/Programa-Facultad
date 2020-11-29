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
    
    private static final String TABLA = "CURSO";
    private static final String NUM_EMPLEADO = "numEmpleado";
    private static final String CLAVE_MATERIA = "claveMateria";
    private static final String GRUPO = "grupo";
    private static final String TIPO = "tipo";
    private static final String HRS_TC = "hrsTC";
    private static final String HRS_ASIG = "hrsAsig";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLA;
    private static final String SQL_INSERT = "INSERT INTO "+TABLA+"("+NUM_EMPLEADO+","+CLAVE_MATERIA+","
            + GRUPO + ", "+ TIPO +", "+ HRS_TC +", "+ HRS_ASIG +") VALUES(?,?,?,?,?,?);";
    private static final String SQL_READ = "SELECT*FROM "+ TABLA +" WHERE " + NUM_EMPLEADO + " = ? AND "
            + CLAVE_MATERIA +"= ? AND"+ GRUPO +"= ? AND"+ TIPO +"= ?;";
    private static final String SQL_DELETE = "DELETE  FROM "+ TABLA +"WHERE "+ NUM_EMPLEADO + " = ? AND"  
            + CLAVE_MATERIA + "= ? AND"+ GRUPO +"= ? AND"+ TIPO +"= ?;";
    private static final String SQL_UPDATE = "UPDATE "+ TABLA +" SET " + HRS_TC + " = ?, "
            + HRS_ASIG +" = ? WHERE " + NUM_EMPLEADO + " = ? AND" 
            + CLAVE_MATERIA + "= ? AND"+ GRUPO +"= ? AND"+ TIPO +"= ?;";

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
        while (rs.next()) 
            result.add(getObject(rs));    
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
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getGrupo());
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
    public void update(Curso dto) throws Exception {
        PreparedStatement ps = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_UPDATE);
        //Les asigna los valores que deben tener los ?
        ps.setInt(1, dto.getHrsTC());
        ps.setInt(2, dto.getHrsAsig());
        ps.setInt(3, dto.getProfesor().getNumEmpleado());
        ps.setInt(4, dto.getMateria().getClaveMateria());
        ps.setInt(5, dto.getGrupo());
        ps.setString(6, dto.getTipo());
        //Ejecuta el comando y actualiza
        ps.executeUpdate();
        //Cierra la conexión
        cerrar(ps);
    }
    
    
    /**
     * Borra un dato de la tabla
     * 
     * @param dto Es el dto que se borra
     * @throws Exception Devuelve error
     */
    public void delete(Curso dto) throws Exception {
        PreparedStatement ps = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getGrupo());
        ps.setString(4, dto.getTipo());
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
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getGrupo());
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
     * Detecta el objeto enviado por el resultset y lo devuelve convertido a dto
     * 
     * @param rs Es la salida de la consola
     * @return Devuelve el objeto dto
     * @throws Exception Devuelve un error
     */
    private Curso getObject(ResultSet rs) throws Exception {
        int grupo = Integer.parseInt(rs.getString(GRUPO));
        String tipo = rs.getString(TIPO);
        int hrsTc = Integer.parseInt(rs.getString(HRS_TC));
        int hrsAsig = Integer.parseInt(rs.getString(HRS_ASIG));
        int numEmpleado = Integer.parseInt(NUM_EMPLEADO);
        Profesor profesor = new ProfesorDAO().read(new Profesor(numEmpleado, null));
        int claveMateria = Integer.parseInt(rs.getString(CLAVE_MATERIA));
        Materia materia = new MateriaDAO().read(new Materia(claveMateria, null,null));
        
        return new Curso(profesor, materia, grupo, tipo, hrsTc, hrsAsig);
    }
 }
