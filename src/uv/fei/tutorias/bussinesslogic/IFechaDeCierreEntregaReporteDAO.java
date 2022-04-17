package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.FechaDeCierreEntregaReporte;

// author @liu
public interface IFechaDeCierreEntregaReporteDAO {
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteByFecha(String searchDate);
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte);
    public boolean addFechaDeCierreEntregaReporte(FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte);
    public boolean deleteFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte);
}
