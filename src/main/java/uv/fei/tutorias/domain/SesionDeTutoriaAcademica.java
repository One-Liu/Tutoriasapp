package uv.fei.tutorias.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

// author @liu
public class SesionDeTutoriaAcademica {

    private int id;
    private Date fecha;
    private boolean ocurrio;
    private int idPeriodoEscolar;
    private int idFechaDeCierreEntregaDeReporte;

    public SesionDeTutoriaAcademica() {
        this.id = 0;
        this.fecha = null;
        this.ocurrio = false;
        this.idPeriodoEscolar = 0;
        this.idFechaDeCierreEntregaDeReporte = 0;
    }

    public SesionDeTutoriaAcademica(Date fecha, boolean ocurrio, int idPeriodoEscolar, int idFechaDeCierreEntregaDeReporte) {
        this.id = 0;
        this.fecha = fecha;
        this.ocurrio = ocurrio;
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
    }

    public SesionDeTutoriaAcademica(int id, Date fecha, boolean ocurrio, int idPeriodoEscolar, int idFechaDeCierreEntregaDeReporte) {
        this.id = id;
        this.fecha = fecha;
        this.ocurrio = ocurrio;
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFechaConFormato() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaConFormato = formatoFecha.format(this.fecha);
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

    public int getIdFechaDeCierreEntregaDeReporte() {
        return idFechaDeCierreEntregaDeReporte;
    }

    public void setIdFechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SesionDeTutoriaAcademica) {
            SesionDeTutoriaAcademica tmpSesionDeTutoriaAcademica = (SesionDeTutoriaAcademica) obj;
            if(this.id == tmpSesionDeTutoriaAcademica.getId()
                && this.fecha.equals(tmpSesionDeTutoriaAcademica.getFecha())
                && this.ocurrio == tmpSesionDeTutoriaAcademica.getOcurrio()
                && this.idPeriodoEscolar == tmpSesionDeTutoriaAcademica.getIdPeriodoEscolar()
                && this.idFechaDeCierreEntregaDeReporte == tmpSesionDeTutoriaAcademica.getIdFechaDeCierreEntregaDeReporte()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "SesionDeTutoriaAcademica{"
            + "id=" + id
            + ", fecha=" + fecha
            + ", ocurrio=" + ocurrio
            + ", idPeriodoEscolar=" + idPeriodoEscolar
            + ", idFechaDeCierreEntregaDeReporte=" + idFechaDeCierreEntregaDeReporte
            + '}';
    }

}
