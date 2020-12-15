
package conexion;


/**
 * Describe como es una materia
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Materia {
    
    private int claveMateria;
    private String nom;

    public Materia(int claveMateria, String nom) {
        this.claveMateria = claveMateria;
        this.nom = nom;
    }

    public int getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(int claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Materia{" + "claveMateria=" + claveMateria + ", nom=" + nom +"}\n";
    }

    @Override
    public boolean equals(Object obj) {
        Materia materia = (Materia) obj;
        boolean esIgualClave = claveMateria == materia.getClaveMateria();
        boolean esIgualNom = nom == null ? materia.getNom() == null : nom.equals(materia.getNom());
        return esIgualClave && esIgualNom; 
    }

    
}
