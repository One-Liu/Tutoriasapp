package uv.fei.tutorias.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

// author @liu
public class PeriodoEscolar {
    private int id;
    private Date fechaInicio;
    private Date fechaTermino;

    public PeriodoEscolar() {
        this.id = 0;
        this.fechaInicio = new Date();
        this.fechaTermino = new Date();
    }
    
    public PeriodoEscolar(Date fechaInicio, Date fechaTermino) {
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.id = 0;
    }
    
    public PeriodoEscolar(int id, Date fechaInicio, Date fechaTermino) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }
    
    public int getId() {
        return id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }
    
    public String getFechas() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMMM yyyy");
        String fechaInicioConFormato = formatoFecha.format(this.fechaInicio);
        String fechaTerminoConFormato = formatoFecha.format(this.fechaTermino);
        return fechaInicioConFormato.toUpperCase() + " - " + fechaTerminoConFormato.toUpperCase();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PeriodoEscolar) {
            PeriodoEscolar tmpPeriodoEscolar = (PeriodoEscolar)obj;
            if(this.id == tmpPeriodoEscolar.getId()
                    && this.fechaInicio.equals(tmpPeriodoEscolar.getFechaInicio())
                    && this.fechaTermino.equals(tmpPeriodoEscolar.getFechaTermino())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "PeriodoEscolar{" + 
                "id=" + id + 
                ", fechaInicio=" + fechaInicio + 
                ", fechaTermino=" + fechaTermino 
                + '}';
    }
}
