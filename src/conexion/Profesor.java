package conexion;

/**
 * Describe como es un profesor
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Profesor {
    private String numEmpleado;
    private String nom;

    public Profesor(String numEmpleado, String nom) {
        this.numEmpleado = numEmpleado;
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Profesor{" + "numEmpleado=" + numEmpleado + ", nom=" + nom + '}';
    }
    
    
}
