package uv.fei.tutorias.domain;

// author @liu
public class ReporteDeTutoriaAcademica {
    private int idReporteDeTutoriaAcademica;
    private String descripcionGeneral;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    private TutorAcademico tutorAcademico;

    // Getters of uv.fei.tutorias.domain.ReporteDeTutoriaAcademica
    public int getIdReporteDeTutoriaAcademica() {
        return idReporteDeTutoriaAcademica;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica() {
        return sesionDeTutoriaAcademica;
    }

    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    // Setters of uv.fei.tutorias.domain.ReporteDeTutoriaAcademica
    public void setIdReporteDeTutoriaAcademica(int idReporteDeTutoriaAcademica) {
        this.idReporteDeTutoriaAcademica = idReporteDeTutoriaAcademica;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }

    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }
}