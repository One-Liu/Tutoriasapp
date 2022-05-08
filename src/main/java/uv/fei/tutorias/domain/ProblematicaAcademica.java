package uv.fei.tutorias.domain;

// author @liu

public class ProblematicaAcademica {
    private int idProblematicaAcademica;
    private String descripcion;
    private int idExperienciaEducativa;

    public ProblematicaAcademica(int idProblematicaAcademica, String descripcion, int idExperienciaEducativa) {
        this.idProblematicaAcademica = idProblematicaAcademica;
        this.descripcion = descripcion;
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public ProblematicaAcademica(String descripcion, int idExperienciaEducativa) {
        this.descripcion = descripcion;
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public ProblematicaAcademica() {
        this.idProblematicaAcademica = 0;
        this.descripcion = "";
        this.idExperienciaEducativa = 0;

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProblematicaAcademica)) return false;

        ProblematicaAcademica that = (ProblematicaAcademica) o;

        if (getIdProblematicaAcademica() != that.getIdProblematicaAcademica()) return false;
        if (getIdExperienciaEducativa() != that.getIdExperienciaEducativa()) return false;
        return getDescripcion() != null ? getDescripcion().equals(that.getDescripcion()) : that.getDescripcion() == null;
    }

    @Override
    public int hashCode() {
        int result = getIdProblematicaAcademica();
        result = 31 * result + (getDescripcion() != null ? getDescripcion().hashCode() : 0);
        result = 31 * result + getIdExperienciaEducativa();
        return result;
    }
}