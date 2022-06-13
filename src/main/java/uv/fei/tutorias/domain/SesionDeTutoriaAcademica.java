package uv.fei.tutorias.domain;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

// author @liu
public class SesionDeTutoriaAcademica {
    private int id;
    private Date fecha;
    private boolean ocurrio;
    private int idPeriodoEscolar;

    public SesionDeTutoriaAcademica() {
        this.id = 0;
        this.fecha = new Date();
        this.ocurrio = false;
        this.idPeriodoEscolar = 0;
    }

    public SesionDeTutoriaAcademica(Date fecha, boolean ocurrio, int idPeriodoEscolar) {
        this.id = 0;
        this.fecha = fecha;
        this.ocurrio = ocurrio;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
    
    public SesionDeTutoriaAcademica(int id, Date fecha, boolean ocurrio, int idPeriodoEscolar) {
        this.id = id;
        this.fecha = fecha;
        this.ocurrio = ocurrio;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public String getFechaConFormato() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaConFormato = formatoFecha.format((TemporalAccessor) this.fecha);
        return fechaConFormato;
    }

    public boolean getOcurrio() {
        return ocurrio;
    }
    
    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setOcurrio(boolean ocurrio) {
        this.ocurrio = ocurrio;
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
                    && this.ocurrio == tmpSesionDeTutoriaAcademica.getOcurrio()
                    && this.idPeriodoEscolar == tmpSesionDeTutoriaAcademica.getIdPeriodoEscolar()) {
                return true;
            }
        }
        return false;
    }
}