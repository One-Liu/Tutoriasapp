package uv.fei.tutorias.domain;

// author @liu
public class FechaDeCierreEntregaDeReporte {
    private int id;
    private String fecha;

    // Constructors of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public FechaDeCierreEntregaDeReporte() {
        this.id = 0;
        this.fecha = "";
    }

    public FechaDeCierreEntregaDeReporte(String fecha) {
        this.id = 0;
        this.fecha = fecha;
    }

    public FechaDeCierreEntregaDeReporte(int id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    // Getters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    // Setters of uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte
    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
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

    @Override
    public String toString() {
        return this.getFecha();
    }
}