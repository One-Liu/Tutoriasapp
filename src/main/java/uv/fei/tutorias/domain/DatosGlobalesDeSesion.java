package uv.fei.tutorias.domain;

// author @liu
public class DatosGlobalesDeSesion {
    private static DatosGlobalesDeSesion instancia;
    private TutorAcademico tutorAcademico;
    private Coordinador coordinador;
    private JefeDeCarrera jefeDeCarrera;
    
    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public JefeDeCarrera getJefeDeCarrera() {
        return jefeDeCarrera;
    }

    // Solo se guarda el usuario que ingrese
    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.coordinador = null;
        this.jefeDeCarrera = null;
        this.tutorAcademico = tutorAcademico;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.tutorAcademico = null;
        this.jefeDeCarrera = null;
        this.coordinador = coordinador;
    }

    public void setJefeDeCarrera(JefeDeCarrera jefeDeCarrera) {
        this.tutorAcademico = null;
        this.coordinador = null;
        this.jefeDeCarrera = jefeDeCarrera;
    }
    
    private DatosGlobalesDeSesion() {
    }
    
    public static DatosGlobalesDeSesion getDatosGlobalesDeSesion() {
        if(instancia == null) {
            instancia = new DatosGlobalesDeSesion();
        }
        return instancia;
    }
}
