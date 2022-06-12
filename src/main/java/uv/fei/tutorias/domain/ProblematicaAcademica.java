package uv.fei.tutorias.domain;

// author @liu

public class ProblematicaAcademica {
    private int idProblematicaAcademica;
    private String titulo;
    private String descripcion;
    private int idExperienciaEducativa;

    public ProblematicaAcademica(int idProblematicaAcademica, String titulo, String descripcion, int idExperienciaEducativa) {
        this.idProblematicaAcademica = idProblematicaAcademica;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public ProblematicaAcademica(String titulo, String descripcion, int idExperienciaEducativa) {
        this.idProblematicaAcademica = 0;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public ProblematicaAcademica() {
        this.idProblematicaAcademica = 0;
        this.titulo = "";
        this.descripcion = "";
        this.idExperienciaEducativa = 0;

    }

    // Getters of uv.fei.tutorias.domain.ProblematicaAcademica
    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public String getTitulo() {
        return titulo;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    @Override
    public String toString(){
        return descripcion;
    }
}