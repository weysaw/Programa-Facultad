package conexion;

import java.util.Objects;
/**
 * Describe como es un profesor
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Profesor {
    private String numEmpleado;
    private String nom;
    private boolean esTiempoCompleto;

    public Profesor(String numEmpleado, String nom, boolean esTiempoCompleto) {
        this.numEmpleado = numEmpleado;
        this.nom = nom;
        this.esTiempoCompleto = esTiempoCompleto;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isEsTiempoCompleto() {
        return esTiempoCompleto;
    }

    public void setEsTiempoCompleto(boolean esTiempoCompleto) {
        this.esTiempoCompleto = esTiempoCompleto;
    }
    
    @Override
    public String toString() {
        return "Profesor{" + "numEmpleado=" + numEmpleado + ", nom=" + nom + " esTiempoCompleto = "+ esTiempoCompleto +"}\n";
    }   
}
