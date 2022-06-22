package uv.fei.tutorias.utilidades;

import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class TablaProblematica_FechaReporte_EE_Profesor {
    private ProblematicaAcademica problematicaAcademica;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    private ExperienciaEducativa experienciaEducativa;
    private Profesor profesor;
    
    public TablaProblematica_FechaReporte_EE_Profesor() {
        this.problematicaAcademica = new ProblematicaAcademica();
        this.sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        this.experienciaEducativa = new ExperienciaEducativa();
        this.profesor = new Profesor();
    }

    public ProblematicaAcademica getProblematicaAcademica() {
        return problematicaAcademica;
    }

    public void setProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        this.problematicaAcademica = problematicaAcademica;
    }

    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica() {
        return sesionDeTutoriaAcademica;
    }

    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public String getTituloProblematicaAcademica() {
        return problematicaAcademica.getTitulo();
    }
    
    public String getFechaDeReporte() {
        return sesionDeTutoriaAcademica.getFechaConFormato();
    }
    
    public String getNombreExperienciaEducativa() {
        return experienciaEducativa.getNombre();
    }
    
    public String getNombreProfesor() {
        return profesor.getNombreCompleto();
    }
}
