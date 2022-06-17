package uv.fei.tutorias.domain;

// author @liu

public class ReporteDeTutoriaAcademica {
    private int id;
    private String descripcionGeneral;
    private int idSesionDeTutoriaAcademica;
    private int idTutorAcademico;

    public ReporteDeTutoriaAcademica(String descripcionGeneral, int idSesionDeTutoriaAcademica, int idTutorAcademico) {
        this.descripcionGeneral = descripcionGeneral;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idTutorAcademico = idTutorAcademico;
    }

    public ReporteDeTutoriaAcademica() {
        this.descripcionGeneral = "";
        this.idSesionDeTutoriaAcademica = 0;
        this.idTutorAcademico = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ReporteDeTutoriaAcademica) {
            ReporteDeTutoriaAcademica tmpReporteDeTutoriaAcademica = (ReporteDeTutoriaAcademica)obj;
            if(this.id == tmpReporteDeTutoriaAcademica.getId()
                && this.descripcionGeneral.equals(tmpReporteDeTutoriaAcademica.getDescripcionGeneral())
                && this.idSesionDeTutoriaAcademica == tmpReporteDeTutoriaAcademica.getIdSesionDeTutoriaAcademica()
                && this.idTutorAcademico == tmpReporteDeTutoriaAcademica.getIdTutorAcademico()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ReporteDeTutoriaAcademica{" +
                "id=" + id +
                ", descripcionGeneral='" + descripcionGeneral + '\'' +
                ", idSesionDeTutoriaAcademica=" + idSesionDeTutoriaAcademica +
                ", idTutorAcademico=" + idTutorAcademico +
                '}';
    }
}