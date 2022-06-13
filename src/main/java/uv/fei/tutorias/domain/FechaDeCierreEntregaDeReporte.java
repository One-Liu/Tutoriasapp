package uv.fei.tutorias.domain;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

// author @liu
public class FechaDeCierreEntregaDeReporte {
    private int id;
    private Date fecha;

    public FechaDeCierreEntregaDeReporte() {
        this.id = 0;
        this.fecha = new Date();
    }

    public FechaDeCierreEntregaDeReporte(Date fecha) {
        this.id = 0;
        this.fecha = fecha;
    }

    public FechaDeCierreEntregaDeReporte(int id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FechaDeCierreEntregaDeReporte) {
            FechaDeCierreEntregaDeReporte tmpFechaDeCierreEntregaDeReporte = (FechaDeCierreEntregaDeReporte)obj;
            if(this.id == tmpFechaDeCierreEntregaDeReporte.getId()
                    && this.fecha.equals(tmpFechaDeCierreEntregaDeReporte.getFecha())) {
                return true;
            }
        }
        return false;
    }
}