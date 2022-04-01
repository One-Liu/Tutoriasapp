package uv.fei.tutorias.domain;

// author @liu
public class SolucionAProblematicaAcademica {
    private int idSolucionAProblematicaAcademica;
    private String fecha;
    private String descripcion;

    // Getters of uv.fei.tutorias.domain.SolucionAProblematicaAcademica
    public int getIdSolucionAProblematicaAcademica() {
        return idSolucionAProblematicaAcademica;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters of uv.fei.tutorias.domain.SolucionAProblematicaAcademica
    public void setIdSolucionAProblematicaAcademica(int idSolucionAProblematicaAcademica) {
        this.idSolucionAProblematicaAcademica = idSolucionAProblematicaAcademica;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
