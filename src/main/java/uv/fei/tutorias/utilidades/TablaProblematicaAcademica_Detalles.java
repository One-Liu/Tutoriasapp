package uv.fei.tutorias.utilidades;

import uv.fei.tutorias.domain.ProblematicaAcademica;

public class TablaProblematicaAcademica_Detalles {
    private String fechaDeTutoria;
    private String nombreEE;
    private String tituloProblematicaAcademica;

    public ProblematicaAcademica getProblematicaAcademica() {
        return problematicaAcademica;
    }

    public void setProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        this.problematicaAcademica = problematicaAcademica;
    }

    public ProblematicaAcademica problematicaAcademica;

    public TablaProblematicaAcademica_Detalles() {
        this.fechaDeTutoria = "";
        this.nombreEE = "";
        this.tituloProblematicaAcademica = "";
    }

    public TablaProblematicaAcademica_Detalles(String fechaDeTutoria, String nombreEE, String tituloProblematicaAcademica) {
        this.fechaDeTutoria = fechaDeTutoria;
        this.nombreEE = nombreEE;
        this.tituloProblematicaAcademica = tituloProblematicaAcademica;
    }

    public String getFechaDeTutoria() {
        return fechaDeTutoria;
    }

    public void setFechaDeTutoria(String fechaDeTutoria) {
        this.fechaDeTutoria = fechaDeTutoria;
    }

    public String getNombreEE() {
        return nombreEE;
    }

    public void setNombreEE(String nombreEE) {
        this.nombreEE = nombreEE;
    }

    public String getTituloProblematicaAcademica() {
        return tituloProblematicaAcademica;
    }

    public void setTituloProblematicaAcademica(String tituloProblematicaAcademica) {
        this.tituloProblematicaAcademica = tituloProblematicaAcademica;
    }

    @Override
    public String toString() {
        return "TablaProblematicaAcademica_Detalles{" +
                "fechaDeTutoria='" + fechaDeTutoria + '\'' +
                ", nombreEE='" + nombreEE + '\'' +
                ", tituloProblematicaAcademica='" + tituloProblematicaAcademica + '\'' +
                '}';
    }
}
