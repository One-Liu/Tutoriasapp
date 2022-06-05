package uv.fei.tutorias.domain;

// author @liu
public class SesionDeTutoriaAcademica {
    private int id;
    private String fecha;
    private String hora;
    private int idPeriodoEscolar;

    public SesionDeTutoriaAcademica() {
        this.id = 0;
        this.fecha = "";
        this.hora = "";
        this.idPeriodoEscolar = 0;
    }

    public SesionDeTutoriaAcademica(String fecha, String hora, int idPeriodoEscolar) {
        this.id = 0;
        this.fecha = fecha;
        this.hora = hora;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
    
    public SesionDeTutoriaAcademica(int id, String fecha, String hora, int idPeriodoEscolar) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public SesionDeTutoriaAcademica(int id, String fecha, int idPeriodoEscolar) {
        this.id = id;
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SesionDeTutoriaAcademica) {
            SesionDeTutoriaAcademica tmpSesionDeTutoriaAcademica = (SesionDeTutoriaAcademica)obj;
            if(this.id == tmpSesionDeTutoriaAcademica.getId()
                    && this.fecha.equals(tmpSesionDeTutoriaAcademica.getFecha())
                    && this.hora.equals(tmpSesionDeTutoriaAcademica.getHora())
                    && this.idPeriodoEscolar == tmpSesionDeTutoriaAcademica.getIdPeriodoEscolar()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getFecha();
    }
}