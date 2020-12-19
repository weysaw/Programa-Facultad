package conexion;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import registrodeclases.LectorConfig;

/**
 * Se encarga de conectar la base de datos con el programa.
 *
 * @author Ornelas Munguía Axel Leonardo
 * @version 04.12.2020
 */
public class ConexionBD {

    protected Connection conexion;
    private Session session;
    //Nombre de la bd
    private static final String BD = "profesorMateria";
    //Se encarga de la zona del tiempo
    private static final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //Es el puerto
    private static String puerto;
    //Es el url de la base de datos
    private static String url = "jdbc:mysql://";
    //Indica el driver que se debe usar
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Indicia el usuario
    private static String userBD;
    //Es la contraseña del usuario
    private static String passwordBD;
    //Es el usuario de la conexión SSH
    private static String userSSH;
    //Es la contraseña de la conexión ssh
    private static String passSSH;
    //El servidor de la conexión ssh
    private static String direccionSSH;
    private static boolean esSSH;
            
    //Constructor de la clase
    public ConexionBD() {
        conexion = null;
        session = null;
    }

    /**
     * Abre la conexión de base de SSH
     */
    public void abrirSSH() {
        if (esSSH) {
            try {
                //Crea las propiedades de la conexión
                Properties config = new Properties();
                //Indica las propiedades
                config.put("StrictHostKeyChecking", "no");
                //Crea el objeto de la conexión
                JSch jsch = new JSch();
                //Asigna a donde debe conectar
                session = jsch.getSession(userSSH, direccionSSH, 22);
                //Asigna la contraseña de la conexión
                session.setPassword(passSSH);
                //Asigna la configuración
                session.setConfig(config);
                //Realiza la conexión ssh
                session.connect();
                //Asigna el puerto de la conexión ssh a otro
                session.setPortForwardingL(5050, "localhost", 3306);
            } catch (JSchException ex) {
                JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN SSH:\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                cerrarSSH();
            }
        }
    }

    /**
     * Se conecta con la base de datos
     */
    public void abrirConexion() {
        try {
            //Busca el driver de la base de datos
            Class.forName(DRIVER);
            //Intenta conectarse
            conexion = DriverManager.getConnection(url + puerto +"/"+ BD + TIMEZONE, userBD, passwordBD);
            //Si hay una conexión se lo indica
            if (conexion != null) {
                System.out.println("Conexión establecida...");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Si hay error le indica porque fue 
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN BASE DE DATOS:\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Le indica la conexión
     *
     * @return Devuelve la conexión
     */
    public Connection getConexionBD() {
        return conexion;
    }

    /**
     * Se indica la conexión
     *
     * @param conexion Es la conexion que se indica
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Cierra la conexión de comandos de mysql
     *
     * @param ps Es la conexión con la base de datos para mandar comandos
     * @throws Exception Es el error
     */
    protected void cerrar(PreparedStatement ps) throws Exception {
        if (ps != null) {
            ps.close();
        }
    }

    /**
     * Cierra la conexión de comandos de resultados MySQL
     *
     * @param rs Es la conexión con la base de datos para mandar comandos
     * @throws Exception Es el error
     */
    protected void cerrar(ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Cierra la conexión SSH
     */
    public final void cerrarSSH() {
        //Si esta conectado la cierra
        if (session != null && session.isConnected()) {
            System.out.println("Cerrar SSH...");
            session.disconnect();
        }
    }
    /**
     * Lee la configuracion del archivo de texto
     */
    public static void leerConfig() {
        LectorConfig lector = new LectorConfig();
        //Asigna la información de cada uno
        puerto = ":" + lector.getPuerto();
        url += lector.getDireccion();
        esSSH = lector.isEsRemoto();
        userSSH = lector.getUsuarioSSH();
        passSSH = lector.getContrasenaSSH();
        userBD = lector.getUsuarioMySQL();
        passwordBD = lector.getContrasenaMySQL();
    }
    
}
