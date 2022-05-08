package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaDeReporte {
    private int idFechaDeCierreEntregaDeReporte;
    private String fecha;
    private int idReporteDeTutoriaAcademica;

    // Constructors of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public FechaDeCierreEntregaDeReporte() {
        this.idFechaDeCierreEntregaDeReporte = 0;
        this.fecha = "";
        this.idReporteDeTutoriaAcademica = 0;
    }

    public FechaDeCierreEntregaDeReporte(String fecha, int idReporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaDeReporte = 0;
        this.fecha = fecha;
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }

    public FechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte, String fecha, int idReporteDeTutoriaAcademica) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
        this.fecha = fecha;
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }

    // Getters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public int getIdFechaDeCierreEntregaDeReporte() {
        return idFechaDeCierreEntregaDeReporte;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdReporteDeTutoriaAcademica() {
        return idReporteDeTutoriaAcademica;
    }

    // Setters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public void setIdFechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdReporteDeTutoriaAcademica(int idReporteDeTutoriaAcademica) {
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FechaDeCierreEntregaDeReporte) {
            FechaDeCierreEntregaDeReporte tmpFechaDeCierreEntregaDeReporte = (FechaDeCierreEntregaDeReporte)obj;
            if(this.idFechaDeCierreEntregaDeReporte == tmpFechaDeCierreEntregaDeReporte.getIdFechaDeCierreEntregaDeReporte()
                    && this.fecha.equals(tmpFechaDeCierreEntregaDeReporte.getFecha())
                    && this.idReporteDeTutoriaAcademica == tmpFechaDeCierreEntregaDeReporte.getIdReporteDeTutoriaAcademica()) {
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