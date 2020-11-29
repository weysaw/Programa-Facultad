package conexion;

import java.util.*;
import java.sql.*;

/**
 * Es la clase curso que se comunica con la base de datos
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 27.11.2020
 */
public class CursoHorarioDAO extends ConexionBD {
    
    private static final String TABLA = "CURSO_HORARIO";
    private static final String NUM_EMPLEADO = "numEmpleado";
    private static final String CLAVE_MATERIA = "claveMateria";
    private static final String GRUPO = "grupo";
    private static final String TIPO = "tipo";
    private static final String CLAVE_HORARIO = "claveHorario";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLA;
    private static final String SQL_INSERT = "INSERT INTO "+TABLA+"("+NUM_EMPLEADO+","
            +CLAVE_MATERIA+", "+ GRUPO +", "+ TIPO +", "+ CLAVE_HORARIO +") VALUES(?,?,?,?,?)";
    private static final String SQL_READ = "SELECT*FROM "+ TABLA +" WHERE " 
            + NUM_EMPLEADO + " = ? AND "+ CLAVE_MATERIA +" = ? AND" + GRUPO +" = ? AND" 
            + TIPO +" = ? AND" + CLAVE_HORARIO +" = ?;"  ;
    private static final String SQL_DELETE = "DELETE  FROM "+ TABLA +" WHERE " 
            + NUM_EMPLEADO + " = ? AND "+ CLAVE_MATERIA +" = ? AND" + GRUPO +" = ? AND" 
            + TIPO +" = ? AND" + CLAVE_HORARIO +" = ?;"  ;

    /**
     * Constructor de la clase
     */
    public CursoHorarioDAO() {
        super();
    }
    /**
     * Devuelve un arreglo de los datos
     * 
     * @return El arreglo de objetos
     * @throws Exception Devuelve error
     */
    public ArrayList<CursoHorario> readAll() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se utilza para almacenar los objetos
        ArrayList<CursoHorario> result = new ArrayList();
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
    public void append(CursoHorario dto) throws Exception {
        PreparedStatement ps = null;
        //Manda al comando
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getCurso().getGrupo());
        ps.setString(4, dto.getCurso().getTipo());
        ps.setInt(5, dto.getHorario().getClaveHorario());
        //Ejecuta el comando y acutaliza
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
    public void delete(CursoHorario dto) throws Exception {
        PreparedStatement ps = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_DELETE);
        //Les asigna los valores que deben tener los ?
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getCurso().getGrupo());
        ps.setString(4, dto.getCurso().getTipo());
        ps.setInt(5, dto.getHorario().getClaveHorario());
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
    public CursoHorario read(CursoHorario dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CursoHorario result = null;
        //Manda el comando
        ps = conexion.prepareStatement(SQL_READ);
        //Les asigna los valores que deben tener los ?
        ps.setInt(1, dto.getProfesor().getNumEmpleado());
        ps.setInt(2, dto.getMateria().getClaveMateria());
        ps.setInt(3, dto.getCurso().getGrupo());
        ps.setString(4, dto.getCurso().getTipo());
        ps.setInt(5, dto.getHorario().getClaveHorario());
        //Ejecuta el comando y devuelve el resultado del comando
        rs = ps.executeQuery();
        //Recorre por todos los resultados
        if (rs.next()) 
            result = getObject(rs);        
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
    private CursoHorario getObject(ResultSet rs) throws Exception {
        int numEmpleado = Integer.parseInt(rs.getString(NUM_EMPLEADO));
        int claveMateria = Integer.parseInt(rs.getString(CLAVE_MATERIA));
        int grupo = Integer.parseInt(rs.getString(GRUPO));
        String tipo = rs.getString(TIPO);
        int claveHorario = Integer.parseInt(rs.getString(CLAVE_HORARIO));
        Profesor profesor = new ProfesorDAO().read(new Profesor(numEmpleado,null));
        Materia materia = new MateriaDAO().read(new Materia(claveMateria, null, null));
        Curso curso = new CursoDAO().read(new Curso(profesor,materia,grupo,tipo,0,0));
        Horario horario = new HorarioDAO().read(new Horario(claveHorario,null,null,null,null));
        
        return new CursoHorario(profesor, materia, curso, horario);
    }
 }
