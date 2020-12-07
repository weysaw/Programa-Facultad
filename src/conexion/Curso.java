
package conexion;

import java.io.Serializable;

/**
 * Describe el curso de la facultad
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 27.11.2020
 */
public class Curso implements Serializable {
    
    private Profesor profesor;
    private Materia materia;
    private String grupo;
    private String tipo;
    private int hrsTC;
    private int hrsAsig;

    public Curso(Profesor profesor, Materia materia, String grupo, String tipo, int hrsTC, int hrsAsig) {
        this.profesor = profesor;
        this.materia = materia;
        this.grupo = grupo;
        this.tipo = tipo;
        this.hrsTC = hrsTC;
        this.hrsAsig = hrsAsig;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHrsTC() {
        return hrsTC;
    }

    public void setHrsTC(int hrsTC) {
        this.hrsTC = hrsTC;
    }

    public int getHrsAsig() {
        return hrsAsig;
    }

    public void setHrsAsig(int hrsAsig) {
        this.hrsAsig = hrsAsig;
    }

    @Override
    public String toString() {
        return "Curso{" + "profesor=" + profesor + ", materia=" + materia + ", grupo=" + grupo + ", tipo=" + tipo + ", hrsTC=" + hrsTC + ", hrsAsig=" + hrsAsig + "\n}";
    }
    
    
    
}
