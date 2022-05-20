package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public interface IFechaDeCierreEntregaDeReporteDAO {
    public ArrayList<FechaDeCierreEntregaDeReporte> findFechasDeCierreEntregaDeReporteByFecha(String searchDate);
    public FechaDeCierreEntregaDeReporte findFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaDeReporte);
    public boolean addFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte);
    public boolean deleteFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaDeReporte);
}