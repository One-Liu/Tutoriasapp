package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public interface IFechaDeCierreEntregaDeReporteDAO {
    public List<FechaDeCierreEntregaDeReporte> obtenerFechasDeCierreEntregaDeReporte() throws SQLException;
    public FechaDeCierreEntregaDeReporte obtenerFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaDeReporte) throws SQLException;
    public int agregarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException;
    public boolean modificarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException;
}