package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaReporte {
    private int idFechaDeCierreEntregaReporte;
    private String fecha;
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica;
    
    // Constructors of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public FechaDeCierreEntregaReporte() {
        this.idFechaDeCierreEntregaReporte = 0;
        this.fecha = "";
        this.reporteDeTutoriaAcademica = null;
    }
    
    public FechaDeCierreEntregaReporte(int idFechaDeCierreEntregaReporte, String fecha, ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaReporte = idFechaDeCierreEntregaReporte;
        this.fecha = fecha;
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademica;
    }
    
    public FechaDeCierreEntregaReporte(String fecha, ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaReporte = 0;
        this.fecha = fecha;
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademica;
    }
    
    // Getters of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public int getIdFechaDeCierreEntregaReporte() {
        return idFechaDeCierreEntregaReporte;
    }

    public String getFecha() {
        return fecha;
    }

    public ReporteDeTutoriaAcademica getReporteDeTutoriaAcademica() {
        return reporteDeTutoriaAcademica;
    }
    
    // Setters of uv.fei.tutorias.domain.FechaDeCierreEntregaReporte
    public void setIdFechaDeCierreEntregaReporte(int idFechaDeCierreEntregaReporte) {
        this.idFechaDeCierreEntregaReporte = idFechaDeCierreEntregaReporte;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) {
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademica;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FechaDeCierreEntregaReporte) {
            FechaDeCierreEntregaReporte tmpFechaDeCierreEntregaReporte = (FechaDeCierreEntregaReporte)obj;
            if(this.idFechaDeCierreEntregaReporte == tmpFechaDeCierreEntregaReporte.getIdFechaDeCierreEntregaReporte()
                    && this.fecha.equals(tmpFechaDeCierreEntregaReporte.getFecha())
                    && this.reporteDeTutoriaAcademica.equals(tmpFechaDeCierreEntregaReporte.getReporteDeTutoriaAcademica())) {
                return true;
            }
        }
        return false;
    }
}
