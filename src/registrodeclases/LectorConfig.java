package registrodeclases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Este objeto lee el archivo de configuracion
 *
 * @author Raymundo Perez Valdez
 * @version 19 dic. 2020
 */
public class LectorConfig {

    private String direccion;
    private String puerto;
    private boolean esRemoto;
    private String usuarioMySQL;
    private String contrasenaMySQL;
    private String usuarioSSH;
    private String contrasenaSSH;

    public LectorConfig() {
        leerConfig();
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean isEsRemoto() {
        return esRemoto;
    }

    public String getUsuarioMySQL() {
        return usuarioMySQL;
    }

    public String getContrasenaMySQL() {
        return contrasenaMySQL;
    }

    public String getUsuarioSSH() {
        return usuarioSSH;
    }

    public String getContrasenaSSH() {
        return contrasenaSSH;
    }

    public String getPuerto() {
        return puerto;
    }

    /**
     * Lee la configuracion del archivo 
     * 
     */
    private void leerConfig() {
        BufferedReader reader;
        try { //lectura del archivo con maestros
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("./config.txt"), "UTF-8"));
            String line;
            String campos[];
            while ((line = reader.readLine()) != null) {
                if (!Pattern.matches("^#.*", line) && Pattern.matches(".*=.*", line)) { //Si la linea comienza en #, es ignorada
                    campos = line.split("=");
                    if (Pattern.matches(".*direccion.+", line)) //si es la direccion
                    {
                        direccion = campos[1];
                    } else if (Pattern.matches(".*puerto.+", line)) //si es el puerto
                    {
                        puerto = campos[1];
                    } else if (Pattern.matches(".*remoto.+", line)) //si es remoto
                    {
                        esRemoto = campos[1].equals("true");
                    } else if ((Pattern.matches(".*usuarioMySQL.+", line))) //si es usuarioMySQL
                    {
                        usuarioMySQL = campos[1];
                    } else if ((Pattern.matches(".*contraseñaMySQL.+", line))) //si es contrasenaMySQL
                    {
                        contrasenaMySQL = campos[1];
                    } else if ((Pattern.matches(".*usuarioSSH.+", line))) //si es usuarioSSH
                    {
                        usuarioSSH = campos[1];
                    } else if ((Pattern.matches(".*contraseñaSSH.+", line))) //si es ContrasenaSSH
                    {
                        contrasenaSSH = campos[1];
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ARCHIVO DE CONFIGURACIÓN NO ENCONTRADO", "ERROR"
                    ,JOptionPane.ERROR_MESSAGE);

        }
    }
}
