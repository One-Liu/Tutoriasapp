package uv.fei.tutorias.domain;

public class SolucionAProblematicaAcademica {
    private int idSolucionAProblematicaAcademica;
    private  String descripcion;

    public SolucionAProblematicaAcademica(int idSolucionAProblematicaAcademica, String descripcion) {
        this.idSolucionAProblematicaAcademica = idSolucionAProblematicaAcademica;
        this.descripcion = descripcion;
    }

    public SolucionAProblematicaAcademica(int idSolucionAProblematicaAcademica) {
        this.idSolucionAProblematicaAcademica = idSolucionAProblematicaAcademica;
    }

    public SolucionAProblematicaAcademica(String descripcion) {
        this.descripcion = descripcion;
    }

    public SolucionAProblematicaAcademica() {
        this.idSolucionAProblematicaAcademica = 0;
        this.descripcion = "";
    }

    public int getIdSolucionAProblematicaAcademica() {
        return idSolucionAProblematicaAcademica;
    }

    public void setIdSolucionAProblematicaAcademica(int idSolucionAProblematicaAcademica) {
        this.idSolucionAProblematicaAcademica = idSolucionAProblematicaAcademica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
