package conexion;

/**
 * Describe la tasbla auxiliar
 * @author Raymundo Perez
 */
public class AuxDTO {    
    private boolean triggerActivo;
    private String semestre;

    public AuxDTO(boolean triggerActivo, String semestre) {
        this.triggerActivo = triggerActivo;
        this.semestre = semestre;
    }

    public boolean isTriggerActivo() {
        return triggerActivo;
    }

    public void setTriggerActivo(boolean triggerActivo) {
        this.triggerActivo = triggerActivo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

@Override
    public String toString() {
        return "AuxDTO{" + "triggerActivo=" + triggerActivo + ", semestre=" + semestre +"}\n";
    }    
    
}
