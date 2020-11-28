
package conexion;

import java.io.Serializable;

/**
 * Describe el curso de la facultad
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 27.11.2020
 */
public class CursoDTO implements Serializable {
    private int clave;
    private String nombre;

    public CursoDTO(int clave, String nombre) {
        this.clave = clave;
        this.nombre = nombre;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "clave=" + clave + ", nombre=" + nombre;
    }
    
    
    
}
