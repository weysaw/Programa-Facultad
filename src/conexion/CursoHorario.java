
package conexion;

import java.io.Serializable;

/**
 * Describe el horario de la facultad
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class CursoHorario implements Serializable {
    
    private Profesor profesor;
    private Materia materia;
    private Curso curso;
    private Horario horario;

    public CursoHorario(Profesor profesor, Materia materia, Curso curso, Horario horario) {
        this.profesor = profesor;
        this.materia = materia;
        this.curso = curso;
        this.horario = horario;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "CursoHorario{" + "profesor=" + profesor + ", materia=" + materia + ", curso=" + curso + ", horario=" + horario + '}';
    }

    
    
    
}
