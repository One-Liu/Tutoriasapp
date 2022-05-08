package uv.fei.tutorias.domain;

// author @liu

public class EstudianteProblematicaAcademica {
    private int idEstudiante_ProblematicaAcademica;
    private int idEstudiante;
    private int idProblematicaAcademica;

    public EstudianteProblematicaAcademica(int idEstudiante_ProblematicaAcademica, int idEstudiante, int idProblematicaAcademica) {
        this.idEstudiante_ProblematicaAcademica = idEstudiante_ProblematicaAcademica;
        this.idEstudiante = idEstudiante;
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public EstudianteProblematicaAcademica() {
    }

    public EstudianteProblematicaAcademica(int idEstudiante, int idProblematicaAcademica) {
        this.idEstudiante = idEstudiante;
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    // Getters of uv.fei.tutorias.domain.EstudianteProblematicaAcademica
    public int getIdEstudiante_ProblematicaAcademica() {
        return idEstudiante_ProblematicaAcademica;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    // Setters of uv.fei.tutorias.domain.EstudianteProblematicaAcademica
    public void setIdEstudiante_ProblematicaAcademica(int idEstudiante_ProblematicaAcademica) {
        this.idEstudiante_ProblematicaAcademica = idEstudiante_ProblematicaAcademica;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }
}