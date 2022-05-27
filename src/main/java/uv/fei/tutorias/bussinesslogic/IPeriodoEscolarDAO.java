package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.sql.SQLException;
import domain.PeriodoEscolar;

// author @liu
public interface IPeriodoEscolarDAO {
    public ArrayList<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException;
    public PeriodoEscolar obtenerPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException;
    public boolean agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException;
    public boolean eliminarPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException;
    public boolean modificarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException;
}