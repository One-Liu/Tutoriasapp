package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaDeReporte {
    private int idFechaDeCierreEntregaDeReporte;
    private String fecha;

    // Constructors of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public FechaDeCierreEntregaDeReporte() {
        this.idFechaDeCierreEntregaDeReporte = 0;
        this.fecha = "";
    }

    public FechaDeCierreEntregaDeReporte(String fecha) {
        this.idFechaDeCierreEntregaDeReporte = 0;
        this.fecha = fecha;
    }

    public FechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte, String fecha) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
        this.fecha = fecha;
    }

    // Getters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public int getIdFechaDeCierreEntregaDeReporte() {
        return idFechaDeCierreEntregaDeReporte;
    }

    public String getFecha() {
        return fecha;
    }

    // Setters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public void setIdFechaDeCierreEntregaDeReporte(int idFechaDeCierreEntregaDeReporte) {
        this.idFechaDeCierreEntregaDeReporte = idFechaDeCierreEntregaDeReporte;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FechaDeCierreEntregaDeReporte) {
            FechaDeCierreEntregaDeReporte tmpFechaDeCierreEntregaDeReporte = (FechaDeCierreEntregaDeReporte)obj;
            if(this.idFechaDeCierreEntregaDeReporte == tmpFechaDeCierreEntregaDeReporte.getIdFechaDeCierreEntregaDeReporte()
                    && this.fecha.equals(tmpFechaDeCierreEntregaDeReporte.getFecha())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getFecha();
    }
}