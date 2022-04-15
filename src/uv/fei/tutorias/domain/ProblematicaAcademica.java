package uv.fei.tutorias.domain;

// author @liu
public class ProblematicaAcademica {
    private int idProblematicaAcademica;
    private String titulo;
    private String descripcion;
    private ExperienciaEducativa experienciaEducativa;
    private SolucionAProblematicaAcademica solucionAProblematicaAcademica;

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

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public SolucionAProblematicaAcademica getSolucionAProblematicaAcademica() {
        return solucionAProblematicaAcademica;
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

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public void setSolucionAProblematicaAcademica(SolucionAProblematicaAcademica solucionAProblematicaAcademica) {
        this.solucionAProblematicaAcademica = solucionAProblematicaAcademica;
    }
}