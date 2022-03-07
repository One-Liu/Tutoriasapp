package uv.fei.tutorias.domain;

// author @liu

public class ProblematicaAcademica() {
    private int idProblematicaAcademica;
    private String descripcion;
    private int idExperienciaEducativa;

    // Getters of uv.fei.tutorias.domain.ProblematicaAcademica
    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    // Setters of uv.fei.tutorias.domain.ProblematicaAcademica
    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }
}