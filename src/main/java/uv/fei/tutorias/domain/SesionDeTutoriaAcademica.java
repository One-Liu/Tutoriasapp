package uv.fei.tutorias.domain;

// author @liu

public class SesionDeTutoriaAcademica {
    private int id;
    private String fecha;
    private String hora;
    private int idPeriodoEscolar;

    public SesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica, String fechaSesionDeTutoriaAcademica, int idPeriodoEscolar) {
        this.id = idSesionDeTutoriaAcademica;
        this.fecha = fechaSesionDeTutoriaAcademica;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public SesionDeTutoriaAcademica() {
    }

    public SesionDeTutoriaAcademica(String fecha, int idPeriodoEscolar) {
        this.fecha = fecha;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    // Getters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    // Setters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdPeriodoEscolar(int idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
}