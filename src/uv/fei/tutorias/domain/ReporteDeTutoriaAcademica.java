package uv.fei.tutorias.domain;

// author @liu
public class ReporteDeTutoriaAcademica {
    private int idReporteDeTutoriaAcademica;
    private String descripcionGeneral;
    private int idSesionDeTutoriaAcademica;
    private int idTutorAcademico;

    // Getters of uv.fei.tutorias.domain.ReporteDeTutoriaAcademica
    public int getIdReporteDeTutoriaAcademica() {
        return idReporteDeTutoriaAcademica;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    // Setters of uv.fei.tutorias.domain.ReporteDeTutoriaAcademica
    public void setIdReporteDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }
}