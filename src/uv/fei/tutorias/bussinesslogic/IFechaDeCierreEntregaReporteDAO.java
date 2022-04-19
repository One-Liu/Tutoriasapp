package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.List;
import uv.fei.tutorias.domain.FechaDeCierreEntregaReporte;

// author @liu
public interface IFechaDeCierreEntregaReporteDAO {
    public List<FechaDeCierreEntregaReporte> findFechasDeCierreEntregaReporteByFecha(String searchDate);
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte);
    public FechaDeCierreEntregaReporte getFechaDeCierreEntregaReporte(ResultSet resultSet);
    public boolean addFechaDeCierreEntregaReporte(FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte);
    public boolean deleteFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte);
}
