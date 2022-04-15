package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaReporte {
    private int idFechaDeCierreEntregaReporte;
    private String fecha;
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica;
    
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
}
