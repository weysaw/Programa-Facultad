
package conexion;

import java.io.Serializable;

/**
 * Describe el horario de la facultad
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class CursoHorario implements Serializable {
    
    private Curso curso;
    private Horario horario;

    public CursoHorario(Curso curso, Horario horario) {
        this.curso = curso;
        this.horario = horario;
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
        return "CursoHorario{" + "curso=" + curso + ", horario=" + horario + '}';
    }


}
