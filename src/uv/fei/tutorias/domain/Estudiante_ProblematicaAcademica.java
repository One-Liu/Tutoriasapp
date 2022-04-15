package uv.fei.tutorias.domain;

// author @liu
public class Estudiante_ProblematicaAcademica {
    private int idEstudiante_ProblematicaAcademica;
    private Estudiante estudiante;
    private ProblematicaAcademica problematicaAcademica;

    // Getters of uv.fei.tutorias.domain.Estudiante_ProblematicaAcademica
    public int getIdEstudiante_ProblematicaAcademica() {
        return idEstudiante_ProblematicaAcademica;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public ProblematicaAcademica getProblematicaAcademica() {
        return problematicaAcademica;
    }

    // Setters of uv.fei.tutorias.domain.Estudiante_ProblematicaAcademica
    public void setIdEstudiante_ProblematicaAcademica(int idEstudiante_ProblematicaAcademica) {
        this.idEstudiante_ProblematicaAcademica = idEstudiante_ProblematicaAcademica;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        this.problematicaAcademica = problematicaAcademica;
    }
}