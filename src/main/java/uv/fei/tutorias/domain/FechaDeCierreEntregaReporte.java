package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaReporte {
    private int idFechaDeCierreEntregaReporte;
    private String fecha;
    private int idReporteDeTutoriaAcademica;
    
    // Constructors of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public FechaDeCierreEntregaReporte() {
        this.idFechaDeCierreEntregaReporte = 0;
        this.fecha = "";
        this.idReporteDeTutoriaAcademica = 0;
    }
    
    public FechaDeCierreEntregaReporte(String fecha, int idReporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaReporte = 0;
        this.fecha = fecha;
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }
    
    public FechaDeCierreEntregaReporte(int idFechaDeCierreEntregaReporte, String fecha, int idReporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaReporte = idFechaDeCierreEntregaReporte;
        this.fecha = fecha;
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }
    
    // Getters of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public int getIdFechaDeCierreEntregaReporte() {
        return idFechaDeCierreEntregaReporte;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdReporteDeTutoriaAcademica() {
        return idReporteDeTutoriaAcademica;
    }
    
    // Setters of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public void setIdFechaDeCierreEntregaReporte(int idFechaDeCierreEntregaReporte) {
        this.idFechaDeCierreEntregaReporte = idFechaDeCierreEntregaReporte;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdReporteDeTutoriaAcademica(int idReporteDeTutoriaAcademica) {
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FechaDeCierreEntregaReporte) {
            FechaDeCierreEntregaReporte tmpFechaDeCierreEntregaReporte = (FechaDeCierreEntregaReporte)obj;
            if(this.idFechaDeCierreEntregaReporte == tmpFechaDeCierreEntregaReporte.getIdFechaDeCierreEntregaReporte()
                    && this.fecha.equals(tmpFechaDeCierreEntregaReporte.getFecha())
                    && this.idReporteDeTutoriaAcademica == tmpFechaDeCierreEntregaReporte.getIdReporteDeTutoriaAcademica()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.getFecha();
    }
}
