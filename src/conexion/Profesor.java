package conexion;

/**
 * Describe como es un profesor
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Profesor {
    private int numEmpleado;
    private String nom;

    public Profesor(int numEmpleado, String nom) {
        this.numEmpleado = numEmpleado;
        this.nom = nom;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
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
