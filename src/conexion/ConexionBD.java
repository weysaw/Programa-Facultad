package conexion;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 * Se encarga de conectar la base de datos con el programa.
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 27.11.2020
 */
public class ConexionBD {
    
    protected Connection conexion; 
    //Nombre de la bd
    private static final String BD = "profesorMateria";
    //Se encarga de la zona del tiempo
    private static final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //Es el url de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD + TIMEZONE;
    //Indica el driver que se debe usar
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Indicia el usuario
    private static final String USER = "root";
    //Es la contraseña del usuario
    private static final String PASSWORD = "1234";
    
    //Constructor de la clase
    public ConexionBD() {
        conexion = null;
        try {
            //Busca el driver de la base de datos
            Class.forName(DRIVER);
            //Intenta conectarse
            conexion = DriverManager.getConnection(URL,USER, PASSWORD);
            //Si hay una conexión se lo indica
            if (conexion != null) 
                System.out.println("Conexión establecida...");
            
        } catch (ClassNotFoundException | SQLException ex) {
            //Si hay error le indica porque fue 
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Le indica la conexión
     * 
     * @return Devuelve la conexión 
     */
    public ConexionBD getConexionBD() {
        return (ConexionBD) conexion;
    }
    
    /**
     * Cierra la conexión de comandos de mysql
     * 
     * @param ps Es la conexión con la base de datos para mandar comandos
     * @throws Exception Es el error
     */
    protected void cerrar(PreparedStatement ps) throws Exception {
        if (ps != null)
            ps.close();
    }
    
    /**
     * Cierra la conexión de comandos de resultados MySQL
     * 
     * @param rs Es la conexión con la base de datos para mandar comandos
     * @throws Exception Es el error
     */
    protected void cerrar(ResultSet rs) throws Exception {
        if (rs != null)
            rs.close();
    }
}
