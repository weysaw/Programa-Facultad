
package conexion;

import java.io.Serializable;
import java.sql.Time;

/**
 * Describe el horario de la facultad
 * 
 * @author Ornelas Munguía Axel Leonardo
 * @version 28.11.2020
 */
public class Horario implements Serializable {
    
    //Deprecated
    private int claveHorario;//No borrar porque se quiebra, se debe optimizar
    private String dia;
    private String turno;
    private Time hrInicio;
    private Time hrFin;

    public Horario(int claveHorario, String dia, String turno, Time hrInicio, Time hrFin) {
        this.claveHorario = claveHorario;
        this.dia = dia;
        this.turno = turno;
        this.hrInicio = hrInicio;
        this.hrFin = hrFin;
    }

    public int getClaveHorario() {
        return claveHorario;
    }

    public void setClaveHorario(int claveHorario) {
        this.claveHorario = claveHorario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Time getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(Time hrInicio) {
        this.hrInicio = hrInicio;
    }

    public Time getHrFin() {
        return hrFin;
    }

    public void setHrFin(Time hrFin) {
        this.hrFin = hrFin;
    }

    @Override
    public String toString() {
        return "Horario{" + "claveHorario=" + claveHorario + ", dia=" + dia + ", turno=" + turno + ", hrInicio=" + hrInicio + ", hrFin=" + hrFin + "}\n";
    }
    
    
    
}
