package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public interface IFechaDeCierreEntregaDeReporteDAO {
    public ArrayList<FechaDeCierreEntregaDeReporte> obtenerFechasDeCierreEntregaDeReporte() throws SQLException;
    public FechaDeCierreEntregaDeReporte obtenerFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaDeReporte) throws SQLException;
    public boolean agregarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException;
    public boolean eliminarFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaDeReporte) throws SQLException;
    public boolean modificarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException;
}