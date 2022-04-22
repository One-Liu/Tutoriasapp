package uv.fei.tutorias.domain;

// author @liu

public class ReporteDeTutoriaAcademica {
    private int idReporteDeTutoriaAcademica;
    private String descripcionGeneral;
    private int idSesionDeTutoriaAcademica;
    private int idTutorAcademico;

    public ReporteDeTutoriaAcademica(int idReporteDeTutoriaAcademica, String descripcionGeneral, int idSesionDeTutoriaAcademica, int idTutorAcademico) {
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
        this.descripcionGeneral = descripcionGeneral;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idTutorAcademico = idTutorAcademico;
    }

    public ReporteDeTutoriaAcademica() {
        this.idReporteDeTutoriaAcademica = 0;
        this.descripcionGeneral = "";
        this.idSesionDeTutoriaAcademica = 0;
        this.idTutorAcademico = 0;
    }

    public int getIdReporteDeTutoriaAcademica() {
        return idReporteDeTutoriaAcademica;
    }

    public void setIdReporteDeTutoriaAcademica(int idReporteDeTutoriaAcademica) {
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }
}