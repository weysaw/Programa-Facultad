
package conexion;

import java.io.Serializable;

/**
 * Describe como es una materia
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Materia implements Serializable {
    
    private int claveMateria;
    private String nom;
    private String planDeEstudio;

    public Materia(int claveMateria, String nom, String planDeEstudio) {
        this.claveMateria = claveMateria;
        this.nom = nom;
        this.planDeEstudio = planDeEstudio;
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

    public String getPlanDeEstudio() {
        return planDeEstudio;
    }

    public void setPlanDeEstudio(String planDeEstudio) {
        this.planDeEstudio = planDeEstudio;
    }

    @Override
    public String toString() {
        return "Materia{" + "claveMateria=" + claveMateria + ", nom=" + nom + ", planDeEstudio=" + planDeEstudio + "}\n";
    }

    @Override
    public boolean equals(Object obj) {
        Materia materia = (Materia) obj;
        boolean esIgualClave = claveMateria == materia.getClaveMateria();
        boolean esIgualNom = nom == null ? materia.getNom() == null : nom.equals(materia.getNom());
        boolean esIgualPlan = planDeEstudio == null ? materia.getPlanDeEstudio() == null : planDeEstudio.equals(materia.getPlanDeEstudio());
        return esIgualClave && esIgualNom && esIgualPlan; 
    }

    
}
