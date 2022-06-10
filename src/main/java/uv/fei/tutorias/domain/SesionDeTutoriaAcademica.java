package uv.fei.tutorias.domain;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

// author @liu
public class SesionDeTutoriaAcademica {
    private int id;
    private Date fecha;
    private int idFechaDeCierreEntregaDeReporte;
    private int idPeriodoEscolar;

    public SesionDeTutoriaAcademica() {
        this.id = 0;
        this.fecha = new Date();
        this.idFechaDeCierreEntregaDeReporte = 0;
        this.idPeriodoEscolar = 0;
    }

    public SesionDeTutoriaAcademica(Date fecha, int idFechaDeCierreEntregaDeReporte, int idPeriodoEscolar) {
        this.id = 0;
        this.fecha = fecha;
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
    
    public SesionDeTutoriaAcademica(int id, Date fecha, int idFechaDeCierreEntregaDeReporte, int idPeriodoEscolar) {
        this.id = id;
        this.fecha = fecha;
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    // Getters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
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

    public int getIdFechaDeCierreEntregaDeReporte() {
        return idFechaDeCierreEntregaDeReporte;
    }
    
    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    // Setters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setIdFechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
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
                    && this.idFechaDeCierreEntregaDeReporte == tmpSesionDeTutoriaAcademica.getIdFechaDeCierreEntregaDeReporte()
                    && this.idPeriodoEscolar == tmpSesionDeTutoriaAcademica.getIdPeriodoEscolar()) {
                return true;
            }
        }
        return false;
    }
}