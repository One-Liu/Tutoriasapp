package uv.fei.tutorias.domain;

// author @liu

public class ReporteDeTutoriaAcademica {
    private int id;
    private String descripcionGeneral;
    private int idSesionDeTutoriaAcademica;
    private int idTutorAcademico;
    private int idFechaCierreEntregaReporte;

    public ReporteDeTutoriaAcademica(String descripcionGeneral, int idSesionDeTutoriaAcademica, int idTutorAcademico, int idFechaCierreEntregaReporte) {
        this.descripcionGeneral = descripcionGeneral;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idTutorAcademico = idTutorAcademico;
        this.idFechaCierreEntregaReporte = idFechaCierreEntregaReporte;
    }

    public ReporteDeTutoriaAcademica() {
        this.descripcionGeneral = "";
        this.idSesionDeTutoriaAcademica = 0;
        this.idTutorAcademico = 0;
        this.idFechaCierreEntregaReporte = 0;
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

    public int getIdFechaCierreEntregaReporte() {
        return idFechaCierreEntregaReporte;
    }

    public void setIdFechaCierreEntregaReporte(int idFechaCierreEntregaReporte) {
        this.idFechaCierreEntregaReporte = idFechaCierreEntregaReporte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReporteDeTutoriaAcademica)) return false;

        ReporteDeTutoriaAcademica that = (ReporteDeTutoriaAcademica) o;

        if (getId() != that.getId()) return false;
        if (getIdSesionDeTutoriaAcademica() != that.getIdSesionDeTutoriaAcademica()) return false;
        if (getIdTutorAcademico() != that.getIdTutorAcademico()) return false;
        if (getIdFechaCierreEntregaReporte() != that.getIdFechaCierreEntregaReporte()) return false;
        return getDescripcionGeneral() != null ? getDescripcionGeneral().equals(that.getDescripcionGeneral()) : that.getDescripcionGeneral() == null;
    }

    @Override
    public String toString() {
        return "ReporteDeTutoriaAcademica{" +
                "id=" + id +
                ", descripcionGeneral='" + descripcionGeneral + '\'' +
                ", idSesionDeTutoriaAcademica=" + idSesionDeTutoriaAcademica +
                ", idTutorAcademico=" + idTutorAcademico +
                ", idFechaCierreEntregaReporte=" + idFechaCierreEntregaReporte +
                '}';
    }
}